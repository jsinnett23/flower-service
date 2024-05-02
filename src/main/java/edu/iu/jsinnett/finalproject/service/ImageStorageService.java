package edu.iu.jsinnett.finalproject.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageStorageService {
    private final Path rootLocation = Paths.get("src/main/resources/static/images");

    public ImageStorageService() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage location", e);
        }
    }

    public void store(int id, MultipartFile file) throws IOException {
        String filename = id + "_" + file.getOriginalFilename();
        Path destinationFile = this.rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
        file.transferTo(destinationFile);
    }

    public byte[] loadAsResource(int id, String filename) throws IOException {
        Path file = rootLocation.resolve(id + "_" + filename);
        return Files.readAllBytes(file);
    }
}
