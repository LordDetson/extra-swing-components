package by.babanin.ext.export;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JsonFileExporter<T> implements Exporter<T> {

    private final JsonMapper jsonMapper;
    private final Path filePath;

    public JsonFileExporter(JsonMapper jsonMapper, Path filePath) {
        this.jsonMapper = jsonMapper;
        this.filePath = filePath;
    }

    @Override
    public void doExport(T object) {
        log.info("Writing to {}", filePath);
        try {
            write(object, jsonMapper, filePath);
        }
        catch(IOException e) {
            throw new ExportException(e);
        }
    }

    public static <T> void write(T object, JsonMapper jsonMapper, Path filePath) throws IOException {
        Path parent = filePath.getParent();
        if(Files.notExists(parent)) {
            Files.createDirectories(parent);
        }
        Path tempFilePath = Files.createTempFile(parent, filePath.getFileName().toString(), "");
        try {
            jsonMapper.writeValue(tempFilePath.toFile(), object);
            Files.deleteIfExists(filePath);
            Files.move(tempFilePath, filePath);
        }
        catch(IOException e) {
            Files.deleteIfExists(tempFilePath);
            throw e;
        }
    }
}
