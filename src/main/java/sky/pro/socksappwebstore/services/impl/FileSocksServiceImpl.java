package sky.pro.socksappwebstore.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import sky.pro.socksappwebstore.model.Socks;
import sky.pro.socksappwebstore.model.SocksBatch;
import sky.pro.socksappwebstore.services.FileSocksService;
import sky.pro.socksappwebstore.services.FilesService;

import java.util.HashMap;

@Repository
@AllArgsConstructor
public class FileSocksServiceImpl implements FileSocksService {
    private HashMap<Socks, Integer> socksMap = new HashMap<>();
    private final FilesService filesService;

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
            int totalQuantity = socksMap.get(socks);
            if (totalQuantity > socksBatch.getQuantity()) {
                socksMap.replace(socks, socksMap.get(socks) - socksBatch.getQuantity());
                saveToFile();
                return socksBatch.getQuantity();
            } else {
                socksMap.remove(socks);
                saveToFile();
                return totalQuantity;
            }
        }
        return 0;
    }

    @Override
    public HashMap<Socks, Integer> getAll() {
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
}
