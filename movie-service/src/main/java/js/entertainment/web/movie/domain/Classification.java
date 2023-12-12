package js.entertainment.web.movie.domain;

import js.entertainment.web.movie.constants.ConstantsMovieTableNames;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = ConstantsMovieTableNames.ClassificationTable.NAME)
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ConstantsMovieTableNames.ClassificationTable.Id.NAME)
    private Long id;

    @Column(
            name = "classification",
            length = 20,
            nullable = false,
            unique = true
    )
    private String classification;
}
