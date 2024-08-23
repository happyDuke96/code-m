package org.auth.authservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "otp-client", url = "${app.config.otp}")
public interface OtpClient {

    @GetMapping("/generate")
    ResponseEntity<String> getOtp(@RequestParam("username") String username);
}
