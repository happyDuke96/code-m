package org.auth.authservice.cache.repository;

import org.auth.authservice.cache.domain.UserAuth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository("userAuthSessionRepository")
public interface UserAuthSessionRepository extends CrudRepository<UserAuth,String> {

    UserAuth findByUsername(String username);
}
