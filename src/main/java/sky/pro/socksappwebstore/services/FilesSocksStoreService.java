package sky.pro.socksappwebstore.services;

import java.io.File;

public interface FilesSocksStoreService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean deleteFile();

    boolean saveToAllSocksFile(String json);

    String readFromAllSocksFile();

    boolean deleteAllSocksFile();

    File getSocksFile();

    File getAllSocksFile();

}
