package sky.pro.socksappwebstore.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sky.pro.socksappwebstore.services.FilesService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {
    @Value("${path.to.socks.file}")
    private String socksFilePath;
    @Value("${name.of.socks.file}")
    private String socksFileName;


    @Override
    public boolean saveToFile(String json) {
        try {
            deleteFile();
            Files.writeString(Path.of(socksFilePath, socksFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(socksFilePath, socksFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteFile() {
        try {
            Path path = Path.of(socksFilePath, socksFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getSocksFile() {
        return new File(socksFilePath + "/" + socksFileName);
    }

}
