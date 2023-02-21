package sky.pro.socksappwebstore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.socksappwebstore.dto.ResponseDTO;
import sky.pro.socksappwebstore.model.Color;
import sky.pro.socksappwebstore.model.Size;
import sky.pro.socksappwebstore.model.Socks;
import sky.pro.socksappwebstore.model.SocksBatch;

import sky.pro.socksappwebstore.services.SocksService;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Tag(name = "Контроллер управления перемещения носков", description = "POST PUT GET DELETE")
@RestController
@RequestMapping("/api/socks")
public class SocksStoreController {
    private final SocksService socksService;


    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "удалось добавить приход"),
            @ApiResponse(responseCode = "500",
                    description = "произошла ошибка, не зависящая от вызывающей стороны"),
            @ApiResponse(responseCode = "400",
                    description = "параметры запроса отсутствуют или имеют некорректный формат")})
    @Operation(method = "на склад можно добавить новый товар", summary = "на склад можно добавить новый товар",
            description = "на склад можно добавить новый товар")
    @PostMapping
    public ResponseEntity<ResponseDTO> postSocksContr(@RequestBody SocksBatch socksBatch) {
        socksService.accept(socksBatch);
        return ResponseEntity.ok(new ResponseDTO(" Носки успешно добавлены на склад"));
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = " удалось произвести отпуск носков со склада"),
            @ApiResponse(responseCode = "500",
                    description = "произошла ошибка, не зависящая от вызывающей стороны"),
            @ApiResponse(responseCode = "400",
                    description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат")})
    @Operation(method = "можно забрать товар со склада", summary = "можно забрать товар со склада",
            description = "можно забрать товар со склада")
    @PutMapping
    public ResponseEntity<ResponseDTO> putSocksContr(@RequestBody SocksBatch socksBatch) {
        int socksCount = socksService.issuence(socksBatch);
        return ResponseEntity.ok(new ResponseDTO("Успешно забрано на складе " + socksCount + " пар носков"));
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "запрос выполнен, результат в теле ответа в виде строкового представления целого числа"),
            @ApiResponse(responseCode = "500",
                    description = "произошла ошибка, не зависящая от вызывающей стороны"),
            @ApiResponse(responseCode = "400",
                    description = "параметры запроса отсутствуют или имеют некорректный формат")})
    @Operation(method = "можно получить данные о товаре на складке: общее количество и данные по составу",
            summary = "можно получить данные о товаре на складке: общее количество и данные по составу",
            description = "можно получить данные о товаре на складке: общее количество и данные по составу")
    @GetMapping
    public ResponseEntity<ResponseDTO> getSocksContr(@RequestParam Color color, @RequestParam Size size,
                                                     @RequestParam int cottonMin, @RequestParam int cottonMax) {
        return ResponseEntity.ok(new ResponseDTO("Количество носков на складе: " +
                socksService.getCount(color, size, cottonMin, cottonMax) + " пар"));
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "запрос выполнен, товар списан со склада"),
            @ApiResponse(responseCode = "500",
                    description = "произошла ошибка, не зависящая от вызывающей стороны"),
            @ApiResponse(responseCode = "400",
                    description = "параметры запроса отсутствуют или имеют некорректный формат")})
    @Operation(method = "со склада можно списать бракованный товар",
            summary = "со склада можно списать бракованный товар",
            description = "со склада можно списать бракованный товар")
    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteSocksContr(@RequestBody SocksBatch socksBatch) {
        int socksCounts = socksService.reject(socksBatch);
        return ResponseEntity.ok(new ResponseDTO("Удалено со склада: " + socksCounts + " пар носков"));
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Всё хорошо, запрос выполнился"),
            @ApiResponse(responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере"),
            @ApiResponse(responseCode = "400",
                    description = "Есть ошибка в параметрах запроса")})
    @Operation(method = "Все носки на  складе",
            summary = "Все носки на  складе",
            description = "Все носки на  складе")
    @GetMapping("/allSocks")
    public ResponseEntity<Map<Socks, Integer>> getAll() {
        return ResponseEntity.ok(socksService.getAll());
    }
}
