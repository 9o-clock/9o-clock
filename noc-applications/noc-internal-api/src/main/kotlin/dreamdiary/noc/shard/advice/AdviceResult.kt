package pcloud.dp.shard.advice

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

open class AdviceResult(
    val message: String = "success",
    val success:Boolean = true,
    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val timestamp: LocalDateTime = LocalDateTime.now(),
)

class AdviceDataResult<T>(
    val data:T
) : AdviceResult()

class AdviceErrorResult(
    val errors: List<String> = listOf()
) : AdviceResult("failed", false)
