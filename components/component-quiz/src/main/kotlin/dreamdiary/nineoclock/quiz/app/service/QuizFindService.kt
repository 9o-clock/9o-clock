package dreamdiary.nineoclock.quiz.app.service

import dreamdiary.nineoclock.quiz.app.usecase.ChoiceQueryResult
import dreamdiary.nineoclock.quiz.app.usecase.QuizFindUseCase
import dreamdiary.nineoclock.quiz.app.usecase.QuizQueryResult
import dreamdiary.nineoclock.quiz.domain.QuizRepository
import dreamdiary.nineoclock.shard.identifier.QuizPublicId
import org.springframework.stereotype.Service

@Service
internal class QuizFindService(
    private val quizRepository: QuizRepository
) : QuizFindUseCase {
    override fun findQuiz(publicId: String): QuizQueryResult? {
        return quizRepository.findByPublicId(QuizPublicId(publicId))?.let {
            QuizQueryResult(
                publicId = it.id.publicId.value,
                title = it.title.value,
                content = it.content.value,
                choices = it.choiceGroup.values.map { choice ->
                    ChoiceQueryResult(
                        publicId = choice.id.publicId.value,
                        text = choice.value,
                        isAnswer = choice.isAnswer
                    )
                },
                releaseAt = it.releaseAt,
                answerReleaseAt = it.answerReleaseAt,
            )
        }
    }
}
