package dreamdiary.quiz.domain;

import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.domain.model.QuizTitle;

import java.util.Optional;

public interface QuizRepository {
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
    Quiz store(final Quiz quiz);

    /**
     * 사용가능한 퀴즈 아이디(공개)를 얻습니다.
     * @return uniqId
     */
    QuizPublicId obtainQuizPublicId();

    Optional<Quiz> findBy(QuizPublicId publicId);
    Optional<Quiz> findBy(Long quizId);
}
