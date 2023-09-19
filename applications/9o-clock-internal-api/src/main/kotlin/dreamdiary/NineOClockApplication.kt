package dreamdiary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "dreamdiary.nineoclock.*.interface",
        "dreamdiary.nineoclock.*.infrastructure",
        "dreamdiary.nineoclock.*.domain",
    ]
)
class NineOClockApplication

fun main(args: Array<String>) {
    runApplication<NineOClockApplication>(*args)
}
