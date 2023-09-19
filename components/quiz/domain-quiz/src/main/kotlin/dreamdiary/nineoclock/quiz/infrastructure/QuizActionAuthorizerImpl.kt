package dreamdiary.nineoclock.quiz.infrastructure

import dreamdiary.nineoclock.quiz.domain.QuizActionAuthorizer
import dreamdiary.nineoclock.quiz.domain.QuizAuthorizeQuizSubmitActionResult
import dreamdiary.nineoclock.user.`interface`.UserInfoProvider
import dreamdiary.nineoclock.user.`interface`.UserStatus
import org.springframework.stereotype.Component

@Component
private class QuizActionAuthorizerImpl(
    private val userInfoProvider: UserInfoProvider
) : QuizActionAuthorizer {
    override fun authorizeQuizCreateAction(creatorSecureId: String): Boolean {
        // 단순한 권한 검사라면 Quiz BC만 접근 가능한 QuizActionPermission 모델을 구성하고
        // creatorId를 기록하려 권한 검사를 할 수 있다.
        // 해당 방법으로 User BC에 불필요한 접근과 책임을 줄일 수 있다.
        // 다만 creatorId는 결국 User를 의미하고 User의 상태가 존재하며 Blacklist 같은 기능이 존재한다면?
        // 더 이상의 요청 사항을 받을 수 없어야한다.
        // 물론 Gateway 레벨에서 User의 요청을 차단하면 된다. 요구사항을 어떻게 서술하냐의 차이인데
        // 가령 초기 전체 요구사항에 탈퇴, "차단된 회원은 서비스를 이용할 수 없습니다." 라는 요구사항이 발생하면
        // 서비스의 진입인 Gateway에서 차단해버리면 된다.
        // 아니라면? 구현해야하는게 맞고 BC 간 요청을 주고받을 수 있어야하는데 모듈러 모놀로식에서 모듈을 크게크게 나누면? 불필요한 애들까지 땡겨온다
        return true
    }

    override fun authorizeQuizSubmitAction(submitterSecureId: String): QuizAuthorizeQuizSubmitActionResult {
        return userInfoProvider.findUserInfo(submitterSecureId)?.let {
            if (UserStatus.ACTIVE == it.status) return QuizAuthorizeQuizSubmitActionResult(isPossible = true, message = "success")
            return QuizAuthorizeQuizSubmitActionResult(isPossible = false, message = "Status is %s".format(it.status.name))
        } ?: QuizAuthorizeQuizSubmitActionResult(isPossible = false, message = "NotFound Submitter")
    }
}
