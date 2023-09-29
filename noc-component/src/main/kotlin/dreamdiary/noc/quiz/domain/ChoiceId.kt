package dreamdiary.noc.quiz.domain

import pcloud.dp.shard.identifier.ApplyIdentifier
import pcloud.dp.shard.identifier.SpecialApplyIdentifier

class ChoiceId : SpecialApplyIdentifier<ChoicePublicId, ChoiceSecureId> {
    constructor(): super(ChoicePublicId(), ChoiceSecureId())
    constructor(publicId: ChoicePublicId, secureId: ChoiceSecureId): super(publicId, secureId)
    constructor(publicId: String, secureId: String): super(ChoicePublicId(publicId), ChoiceSecureId(secureId))
}

class ChoicePublicId : ApplyIdentifier {
    constructor(): super()
    constructor(value: String): super(value)
}
class ChoiceSecureId : ApplyIdentifier {
    constructor(): super()
    constructor(value: String): super(value)
}
