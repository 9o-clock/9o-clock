package dreamdiary.nineoclock.quiz.infrastructure

data class UserStatusInfo(
    val isDeactivated: Boolean,
    val isBlacklisted: Boolean,
    val role: UserRole
)

enum class UserRole {}
