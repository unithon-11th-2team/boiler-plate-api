package com.core.api.user.repository;

import com.core.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByNicknameOrDeviceId(String nickname, String deviceId);

    Optional<User> findByToken(String token);
}
