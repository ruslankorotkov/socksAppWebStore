package sky.pro.socksappwebstore.services.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import sky.pro.socksappwebstore.exception.ValidationException;
import sky.pro.socksappwebstore.model.Color;
import sky.pro.socksappwebstore.model.Size;
import sky.pro.socksappwebstore.model.SocksBatch;
import sky.pro.socksappwebstore.services.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(SocksBatch socksBatch) {
        try {
            Validate.notBlank(socksBatch.getSocks().getSize().name(),
                    "Ошибка валидации размера / Size");
            Validate.notBlank(socksBatch.getSocks().getColor().name(),
                    "Ошибка валидации цвета / Color");
            Validate.notNull(socksBatch.getSocks().getCottonPart(),
                    "Ошибка валидации содержания хлопка / CottonPart");
            Validate.notNull(socksBatch.getSocks(),
                    "Ошибка валидации класса Socks / Socks");
            Validate.validState(socksBatch.getTotalQuantity() > 0,
                    "Ошибка валидации количества носков (должно быть больше 0) / TotalQuantity");
            Validate.validState(checkCotton(socksBatch.getSocks().getCottonPart(),
                    socksBatch.getSocks().getCottonPart()));
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean validate(Color color, Size size, int cottonMin, int cottonMax) {
        try {
            Validate.notBlank(size.name(),
                    "Ошибка валидации размера / Size");
            Validate.notBlank(color.name(),
                    "Ошибка валидации цвета / Color");
            Validate.validState(cottonMin >= 0,
                    "Ошибка валидации минимального содержания хлопка cottonMin >= 0 / cottonMin");
            Validate.validState(cottonMax <= 100,
                    "Ошибка валидации максимального содержания хлопка cottonMax <= 100 / cottonMax");
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
        return true;
    }

    private boolean checkCotton(int cottonMin, int cottonMax) {
        try {
            Validate.validState(cottonMin >= 0,
                    "Ошибка валидации минимального содержания хлопка (метод private boolean checkCotton" +
                            "(int cottonMin, int cottonMax) / cottonMin");
            Validate.validState(cottonMax <= 100,
                    "Ошибка валидации максимального содержания хлопка (метод private boolean checkCotton" +
                            "(int cottonMin, int cottonMax) / cottonMax");
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
        return true;
    }
}
