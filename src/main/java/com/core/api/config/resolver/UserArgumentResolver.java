package com.core.api.config.resolver;

import com.core.api.auth.AuthConstants;
import com.core.api.auth.AuthToken;
import com.core.api.auth.AuthUser;
import com.core.api.auth.AuthUserImpl;
import com.core.api.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(AuthUser.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        var httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();

        var accessToken = httpServletRequest.getHeader(AuthConstants.AUTH_TOKEN_HEADER_KEY);

        if (accessToken == null) {
            if (parameter.isOptional()) {
                return null;
            }

            accessToken = "";
        }

        var token = new AuthToken(accessToken);

        return getAuthUser(token);
    }

    public AuthUser getAuthUser(AuthToken token) {
        var user = userService.findByToken(token.getToken());
        return new AuthUserImpl(user.getId(), user.getNickname());
    }
}
