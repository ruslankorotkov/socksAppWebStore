package sky.pro.socksappwebstore.services;
import java.io.File;


public interface FilesService {

    boolean saveToFile(String json);

    String readFromFile();

    boolean deleteFile();

    File getSocksFile();

}
