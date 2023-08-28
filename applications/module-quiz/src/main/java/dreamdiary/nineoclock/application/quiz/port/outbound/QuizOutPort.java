package dreamdiary.nineoclock.application.quiz.port.outbound;

import dreamdiary.nineoclock.domain.quiz.model.Quiz;
import dreamdiary.nineoclock.domain.quiz.model.QuizPublicId;
import dreamdiary.nineoclock.domain.quiz.model.QuizSubmit;
import dreamdiary.nineoclock.domain.quiz.model.QuizTitle;

import java.util.Optional;

public interface QuizOutPort {
    /**
     * 동일한 퀴즈 명이 존재하는지 확인합니다.
     * @param title 퀴즈 제목
     * @return true-존재, false-존재하지 않음.
     */
    boolean isTitleAlreadyExists(final QuizTitle title);

    /**
     * 퀴즈를 저장합니다.
     * @param quiz 퀴즈
     * @return 퀴즈
     */
    Quiz save(final Quiz quiz);

    /**
     * 사용가능한 퀴즈 아이디(공개)를 얻습니다.
     * @return uniqId
     */
    QuizPublicId obtainQuizPublicId();

    Optional<Quiz> findBy(QuizPublicId publicId);
    Optional<Quiz> findBy(Long quizId);

    void submit(QuizSubmit quizSubmit);
}
