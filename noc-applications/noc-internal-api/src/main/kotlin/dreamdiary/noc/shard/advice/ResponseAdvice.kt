package dreamdiary.noc.shard.advice

import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.PathContainer
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import org.springframework.web.util.pattern.PathPatternParser
import pcloud.dp.shard.advice.AdviceDataResult
import pcloud.dp.shard.advice.AdviceResult


@RestControllerAdvice
class ResponseAdvice : ResponseBodyAdvice<Any> {
    private val whitelist = listOf(
        PathPatternParser().parse("/v*/api-docs"),
        PathPatternParser().parse("/v*/api-docs/**"),
        PathPatternParser().parse("/docs/**"),
        PathPatternParser().parse("/swagger-resources/**"),
        PathPatternParser().parse("/swagger-ui.html"),
        PathPatternParser().parse("/swagger/**"),
        PathPatternParser().parse("/swagger-ui/**"),
        PathPatternParser().parse("/webjars/**"),
    )

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean  = true

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        return when {
            checkWhiteList(request) -> body
            body != null && body is AdviceResult -> body
            body != null -> AdviceDataResult(data=body)
            else -> AdviceResult()
        }
    }

    fun checkWhiteList(request: ServerHttpRequest): Boolean = whitelist.stream().anyMatch {
        it.matches(PathContainer.parsePath(request.uri.path))
    }
}
