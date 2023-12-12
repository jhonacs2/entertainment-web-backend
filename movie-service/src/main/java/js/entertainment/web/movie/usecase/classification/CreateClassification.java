package js.entertainment.web.movie.usecase.classification;

import js.entertainment.web.movie.domain.Classification;
import js.entertainment.web.movie.dto.request.ClassificationRequest;
import js.entertainment.web.movie.dto.response.CategoryResponse;
import js.entertainment.web.movie.dto.response.ClassificationResponse;
import js.entertainment.web.movie.exceptions.DuplicateEntryException;
import js.entertainment.web.movie.service.ClassificationService;
import js.entertainment.web.movie.utils.Command;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
@Log4j2
public class CreateClassification implements Command<ClassificationResponse> {
    private final ClassificationService classificationService;

    @Setter
    private ClassificationRequest classificationRequest;

    public CreateClassification(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @Override
    public ClassificationResponse execute() {
        try {
            var classification = classificationService.save(buildClassification());
            return buildCategoryResponse(classification);
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new DuplicateEntryException("Duplicate Entry Movie", HttpStatus.CONFLICT);
        }
    }

    private ClassificationResponse buildCategoryResponse(Classification classification) {
        var classificationResponse = new ClassificationResponse();
        classificationResponse.setId(classification.getId());
        classificationResponse.setClassification(classification.getClassification());
        return classificationResponse;
    }

    public Classification buildClassification() {
        var classification = new Classification();
        classification.setClassification(classificationRequest.getClassification());
        return classification;
    }
}
