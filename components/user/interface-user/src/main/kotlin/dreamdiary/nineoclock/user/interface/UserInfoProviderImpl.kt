package dreamdiary.nineoclock.user.`interface`

import dreamdiary.nineoclock.shard.identifier.UserSecureId
import dreamdiary.nineoclock.user.domain.UserRepository
import org.springframework.stereotype.Component

@Component
private class UserInfoProviderImpl(
    private val userRepository: UserRepository
) : UserInfoProvider {
    override fun findUserInfo(userSecureId: String): UserInfo? {
        return userRepository.findBy(UserSecureId(userSecureId))?.let {
            UserInfo(
                nickname = it.nickname.value,
                status = UserStatus.valueOf(it.status.name)
            )
        }
    }
}
