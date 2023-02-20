package sky.pro.socksappwebstore.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sky.pro.socksappwebstore.exception.ValidationException;
import sky.pro.socksappwebstore.model.Color;
import sky.pro.socksappwebstore.model.Size;
import sky.pro.socksappwebstore.model.Socks;
import sky.pro.socksappwebstore.model.SocksBatch;
import sky.pro.socksappwebstore.services.FileSocksService;
import sky.pro.socksappwebstore.services.SocksService;
import sky.pro.socksappwebstore.services.ValidationService;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {

    private final FileSocksService fileSocksService;
    private final ValidationService validationService;

    @Override
    public void accept(SocksBatch socksBatch) {
        validationService.validate(socksBatch);
        fileSocksService.save(socksBatch);

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
}
