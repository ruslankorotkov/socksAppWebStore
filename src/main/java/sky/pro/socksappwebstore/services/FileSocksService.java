package sky.pro.socksappwebstore.services;

import sky.pro.socksappwebstore.model.Socks;
import sky.pro.socksappwebstore.model.SocksBatch;

import java.util.HashMap;

public interface FileSocksService {
    void save(SocksBatch socksBatch);

    int remove(SocksBatch socksBatch);

    HashMap<Socks, Integer> getAll();
}
