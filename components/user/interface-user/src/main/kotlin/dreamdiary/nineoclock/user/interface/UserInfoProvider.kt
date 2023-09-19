package dreamdiary.nineoclock.user.`interface`

interface UserInfoProvider {
    fun findUserInfo(userSecureId: String): UserInfo?
}
