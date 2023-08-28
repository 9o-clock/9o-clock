package dreamdiary.nineoclock.infrastructure.quiz;

import dreamdiary.quiz.domain.model.QuizPublicId;

import javax.persistence.AttributeConverter;

class QuizPublicIdConverter implements AttributeConverter<QuizPublicId, String> {
    @Override
    public String convertToDatabaseColumn(final QuizPublicId attribute) {
        return null == attribute ? null : attribute.value();
    }

    @Override
    public QuizPublicId convertToEntityAttribute(final String dbData) {
        return new QuizPublicId(dbData);
    }
}
