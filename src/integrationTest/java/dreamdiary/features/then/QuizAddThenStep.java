package dreamdiary.features.then;

import cucumbertables.DataTableWrap;
import cucumbertables.MapRowWrap;
import io.cucumber.java8.En;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.TemporalUnitWithinOffset;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class QuizAddThenStep implements En {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public QuizAddThenStep() {
        Then("퀴즈 저장됨", (io.cucumber.datatable.DataTable dataTable) -> {
            final DataTableWrap wrap = new DataTableWrap(dataTable, "<null>", false);
            final MapRowWrap row = wrap.getMapRows().get(0);
            final Long quizId = row.getLong("id");

            SoftAssertions.assertSoftly(s -> {
                SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from quiz where id = ?", quizId);
                if (!rowSet.next()) Assertions.fail("data not found!");
                for (String name : wrap.columnNames()) {
                    String desc = "idx=%d prop=%s".formatted(0, name);
                    switch (name) {
                        case "id":
                            break;
                        case "title":
                        case "content":
                            s.assertThat(rowSet.getString(name)).describedAs(desc).isEqualTo(row.getString(name));
                            break;
                        case "release_at":
                        case "created_at":
                        case "updated_at":
                            s.assertThat(rowSet.getTimestamp(name).toLocalDateTime()).describedAs(desc).isCloseTo(row.getLocalDateTime(name), new TemporalUnitWithinOffset(5, ChronoUnit.SECONDS));
                            break;
                        default:
                            s.fail("invalid property name:" + name);
                    }
                }
            });
        });

        Then("퀴즈 선택지 저장됨", (io.cucumber.datatable.DataTable dataTable) -> {
            final DataTableWrap wrap = new DataTableWrap(dataTable, "<null>", false);
            List<MapRowWrap> rows = wrap.getMapRows();
            SoftAssertions.assertSoftly(s -> {
                SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from quiz_choice order by id asc");
                for (MapRowWrap row : rows) {
                    if (!rowSet.next()) Assertions.fail("data not found!");
                    for (String name : wrap.columnNames()) {
                        String desc = "idx=%d prop=%s".formatted(0, name);
                        switch (name) {
                            case "id", "quiz_id" ->
                                    s.assertThat(rowSet.getLong(name)).describedAs(desc).isEqualTo(row.getLong(name));
                            case "text" ->
                                    s.assertThat(rowSet.getString(name)).describedAs(desc).isEqualTo(row.getString(name));
                            default -> s.fail("invalid property name:" + name);
                        }
                    }
                }
            });
        });
    }
}
