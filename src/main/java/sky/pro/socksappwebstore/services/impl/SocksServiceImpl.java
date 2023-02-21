package sky.pro.socksappwebstore.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sky.pro.socksappwebstore.exception.ValidationException;
import sky.pro.socksappwebstore.model.Color;
import sky.pro.socksappwebstore.model.Size;
import sky.pro.socksappwebstore.model.Socks;
import sky.pro.socksappwebstore.model.SocksBatch;
import sky.pro.socksappwebstore.services.FileSocksService;
import sky.pro.socksappwebstore.services.FilesService;
import sky.pro.socksappwebstore.services.SocksService;
import sky.pro.socksappwebstore.services.ValidationService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {
    private HashMap<Socks, Integer> socksMap = new HashMap<>();
    private final FileSocksService fileSocksService;
    private final ValidationService validationService;
    private final FilesService filesService;

    @Override
    public void accept(SocksBatch socksBatch) {
        validationService.validate(socksBatch);
        fileSocksService.save(socksBatch);
        saveToFile();
    }

    @Override
    public int issuence(SocksBatch socksBatch) {
        validationService.validate(socksBatch);
        return fileSocksService.remove(socksBatch);
    }

    @Override
    public int reject(SocksBatch socksBatch) {
        validationService.validate(socksBatch);
        return fileSocksService.remove(socksBatch);
    }

    @Override
    public int getCount(Color color, Size size, int cottonMin, int cottonMax) {
        if (validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new ValidationException("Ошибка валидации параметров: цвет, размер, содержание хлопка в носках");
        }
        HashMap<Socks, Integer> socksMap = fileSocksService.getAll();
        for (Map.Entry<Socks, Integer> element : socksMap.entrySet()) {
            Socks socks = element.getKey();
            if (socks.getColor().equals(color)
                    && socks.getSize().equals(size)
                    && socks.getCottonPart() >= cottonMin
                    && socks.getCottonPart() <= cottonMax) {
                return element.getValue();
            }
        }
        return 0;
    }
    public void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(socksMap);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFromFile() {
        try {
            String json = filesService.readFromFile();
            socksMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Socks,Integer>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Path createAllRecipes() throws IOException {
        Path path = filesService.getAllsocksFile().toPath();
        String listStop = "*";
        for (Integer element : socksMap.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(element.getTitle() + "\n"
                        + "\nВремя приготовления: " +
                        element.getCookingTime()).append(" минут\n");

                writer.append("\nИнгредиенты:\n");
                for (Ingredient ele : element.getIngredients()) {
                    writer.append(listStop).append(ele.toString()).append("\n");
                }
                writer.append("\nИнструкция приготовления:\n");
                for (Step elem : element.getCookingInstructionsSteps()) {
                    writer.append(listStop).append(elem.toString()).append("\n");
                }
                writer.append("\n");
            }
        }
        return path;
    }
@PostConstruct
private void bim() {
    try {
        readFromFile();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
