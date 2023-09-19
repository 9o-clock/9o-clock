package dreamdiary.nineoclock.quiz.app.service

import dreamdiary.nineoclock.quiz.app.usecase.QuizCreateCommand
import dreamdiary.nineoclock.quiz.app.usecase.QuizCreateResult
import dreamdiary.nineoclock.quiz.app.usecase.QuizCreateUseCase
import dreamdiary.nineoclock.quiz.domain.Choice
import dreamdiary.nineoclock.quiz.domain.ChoiceGroup
import dreamdiary.nineoclock.quiz.domain.Quiz
import dreamdiary.nineoclock.quiz.domain.QuizActionAuthorizer
import dreamdiary.nineoclock.quiz.domain.QuizContent
import dreamdiary.nineoclock.quiz.domain.QuizRepository
import dreamdiary.nineoclock.quiz.domain.QuizTitle
import jakarta.validation.Validator
import org.springframework.stereotype.Service

@Service
internal class QuizCreateService(
    private val validator: Validator,
    private val quizActionAuthorizer: QuizActionAuthorizer,
    private val quizRepository: QuizRepository
) : QuizCreateUseCase {

    override fun createQuiz(command: QuizCreateCommand): QuizCreateResult {
        if (validator.validate(command).isNotEmpty()) throw RuntimeException()
        if (!quizActionAuthorizer.authorizeQuizCreateAction(command.creatorId)) throw RuntimeException()

        val quiz = Quiz(
            title = QuizTitle(command.title),
            content = QuizContent(command.content),
            choiceGroup = ChoiceGroup(command.choices.stream().map { choice -> Choice(choice.value, choice.isAnswer) }
                .toList()),
            releaseAt = command.releaseAt,
            answerReleaseAt = command.answerReleaseAt
        )

        quizRepository.save(quiz)
        return QuizCreateResult(quiz.id.publicId.value)
    }
}
