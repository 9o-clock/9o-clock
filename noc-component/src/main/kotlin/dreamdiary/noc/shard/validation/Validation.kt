package pcloud.dp.shard.validation

import dreamdiary.noc.shard.exception.ValidationException
import jakarta.validation.ConstraintViolation
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

inline fun <T, reified X : RuntimeException> Set<ConstraintViolation<T>>.isNotEmptyToThrow(e: KClass<out X>) {
    if (this.isNotEmpty()) {
        if (ValidationException::class.java.isAssignableFrom(e.java)) {
            val constructor = e.primaryConstructor!!
            if (constructor.parameters.size == 1 &&
                constructor.parameters[0].type.classifier == Set::class
            ) {
                throw constructor.call(this)
            }
        }
        throw e.primaryConstructor!!.call("Validation failed.")
    }
}
