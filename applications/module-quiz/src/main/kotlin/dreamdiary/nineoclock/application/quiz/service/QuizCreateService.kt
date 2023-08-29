package dreamdiary.nineoclock.application.quiz.service

import dreamdiary.nineoclock.application.quiz.usecase.QuizCreateCommand
import dreamdiary.nineoclock.application.quiz.usecase.QuizCreateUseCase
import dreamdiary.nineoclock.domain.outbound.QuizOutPort
import dreamdiary.nineoclock.domain.quiz.Choice
import dreamdiary.nineoclock.domain.quiz.ChoiceGroup
import dreamdiary.nineoclock.domain.quiz.Quiz
import dreamdiary.nineoclock.domain.quiz.QuizContent
import dreamdiary.nineoclock.domain.quiz.QuizTitle
import dreamdiary.nineoclock.domain.service.QuizActionAuthorizer
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
internal class QuizCreateService(
    private val quizActionAuthorizer: QuizActionAuthorizer, // << interface
    private val quizOutPort: QuizOutPort // << interface
) : QuizCreateUseCase {
    override fun createQuiz(command: QuizCreateCommand): Mono<Unit> {
        return quizActionAuthorizer.authorizeQuizAction(command.creatorId)
            .flatMap {
                val quiz = Quiz(
                    title = QuizTitle(command.title),
                    content = QuizContent(command.content),
                    choiceGroup = ChoiceGroup(command.choices.stream().map { Choice("", it, false) }.toList()),
                    releaseAt = command.releaseAt,
                    answerReleaseAt = command.answerReleaseAt
                )
                quizOutPort.save(quiz)
            }
            .map { Unit }
            .subscribeOn(Schedulers.boundedElastic())
    }
}
