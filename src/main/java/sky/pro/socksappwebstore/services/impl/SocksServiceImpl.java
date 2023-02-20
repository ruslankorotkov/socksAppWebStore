package sky.pro.socksappwebstore.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sky.pro.socksappwebstore.model.Color;
import sky.pro.socksappwebstore.model.Size;
import sky.pro.socksappwebstore.model.Socks;
import sky.pro.socksappwebstore.model.SocksBatch;
import sky.pro.socksappwebstore.services.FileSocksService;
import sky.pro.socksappwebstore.services.SocksService;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final FileSocksService fileSocksService;

    @Override
    public void accept(SocksBatch socksBatch) {
        fileSocksService.save(socksBatch);

    }

    @Override
    public int issuence(SocksBatch socksBatch) {
        return fileSocksService.remove(socksBatch);
    }

    @Override
    public int reject(SocksBatch socksBatch) {
        return fileSocksService.remove(socksBatch);
    }

    @Override
    public int getCount(Color color, Size size, int cottonMin, int cottonMax) {
        Map<Socks, Integer> socksMap = fileSocksService.getAll();
        for (Map.Entry<Socks, Integer> element : socksMap.entrySet()) {
            Socks socks = element.getKey();
            if (socks.getColor().equals(color)
                    && socks.getSize().equals(size)
                    && socks.getCottonPart() >= cottonMax
                    && socks.getCottonPart() <= cottonMin) ;
            {
                return element.getValue();
            }
        }
        return 0;
    }
}
