package org.auth.authservice;

import lombok.RequiredArgsConstructor;
import org.auth.authservice.cache.domain.UserAuth;
import org.auth.authservice.cache.repository.UserAuthSessionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserAuthSessionRepository sessionRepository;
    private final OtpClient otpClient;

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
    public String codePage(@ModelAttribute("username") String username) {
        var otpResponse = otpClient.getOtp(username);
        String otpCode = otpResponse.getBody();
        return otpCode;
    }

}
