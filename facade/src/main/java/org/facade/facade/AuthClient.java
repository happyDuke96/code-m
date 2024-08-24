package org.facade.facade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service",url = "${app.config.auth}")
public interface AuthClient {

    @DeleteMapping("/cache/remove")
    ResponseEntity<Void> removeFromCache(@RequestParam("username") String username);
}
