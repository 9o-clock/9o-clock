package dreamdiary.nineoclock.application.quiz.port.inbound;

public record QuizSubmitCommand(String submitterPublicId, String quizPublicId, String choicePublicId) {
}
