package sky.pro.socksappwebstore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/files")
public class FilesController {
    private final FilesService filesService;
    private final SocksService socksService;

    public FilesController(FilesService filesService, SocksService socksService) {
        this.filesService = filesService;
        this.socksService = socksService;
    }

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
            throw new ValidationException("Ошибка при выгрузке файла / uploadRecipeFile() ");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        try (BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
//             FileOutputStream fos = new FileOutputStream(recipesFile);
//             BufferedOutputStream bos = new BufferedOutputStream(fos);) {
//            byte[]buffer = new byte[1024];
//            while (bis.read(buffer)>0){
//                bos.write(buffer);
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Всё хорошо, запрос выполнился",
            content = {@Content(mediaType = "application/json")})})
    @Operation(method = "export файла ингредиента формат json.", summary = "Можете загрузить (принять) файл формат json",
            description = "Можно получить файл")
    @GetMapping(value = "/export-allsocks")
    public ResponseEntity<InputStreamResource> dowloadAllSocksFile() throws FileNotFoundException {
        File file = filesService.getAllsocksFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"AllSocksLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Всё хорошо, запрос выполнился",
            content = {@Content(mediaType = "application/json")})})
    @Operation(method = "import файла ингредиента.", summary = "Можете выгрузить (отправить) файл",
            description = "Можно отправить файл")
    @PostMapping(value = "/import-allsocks", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadAllSocksFile(@RequestParam MultipartFile file) {
        filesService.deleteAllsocksFile();
        File ingredientsFile = filesService.getAllsocksFile();
        try (FileOutputStream fos = new FileOutputStream(ingredientsFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new ValidationException("Ошибка при выгрузке файла / uploadIngredientsFile ");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Всё хорошо, запрос выполнился",
            content = {@Content(mediaType = "plain/txt")})})
    @Operation(method = "export файла рецепта формат txt.", summary = "Можете загрузить (принять) файл в формате txt",
            description = "Можно получить файл в формате txt")
    @GetMapping(value = "/export-allsockstxt")
    public ResponseEntity<InputStreamResource> dowloadAllSocksFileTXT() throws FileNotFoundException {
        File file = filesService.getSocksFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"AllSocksTXTLog.txt\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Всё хорошо, запрос выполнился")})
    @Operation(method = "Данные всех рецептов в формате JSON.",
            summary = "Данные всех рецептов в формате JSON, можете загрузить (принять) файл",
            description = "Можно получить (принять) данные в формате JSON")
    @GetMapping("/AllRecipes")
    public ResponseEntity<Object> getAllRecipesExport() {
        try {
            Path path = socksService.createAllRecipes();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"AllRecipes.json\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}