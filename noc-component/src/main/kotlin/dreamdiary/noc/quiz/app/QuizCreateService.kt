package dreamdiary.noc.quiz.app

import dreamdiary.noc.quiz.domain.model.Choice
import dreamdiary.noc.quiz.domain.model.ChoiceGroup
import dreamdiary.noc.quiz.domain.model.Quiz
import dreamdiary.noc.quiz.domain.model.QuizContent
import dreamdiary.noc.quiz.domain.model.QuizReleasePeriod
import dreamdiary.noc.quiz.domain.model.QuizRepository
import dreamdiary.noc.quiz.domain.model.QuizTitle
import dreamdiary.noc.shard.exception.ValidationException
import jakarta.validation.Validator
import org.springframework.stereotype.Service
import pcloud.dp.shard.validation.isNotEmptyToThrow

@Service
internal class QuizCreateService(
    private val validator:Validator,
    private val quizRepository: QuizRepository
) : QuizCreateUseCase {
    override fun createQuiz(command: QuizCreateCommand):QuizCreateResult {
        validator.validate(command).isNotEmptyToThrow(ValidationException::class)

        val quiz = Quiz(
            title = QuizTitle(command.title),
            content = QuizContent(command.content),
            choiceGroup = ChoiceGroup(command.choices.stream().map { choice -> Choice(choice.value, choice.isAnswer) }
                .toList()),
            releasePeriod = QuizReleasePeriod(
                openAt = command.releaseAt,
                answerOpenAt = command.answerReleaseAt
            ),
        )

        quizRepository.save(quiz)

        return QuizCreateResult(quiz.id.publicId.value)
    }
}
