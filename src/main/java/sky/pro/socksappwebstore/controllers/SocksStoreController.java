package sky.pro.socksappwebstore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.socksappwebstore.model.Color;
import sky.pro.socksappwebstore.model.Size;
import sky.pro.socksappwebstore.model.SocksBatch;

@Tag(name = "Контроллер управления перемещения носков", description = "POST PUT GET DELETE")
@RestController
@RequestMapping("/api/socks")
public class SocksStoreController {
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Всё хорошо, запрос выполнился"),
            @ApiResponse(responseCode = "404",
                    description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере"),
            @ApiResponse(responseCode = "400",
                    description = "Есть ошибка в параметрах запроса")})
    @Operation(method = "на склад можно добавить новый товар", summary = "на склад можно добавить новый товар",
            description = "на склад можно добавить новый товар")
    @PostMapping
    public ResponseEntity<Void> postSocksContr(@RequestBody SocksBatch socksBatch) {
        return null;
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Всё хорошо, запрос выполнился"),
            @ApiResponse(responseCode = "404",
                    description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере"),
            @ApiResponse(responseCode = "400",
                    description = "Есть ошибка в параметрах запроса")})
    @Operation(method = "можно забрать товар со склада", summary = "можно забрать товар со склада",
            description = "можно забрать товар со склада")
    @PutMapping
    public ResponseEntity<Void> putSocksContr(@RequestBody SocksBatch socksBatch) {
        return null;
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Всё хорошо, запрос выполнился"),
            @ApiResponse(responseCode = "404",
                    description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере"),
            @ApiResponse(responseCode = "400",
                    description = "Есть ошибка в параметрах запроса")})
    @Operation(method = "можно получить данные о товаре на складке: общее количество и данные по составу",
            summary = "можно получить данные о товаре на складке: общее количество и данные по составу",
            description = "можно получить данные о товаре на складке: общее количество и данные по составу")
    @GetMapping
    public ResponseEntity<Void> getSocksContr(@RequestParam String socks, @RequestParam Size size,
                                              @RequestParam int cottonMin, @RequestParam int cottonMax) {
        return null;
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Всё хорошо, запрос выполнился"),
            @ApiResponse(responseCode = "404",
                    description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере"),
            @ApiResponse(responseCode = "400",
                    description = "Есть ошибка в параметрах запроса")})
    @Operation(method = "со склада можно списать бракованный товар",
            summary = "со склада можно списать бракованный товар",
            description = "со склада можно списать бракованный товар")
    @DeleteMapping
    public ResponseEntity<Void> deleteSocksContr(@RequestBody SocksBatch socksBatch) {
        return null;
    }

}
