package com.core.api.user.service;

import com.core.api.error.AlreadyExistsException;
import com.core.api.error.ErrorType;
import com.core.api.error.NotFoundException;
import com.core.api.user.dto.UserSignRequest;
import com.core.api.user.dto.UserSignResponse;
import com.core.api.user.entity.User;
import com.core.api.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserSignResponse sign(UserSignRequest request) {
        if (userRepository.existsByNicknameOrDeviceId(request.getNickname(), request.getDeviceId())) {
            throw new AlreadyExistsException(ErrorType.ALREADY_EXISTS_USER_ERROR);
        }

        var createdUser = userRepository.save(
                User.builder()
                        .nickname(request.getNickname())
                        .deviceId(request.getDeviceId())
                        .token(UUID.randomUUID().toString())
                        .build()
        );

        return new UserSignResponse(
                createdUser.getId(),
                createdUser.getNickname(),
                createdUser.getToken()
        );
    }

    public User findByToken(String token) {
        return userRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException(ErrorType.USER_NOT_FOUND_ERROR));
    }
}
