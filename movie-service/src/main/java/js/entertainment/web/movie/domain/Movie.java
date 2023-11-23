package js.entertainment.web.movie.domain;

import js.entertainment.web.movie.constants.ConstantsMovieTableNames;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = ConstantsMovieTableNames.MovieTable.NAME)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ConstantsMovieTableNames.MovieTable.Id.NAME)
    private Long id;

    @Column(name = ConstantsMovieTableNames.MovieTable.ImageId.ID)
    private Long imageId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = ConstantsMovieTableNames.CategoryTable.Id.NAME,
            referencedColumnName = ConstantsMovieTableNames.CategoryTable.Id.NAME
    )
    private Category categoryId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = ConstantsMovieTableNames.ClassificationTable.Id.NAME,
            referencedColumnName = ConstantsMovieTableNames.ClassificationTable.Id.NAME
    )
    private Classification classificationId;

    @Column(
            name = "name",
            length = 50,
            nullable = false
    )
    private String Name;

    @Column(
            name = ConstantsMovieTableNames.MovieTable.RelaseDate.NAME,
            nullable = false
    )
    private LocalDate releaseDate;
}
