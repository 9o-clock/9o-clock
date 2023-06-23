package dreamdiary.quiz.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

interface QuizSubmitRepository extends JpaRepository<QuizSubmitEntity, Long> {
    @Modifying
    @Query(value = "INSERT INTO QUIZ_SUBMIT (quiz_id, submitter_id, choice_id, created_at, updated_at) VALUES (:quizId, :submitterId, :choiceId, :createdAt, :updatedAt)", nativeQuery = true)
    void insertQuizSubmitEntity(@Param("quizId") Long quizId,
                                @Param("submitterId") Long submitterId,
                                @Param("choiceId") Long choiceId,
                                @Param("createdAt") LocalDateTime createdAt,
                                @Param("updatedAt") LocalDateTime updatedAt);
}
