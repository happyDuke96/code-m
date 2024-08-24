package org.auth.authservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "facade-service")
public interface FacadeClient {

    @GetMapping("/facade/click-data")
    List<ClickData> getClickData(@RequestParam("username") String username);

    @PostMapping("/facade/click-data")
    void sendClickData(@RequestBody ClickData clickData);
}
