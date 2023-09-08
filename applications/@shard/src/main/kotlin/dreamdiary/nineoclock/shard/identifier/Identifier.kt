package dreamdiary.nineoclock.shard.identifier

import java.nio.charset.StandardCharsets
import java.util.Base64
import java.util.UUID

interface Identifier {
    fun value() : String
}

abstract class ApplyIdentifier(open val value: String = generate()) : Identifier {
    companion object {
        internal fun generate(): String = Base64.getEncoder().encodeToString(
            UUID.randomUUID().toString().toByteArray(
                StandardCharsets.UTF_8
            )
        )
    }

    override fun value() : String {
        return value
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Identifier

        return value == other.value()
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

abstract class SpecialApplyIdentifier<P: ApplyIdentifier, S: ApplyIdentifier>(
    open val publicId: P,
    open val secureId: S
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpecialApplyIdentifier<*, *>

        return publicId == other.publicId && secureId == other.secureId
    }

    override fun hashCode(): Int {
        return (publicId.value + secureId.value).hashCode()
    }
}
