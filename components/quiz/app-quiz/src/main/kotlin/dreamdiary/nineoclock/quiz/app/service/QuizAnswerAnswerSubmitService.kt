package dreamdiary.nineoclock.quiz.app.service

import dreamdiary.nineoclock.quiz.app.usecase.QuizAnswerSubmitCommand
import dreamdiary.nineoclock.quiz.app.usecase.QuizAnswerSubmitUseCase
import dreamdiary.nineoclock.quiz.domain.QuizActionAuthorizer
import dreamdiary.nineoclock.quiz.domain.QuizAnswerSubmitRepository
import dreamdiary.nineoclock.quiz.domain.QuizRepository
import dreamdiary.nineoclock.shard.identifier.ChoicePublicId
import dreamdiary.nineoclock.shard.identifier.QuizPublicId
import jakarta.validation.Validator
import org.springframework.stereotype.Service

@Service
internal class QuizAnswerAnswerSubmitService(
    private val validator: Validator,
    private val authorizer: QuizActionAuthorizer,
    private val quizRepository: QuizRepository,
    private val quizAnswerSubmitRepository: QuizAnswerSubmitRepository
) : QuizAnswerSubmitUseCase {
    override fun submitQuizAnswer(command: QuizAnswerSubmitCommand) {
        validator.validate(command).takeIf { it.isNotEmpty() } ?.let { throw RuntimeException() }
        val quiz = quizRepository.findByPublicId(QuizPublicId(command.quizPublicId)) ?: throw RuntimeException()
        authorizer.authorizeQuizSubmitAction(command.submitterSecureId).takeUnless { it.isPossible }?.let { throw RuntimeException(it.message)}
        val submit = quiz.validateAndToSubmit(command.submitterSecureId, ChoicePublicId(command.choicePublicId))

        quizAnswerSubmitRepository.save(submit)
    }
}
