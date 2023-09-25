package dreamdiary.noc.shard.exception

import jakarta.validation.ConstraintViolation

class ValidationListException(
    val violations: Collection<ConstraintViolation<*>>
): RuntimeException("Validation Exception")
