package dreamdiary.noc.shard.advice

import dreamdiary.noc.shard.exception.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import pcloud.dp.shard.advice.AdviceErrorResult

@RestControllerAdvice
private class ExceptionAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(e: ValidationException): Any {
        return AdviceErrorResult(
            errors = e.violations.map {
                "[${it.propertyPath}] ${it.message}"
            }
        )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(e: IllegalArgumentException): Any {
        return AdviceErrorResult(
            errors = listOf(e.message ?: e.localizedMessage)
        )
    }

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(e: RuntimeException): Any {
        return AdviceErrorResult(
            errors = listOf(e.message ?: e.localizedMessage)
        )
    }
}
