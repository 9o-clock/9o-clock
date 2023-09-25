package dreamdiary.noc.quiz.app

import dreamdiary.noc.quiz.domain.Choice
import dreamdiary.noc.quiz.domain.ChoiceGroup
import dreamdiary.noc.quiz.domain.Quiz
import dreamdiary.noc.quiz.domain.QuizContent
import dreamdiary.noc.quiz.domain.QuizReleasePeriod
import dreamdiary.noc.quiz.domain.QuizRepository
import dreamdiary.noc.quiz.domain.QuizTitle
import dreamdiary.noc.shard.exception.ValidationListException
import jakarta.validation.Validator
import org.springframework.stereotype.Service

@Service
internal class QuizCreateService(
    private val validator:Validator,
    private val quizRepository: QuizRepository
) : QuizCreateUseCase {
    override fun createQuiz(command: QuizCreateCommand):QuizCreateResult {
        validator.validate(command).let {
            if (it.isNotEmpty()) throw ValidationListException(it)
        }

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
