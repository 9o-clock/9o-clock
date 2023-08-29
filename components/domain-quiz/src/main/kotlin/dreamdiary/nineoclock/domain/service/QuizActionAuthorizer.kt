package dreamdiary.nineoclock.domain.service

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono


@Component
class QuizActionAuthorizer {
    /**
     * Quiz Action=[CREATE, UPDATE, DELETE]
     */
    fun authorizeQuizAction(creatorId: String) : Mono<Unit> {
        return Mono.empty()
    }
}
