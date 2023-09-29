package dreamdiary.noc.shard.exception

import jakarta.validation.ConstraintViolation

class ValidationException(
    val violations: Set<ConstraintViolation<*>>
): RuntimeException("Validation failed for ${violations.size} constraint(s)")
