package org.auth.authservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSession,String> {

    UserSession findByUsername(String username);
}
