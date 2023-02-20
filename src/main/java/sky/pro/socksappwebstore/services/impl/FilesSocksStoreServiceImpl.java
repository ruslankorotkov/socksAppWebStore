package sky.pro.socksappwebstore.services.impl;

import sky.pro.socksappwebstore.services.FilesSocksStoreService;

import java.io.File;

public class FilesSocksStoreServiceImpl implements FilesSocksStoreService {
    @Override
    public boolean saveToFile(String json) {
        return false;
    }

    @Override
    public String readFromFile() {
        return null;
    }

    @Override
    public boolean deleteFile() {
        return false;
    }

    @Override
    public boolean saveToAllSocksFile(String json) {
        return false;
    }

    @Override
    public String readFromAllSocksFile() {
        return null;
    }

    @Override
    public boolean deleteAllSocksFile() {
        return false;
    }

    @Override
    public File getSocksFile() {
        return null;
    }

    @Override
    public File getAllSocksFile() {
        return null;
    }
}
