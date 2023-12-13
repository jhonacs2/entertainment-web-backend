package js.entertainment.web.movie.usecase.classification;

import js.entertainment.web.movie.service.ClassificationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Log4j2
public class DeleteClassification {
    private final ClassificationService classificationService;

    public DeleteClassification(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    public void execute(Long classificationId) {
        try {
            var classification = classificationService.findById(classificationId);
            classificationService.deleteById(classification.getId());

        } catch (EntityNotFoundException e) {
            log.error(e);
            throw e;
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    }
}
