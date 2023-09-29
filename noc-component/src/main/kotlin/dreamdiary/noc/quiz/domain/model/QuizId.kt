package dreamdiary.noc.quiz.domain.model

import pcloud.dp.shard.identifier.ApplyIdentifier
import pcloud.dp.shard.identifier.SpecialApplyIdentifier

class QuizId : SpecialApplyIdentifier<QuizPublicId, QuizSecureId> {
    constructor(): super(QuizPublicId(), QuizSecureId())
    constructor(publicId: QuizPublicId, secureId: QuizSecureId): super(publicId, secureId)
    constructor(publicId: String, secureId: String): super(QuizPublicId(publicId), QuizSecureId(secureId))
}

class QuizPublicId : ApplyIdentifier {
    constructor(): super()
    constructor(value: String): super(value)
}
class QuizSecureId : ApplyIdentifier {
    constructor(): super()
    constructor(value: String): super(value)
}
