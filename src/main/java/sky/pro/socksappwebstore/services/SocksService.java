package sky.pro.socksappwebstore.services;

import sky.pro.socksappwebstore.model.Color;
import sky.pro.socksappwebstore.model.Size;
import sky.pro.socksappwebstore.model.Socks;
import sky.pro.socksappwebstore.model.SocksBatch;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface SocksService {
    void accept(SocksBatch socksBatch);

    int issuence(SocksBatch socksBatch);

    int reject(SocksBatch socksBatch);

    int getCount(Color color, Size size, int cottonMin, int cottonMax);
    Path createAllSocks() throws IOException;

    void save(SocksBatch socksBatch);

    int remove(SocksBatch socksBatch);

    Map<Socks, Integer> getAll();
}
