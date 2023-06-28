package dreamdiary.credential;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

public class MockMemberCredentialResolver extends MemberCredentialResolver {
    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return super.supportsParameter(parameter);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
        return super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
    }
}
