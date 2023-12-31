package js.entertainment.web.movie.controller;

import js.entertainment.web.movie.dto.request.ClassificationRequest;
import js.entertainment.web.movie.dto.response.ClassificationResponse;
import js.entertainment.web.movie.usecase.classification.CreateClassification;
import js.entertainment.web.movie.usecase.classification.DeleteClassification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/classification")
public class ClassificationController {
    private final CreateClassification createClassification;
    private final DeleteClassification deleteClassification;

    public ClassificationController(CreateClassification createClassification, DeleteClassification deleteClassification) {
        this.createClassification = createClassification;
        this.deleteClassification = deleteClassification;
    }

    @PostMapping()
    public ResponseEntity<ClassificationResponse> createClassification(@Valid @RequestBody ClassificationRequest classificationRequest) {
        createClassification.setClassificationRequest(classificationRequest);
        return ResponseEntity.ok(createClassification.execute());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteClassification(@PathVariable("id") Long id) {
        deleteClassification.execute(id);
        return ResponseEntity.noContent().build();
    }
}
