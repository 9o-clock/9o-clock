package dreamdiary.nineoclock.application.quiz.usecase;

public record QuizSubmitCommand(String submitterPublicId, String quizPublicId, String choicePublicId) {
}
