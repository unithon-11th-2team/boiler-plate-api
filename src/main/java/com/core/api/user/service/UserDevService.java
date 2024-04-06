package com.core.api.user.service;

import com.core.api.user.dto.UserInfoDevResponse;
import com.core.api.user.entity.User;
import com.core.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDevService {
    private final UserRepository userRepository;

    public List<UserInfoDevResponse> getAll(List<Long> ids) {
        var isEmptyOrNull = ids == null || ids.isEmpty();

        List<User> users = isEmptyOrNull ? userRepository.findAll() : userRepository.findAllByIdIn(ids);

        return users
                .stream()
                .map(UserInfoDevResponse::from)
                .collect(Collectors.toList());
    }
}
