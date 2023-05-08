package by.babanin.ext.export;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JsonFileImporter<T> implements Importer<T> {

    private final JsonMapper jsonMapper;
    private final Path filePath;
    private final Class<T> type;

    public JsonFileImporter(JsonMapper jsonMapper, Path filePath, Class<T> type) {
        this.jsonMapper = jsonMapper;
        this.filePath = filePath;
        this.type = type;
    }

    @Override
    public T doImport() {
        log.info("Reading preferences from {}", filePath);
        try {
            if(Files.exists(filePath)) {
                return jsonMapper.readValue(filePath.toFile(), type);
            }
        }
        catch(IOException e) {
            throw new ImportException(e);
        }
        return null;
    }
}
