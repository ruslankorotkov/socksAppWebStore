package sky.pro.socksappwebstore.services.impl;

import org.springframework.stereotype.Service;
import sky.pro.socksappwebstore.model.Color;
import sky.pro.socksappwebstore.model.Size;
import sky.pro.socksappwebstore.model.SocksBatch;
import sky.pro.socksappwebstore.services.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(SocksBatch socksBatch) {
        return socksBatch.getSocks() != null
                && socksBatch.getTotalQuantity() > 0
                && socksBatch.getSocks().getColor() == null
                && socksBatch.getSocks().getSize() == null
                && checkCotton(socksBatch.getSocks().getCottonPart(),socksBatch.getSocks().getCottonPart());
    }

    @Override
    public boolean validate(Color color, Size size, int cottonMin, int cottonMax) {
        return color != null && size != null && checkCotton(cottonMin,cottonMax);
    }
    private boolean checkCotton(int cottonMin,int cottonMax){
        return cottonMin >= 0 && cottonMax <= 100;
    }
}
