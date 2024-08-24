package org.facade.facade;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/facade")
public class FacadeController {

    private final List<ClickData> clickDataList = new ArrayList<>();

    @PostMapping("/click-data")
    @ResponseBody
    public void handleClickData(@RequestBody ClickData clickData) {
        clickDataList.add(clickData);
    }

    @GetMapping("/click-data")
    public String getClickData(@RequestParam("username") String username, Model model) {
        model.addAttribute("data", clickDataList);
        return "data-table";
    }
}
