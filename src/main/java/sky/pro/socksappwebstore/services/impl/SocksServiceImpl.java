package sky.pro.socksappwebstore.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sky.pro.socksappwebstore.exception.ValidationException;
import sky.pro.socksappwebstore.model.*;
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
    private static Map<Socks, Integer> socksMap = new HashMap<>();
    private final ValidationService validationService;
    private final FilesService filesService;


//    @PostConstruct
//    private void bim() {
//        try {
//            readFromFile();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void accept(SocksBatch socksBatch) {
        validationService.validate(socksBatch);
        save(socksBatch);
    }

    @Override
    public int issuence(SocksBatch socksBatch) {
        validationService.validate(socksBatch);
        return remove(socksBatch);
    }

    @Override
    public int reject(SocksBatch socksBatch) {
        validationService.validate(socksBatch);
        return remove(socksBatch);
    }

    @Override
    public int getCount(Color color, Size size, int cottonMin, int cottonMax) {
        if (validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new ValidationException("Ошибка валидации параметров: цвет, размер, содержание хлопка в носках");
        }
        Map<Socks, Integer> socksMap = getAll();
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


    @Override
    public Path createAllSocks() throws IOException {
        Path path = filesService.getSocksFile().toPath();
        for (Map.Entry<Socks, Integer> element : socksMap.entrySet()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
//                writer.append("\n ВРЕМЯ: " + element.getKey().getLocalDate()).append("\n");
                writer.append("\n ЦВЕТ: " + element.getKey().getColor().name()).append("\n");
                writer.append("\n РАЗМЕР: " + element.getKey().getSize().name()).append("\n");
                writer.append("\n КОЛИЧЕСТВО ПАР НОСКОВ: " + element.getValue().toString()).append("\n");
                writer.append("\n");
            }
        }
        return path;
    }


    @Override
    public void save(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();
        if (socksMap.containsKey(socks)) {
            socksMap.replace(socks, socksMap.get(socks) + socksBatch.getQuantity());
            saveToFile();
        } else {
            socksMap.put(socks, socksBatch.getQuantity());
            saveToFile();
        }
    }

    @Override
    public int remove(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();
        if (socksMap.containsKey(socks)) {
            int quantity = socksMap.get(socks);
            if (quantity > socksBatch.getQuantity()) {
                socksMap.replace(socks, quantity - socksBatch.getQuantity());
                saveToFile();
                return socksBatch.getQuantity();
            } else {
                socksMap.remove(socks);
                saveToFile();
                return quantity;
            }
        }
        return 0;
    }

    public Map<Socks, Integer> getAll() {
        return socksMap;
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
            socksMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Socks, Integer>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
