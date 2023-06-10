package dreamdiary.features;

import dreamdiary.NineOClockApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(classes = NineOClockApplication.class)
@ActiveProfiles("h2")
public class CucumberConfiguration {
}
