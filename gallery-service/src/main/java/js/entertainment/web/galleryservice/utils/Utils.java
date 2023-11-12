package js.entertainment.web.galleryservice.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public final class Utils {
    public static File convertMultiPartToFile(MultipartFile imageFile) throws IOException {
        String file = imageFile.getOriginalFilename();
        assert file != null;
        File convertFile = new File(file);
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(imageFile.getBytes());
        fileOutputStream.close();
        return convertFile;
    }
}
