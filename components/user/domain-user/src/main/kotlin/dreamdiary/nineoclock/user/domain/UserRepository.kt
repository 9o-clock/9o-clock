package dreamdiary.nineoclock.user.domain

import dreamdiary.nineoclock.shard.identifier.UserSecureId

interface UserRepository {
    fun findBy(userSecureId: UserSecureId): User?
}
