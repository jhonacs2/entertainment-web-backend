package js.entertainment.web.movie.service;

import js.entertainment.web.movie.domain.Classification;

public interface ClassificationService {
    Classification save(Classification category);

    void deleteById(Long id);

    Classification findById(Long id);
}
