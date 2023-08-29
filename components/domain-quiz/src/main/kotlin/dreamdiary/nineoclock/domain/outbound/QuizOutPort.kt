package dreamdiary.nineoclock.domain.outbound

import dreamdiary.nineoclock.domain.quiz.Quiz
import reactor.core.publisher.Mono

interface QuizOutPort {
    fun save(quiz: Quiz): Mono<Quiz>
}
