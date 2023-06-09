package dreamdiary.features.when;

import cucumbertables.DataTableWrap;
import cucumbertables.MapRowWrap;
import dreamdiary.quiz.app.QuizAddRequest;
import dreamdiary.quiz.app.QuizAddUseCase;
import io.cucumber.java8.En;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class QuizAddWhenStep implements En {
    @Autowired
    private QuizAddUseCase quizAddUseCase;

    public QuizAddWhenStep() {
        When("퀴즈 추가 요청", (io.cucumber.datatable.DataTable dataTable) -> {
            DataTableWrap wrap = new DataTableWrap(dataTable.transpose(), "<null>", false);
            MapRowWrap row = wrap.getMapRows().get(0);

            final String choicesStr = row.getString("choices");

            final List<String> choices = Arrays.stream(choicesStr.split(","))
                    .map(String::trim)
                    .toList();

            final QuizAddRequest request = QuizAddRequest.builder()
                    .title(row.getString("title"))
                    .content(row.getString("content"))
                    .choices(choices)
                    .releaseAt(row.getLocalDateTime("releaseAt"))
                    .build();

            quizAddUseCase.addQuiz(request);
        });
    }
}
