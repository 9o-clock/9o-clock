package dreamdiary.nineoclock.user.domain

import dreamdiary.nineoclock.shard.identifier.UserId

class User(
    val nickname: Nickname,
    val status:UserStatus = UserStatus.ACTIVE,
    val id: UserId = UserId()
)
