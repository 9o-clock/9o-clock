package dreamdiary.nineoclock.shard.identifier

class UserId : SpecialApplyIdentifier<UserPublicId, UserSecureId> {
    constructor(): super(UserPublicId(), UserSecureId())
    constructor(publicId: UserPublicId, secureId: UserSecureId): super(publicId, secureId)
    constructor(publicId: String, secureId: String): super(UserPublicId(publicId), UserSecureId(secureId))
}

class UserPublicId : ApplyIdentifier {
    constructor(): super()
    constructor(value: String): super(value)
}
class UserSecureId : ApplyIdentifier {
    constructor(): super()
    constructor(value: String): super(value)
}
