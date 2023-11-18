package js.entertainment.web.galleryservice.domain;

import js.entertainment.web.galleryservice.constant.ConstantsTableNames;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = ConstantsTableNames.GalleryTable.Name)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ConstantsTableNames.GalleryTable.Id.NAME)
    private Long id;

    @Column(name = ConstantsTableNames.GalleryTable.UUid.NAME)
    private String Uuid;

    @Column(
            name = ConstantsTableNames.GalleryTable.imageName.NAME,
            length = ConstantsTableNames.GalleryTable.imageName.LENGTH,
            nullable = false
    )
    private String imageName;
}
