package sky.pro.socksappwebstore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.socksappwebstore.exception.ValidationException;
import sky.pro.socksappwebstore.services.FilesService;
import sky.pro.socksappwebstore.services.SocksService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


@Tag(name = "Контроллер export/import", description = "CRUD операции и другие эгдпоинты для выгрузки и загрузки файлов")
@RestController
@AllArgsConstructor
@RequestMapping("/files")
public class FilesController {
    private final FilesService filesService;
    private final SocksService socksService;

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Всё хорошо, запрос выполнился",
            content = {@Content(mediaType = "application/json")})})
    @Operation(method = "export файла рецепта формат json.", summary = "Можете загрузить (принять) файл формат json",
            description = "Можно получить файл")
    @GetMapping(value = "/export-socks")
    public ResponseEntity<InputStreamResource> downloadSocksFile() throws FileNotFoundException {
        File file = filesService.getSocksFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"SocksLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Всё хорошо, запрос выполнился",
            content = {@Content(mediaType = "application/json")})})
    @Operation(method = "import файла рецепта.", summary = "Можете выгрузить (отправить) файл",
            description = "Можно отправить файл")
    @PostMapping(value = "/import-socks", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadSocksFile(@RequestParam MultipartFile file) {
        filesService.deleteFile();
        File recipesFile = filesService.getSocksFile();
        try (FileOutputStream fos = new FileOutputStream(recipesFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new ValidationException("Ошибка при выгрузке файла / uploadSocksFile ");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Всё хорошо, запрос выполнился")})
    @Operation(method = "export файла рецепта формат JSON.", summary = "Можете загрузить (принять) файл в формате json",
            description = "Можно получить файл в формате JSON")
    @GetMapping(value = "/export-allsocks")
    public ResponseEntity<InputStreamResource> dowloadAllSocksFileJson() throws FileNotFoundException {
        File file = filesService.getSocksFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"НоскиLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Всё хорошо, запрос выполнился",
            content = {@Content(mediaType = "text/plain")})})
    @Operation(method = "Данные всех рецептов в формате txt.",
            summary = "Данные всех рецептов в формате txt, можете загрузить (принять) файл",
            description = "Можно получить (принять) данные в формате txt")
    @GetMapping("/export-AllSocks")
    public ResponseEntity<Object> getAllSoocksExport() {
        try {
            Path path = socksService.createAllSocks();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"SocksAll.txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());

        }
    }
}
