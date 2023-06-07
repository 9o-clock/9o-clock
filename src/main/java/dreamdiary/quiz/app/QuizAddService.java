package dreamdiary.quiz.app;

import org.springframework.stereotype.Service;

@Service
class QuizAddService implements QuizAddUseCase {
    @Override
    public void addQuiz() {
        // 요청자
        // 동일한 제목의 퀴즈가 있으면 안되므로 검사
        // 제목, 내용, 개시 일자 정보 생성
        // 퀴즈 저장
    }
}
