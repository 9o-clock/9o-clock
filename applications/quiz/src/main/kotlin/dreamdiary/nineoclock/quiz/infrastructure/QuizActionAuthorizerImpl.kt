package dreamdiary.nineoclock.quiz.infrastructure

import dreamdiary.nineoclock.quiz.domain.service.QuizActionAuthorizer
import org.springframework.stereotype.Component

@Component
private class QuizActionAuthorizerImpl : QuizActionAuthorizer {
    override fun authorizeQuizCreateAction(creatorId: String): Boolean {
        return true
    }
}
