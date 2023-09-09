package dreamdiary.nineoclock.quiz.application.service

import dreamdiary.nineoclock.quiz.helper.QuizTestHelper
import org.mockito.InjectMocks

internal class QuizFindServiceTest : QuizTestHelper() {
    @InjectMocks
    lateinit var quizFindService: QuizFindService
}
