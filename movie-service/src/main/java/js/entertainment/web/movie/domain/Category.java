package js.entertainment.web.movie.domain;

import js.entertainment.web.movie.constants.ConstantsMovieTableNames;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = ConstantsMovieTableNames.CategoryTable.NAME)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ConstantsMovieTableNames.CategoryTable.Id.NAME)
    private Long id;

    @Column(
            name = "category",
            nullable = false,
            length = 20
    )
    private String category;
}
