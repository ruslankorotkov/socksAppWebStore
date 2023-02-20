package sky.pro.socksappwebstore.services.impl;

import org.springframework.stereotype.Repository;
import sky.pro.socksappwebstore.model.Socks;
import sky.pro.socksappwebstore.model.SocksBatch;
import sky.pro.socksappwebstore.services.FileSocksService;

import java.util.HashMap;
import java.util.Map;
@Repository
public class FileSocksServiceImpl implements FileSocksService {
    private HashMap<Socks, Integer> socksMap = new HashMap<>();

    @Override
    public void save(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();
        if (socksMap.containsKey(socks)) {
            socksMap.replace(socks, socksMap.get(socks) + socksBatch.getTotalQuantity());
        } else {
            socksMap.put(socks, socksBatch.getTotalQuantity());
        }

    }

    @Override
    public int remove(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();
        if (socksMap.containsKey(socks)) {
            int totalQuantity = socksMap.get(socks);
            if (totalQuantity > socksBatch.getTotalQuantity()) {
                socksMap.replace(socks, socksMap.get(socks) - socksBatch.getTotalQuantity());
                return socksBatch.getTotalQuantity();
            } else {
                socksMap.remove(socks);
                return totalQuantity;
            }
        }
        return 0;
    }

    @Override
    public Map<Socks, Integer> getAll() {
        return socksMap;
    }
}
