package sky.pro.socksappwebstore.services;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FilesService {

    boolean saveToFile(String json);

    String readFromFile();

    boolean deleteFile();

    File getSocksFile();

}
