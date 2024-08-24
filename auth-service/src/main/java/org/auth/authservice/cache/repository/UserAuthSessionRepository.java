package org.auth.authservice.cache.repository;

import org.auth.authservice.cache.domain.UserAuth;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserAuthSessionRepository extends CrudRepository<UserAuth,String> {

    List<UserAuth> findAllByUsername(String username);

    void deleteAllByUsername(String username);
}
