package com.moti.shared.auth;

import com.moti.domain.auth.service.TokenService;
import com.moti.domain.user.domain.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class AuthenticationArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenService tokenService;
    private final AuthenticationExtractor authenticationExtractor;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String token = authenticationExtractor.extract(webRequest);
        return tokenService.getUser(token);
    }

}
