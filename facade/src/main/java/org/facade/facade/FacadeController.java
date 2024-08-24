package org.facade.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/facade")
@RequiredArgsConstructor
public class FacadeController {

    private final List<ClickData> clickDataList = new ArrayList<>();
    private final AuthClient authClient;

    @PostMapping("/click-data")
    @ResponseBody
    public void handleClickData(@RequestBody ClickData clickData) {
        clickDataList.add(clickData);
    }

    @GetMapping("/click-data")
    public String getClickData(@RequestParam("username") String username, Model model) {
        model.addAttribute("data", clickDataList);
        model.addAttribute("username", username);
        return "data-table";
    }

    @GetMapping("/logout")
    public RedirectView logout(@RequestParam("username") String username) {
        authClient.removeFromCache(username);
        return new RedirectView("http://localhost:9000/login");
    }
}
