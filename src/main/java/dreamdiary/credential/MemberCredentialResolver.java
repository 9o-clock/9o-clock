package dreamdiary.credential;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
class MemberCredentialResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(Credential.class);
        boolean hasAuthResultType = MemberCredential.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && hasAuthResultType;
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        final Optional<String> memberPublicIdOpt = Optional.ofNullable(httpServletRequest.getHeader("X-Member-Public-Id"));
        if (memberPublicIdOpt.isEmpty()) throw CredentialFailedException.memberCredentialFailed();
        return new MemberCredential(memberPublicIdOpt.get());
    }
}
