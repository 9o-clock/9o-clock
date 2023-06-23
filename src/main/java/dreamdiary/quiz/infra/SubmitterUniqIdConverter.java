package dreamdiary.quiz.infra;

import dreamdiary.quiz.domain.model.SubmitterUniqId;

import javax.persistence.AttributeConverter;

class SubmitterUniqIdConverter implements AttributeConverter<SubmitterUniqId, Long> {
    @Override
    public Long convertToDatabaseColumn(final SubmitterUniqId attribute) {
        return null == attribute ? null : attribute.value();
    }

    @Override
    public SubmitterUniqId convertToEntityAttribute(final Long dbData) {
        return new SubmitterUniqId(dbData);
    }
}
