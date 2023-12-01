package js.entertainment.web.movie.service;

import js.entertainment.web.movie.domain.Classification;
import js.entertainment.web.movie.repository.ClassificationRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;

    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public Classification save(Classification category) {
        return classificationRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        classificationRepository.deleteById(id);
    }

    @Override
    public Classification findById(Long id) {
        return classificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Classification Not Found"));
    }
}
