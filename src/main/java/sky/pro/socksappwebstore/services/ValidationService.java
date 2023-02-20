package sky.pro.socksappwebstore.services;

import sky.pro.socksappwebstore.model.Color;
import sky.pro.socksappwebstore.model.Size;
import sky.pro.socksappwebstore.model.SocksBatch;

public interface ValidationService {
    boolean validate(SocksBatch socksBatch);

    boolean validate(Color color, Size size, int cottonMin, int cottonMax);
}
