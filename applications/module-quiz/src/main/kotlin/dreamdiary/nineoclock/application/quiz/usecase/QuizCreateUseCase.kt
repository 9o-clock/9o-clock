package dreamdiary.nineoclock.application.quiz.usecase

import reactor.core.publisher.Mono

interface QuizCreateUseCase {
    fun createQuiz(command: QuizCreateCommand): Mono<Unit>
}
