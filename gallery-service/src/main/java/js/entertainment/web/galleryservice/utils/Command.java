package js.entertainment.web.galleryservice.utils;

import java.io.IOException;

public interface Command<T> {
    T execute() throws IOException;
}
