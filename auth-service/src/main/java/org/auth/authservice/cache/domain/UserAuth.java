package org.auth.authservice.cache.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@RedisHash("userAuth")
public class UserAuth {

    @Id
    private String id;
    private String username;
    private String password;

}
