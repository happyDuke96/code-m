package org.otp.otpservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/otp")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;

    @GetMapping("/generate")
    public ResponseEntity<String> generateOtp(@RequestParam("username") String username) {
        return ResponseEntity.ok(otpService.getOtp(username));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> remove() {
        otpService.clearOtp("username");
        return ResponseEntity.ok().build();
    }
}
