package sky.pro.socksappwebstore.services;

import sky.pro.socksappwebstore.model.Socks;
import sky.pro.socksappwebstore.model.SocksBatch;

import java.util.Map;

public interface FileSocksService {
    void save(SocksBatch socksBatch);
    int remove(SocksBatch socksBatch);
    Map<Socks,Integer> getAll();
}
