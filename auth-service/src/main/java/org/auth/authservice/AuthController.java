package org.auth.authservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.BooleanUtils;
import org.auth.authservice.cache.domain.UserAuth;
import org.auth.authservice.cache.repository.UserAuthSessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserAuthSessionRepository sessionRepository;
    private final OtpClient otpClient;
    private final FacadeClient facadeClient;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute("login") LoginDto loginDto) {
        UserAuth session = new UserAuth();
        session.setId(UUID.randomUUID().toString());
        session.setUsername(loginDto.getUsername());
        session.setPassword(loginDto.getPassword());
        sessionRepository.save(session);
        return "redirect:/code/" + session.getUsername();
    }

    @GetMapping("/code/{username}")
    public String codePage(@ModelAttribute("username") String username,Model model) {
        var otpResponse = otpClient.getOtp(username);
        String otpCode = otpResponse.getBody();
        model.addAttribute("otpCode", otpCode);
        model.addAttribute("username", username);
        return "validate";
    }

    @PostMapping("/code/validate")
    public String validateCode(@RequestParam("code") String code,
                               @RequestParam("username") String username,
                               @RequestParam(value = "clickData", required = false) String clickDataJson,
                               Model model) throws IOException {
        var response = otpClient.validateOtp(username, code);
        if (BooleanUtils.isTrue(response.getBody())) {
            if (clickDataJson != null) {
                ClickData clickData = new ObjectMapper().reader().readValue(clickDataJson, ClickData.class);
                facadeClient.sendClickData(clickData);
            }
            return "redirect:http://localhost:9002/facade/click-data?username=" + username;
        } else {
            model.addAttribute("error", "Invalid code. Please try again.");
            return "login";
        }
    }

    @PostMapping
    public @ResponseBody
    void handleClickData(@RequestBody ClickData clickData) {
        facadeClient.sendClickData(clickData);
    }

    @GetMapping
    public String getClickData(@RequestParam("username") String username, Model model) {
        List<ClickData> data = facadeClient.getClickData(username);
        model.addAttribute("data", data);
        return "data-table";
    }

}
