package dreamdiary.nineoclock.infrastructure.adapter.quiz

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
internal interface QuizReactiveRepository : ReactiveCrudRepository<QuizEntity, Long> {

}
