package sky.pro.socksappwebstore.services;

import sky.pro.socksappwebstore.model.Color;
import sky.pro.socksappwebstore.model.Size;
import sky.pro.socksappwebstore.model.SocksBatch;

public interface SocksService {
    void accept(SocksBatch socksBatch);

    int issuence(SocksBatch socksBatch);

    int reject(SocksBatch socksBatch);

    int getCount(Color color, Size size, int cottonMin, int cottonMax);
}
