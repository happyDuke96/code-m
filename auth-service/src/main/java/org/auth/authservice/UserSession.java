package org.auth.authservice;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@RedisHash("user-session")
public class UserSession {

    @Id
    private String id;
    private String username;
    private String password;

}
