package edu.iu.jsinnett.finalproject.controllers;


import edu.iu.jsinnett.finalproject.model.Flower;
import edu.iu.jsinnett.finalproject.repository.FlowerRepository;
import edu.iu.jsinnett.finalproject.repository.FlowersFileRepository;
import edu.iu.jsinnett.finalproject.service.ImageStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/flowers")
public class FlowersController {
    FlowersFileRepository flowersFileRepository;
    FlowerRepository flowerRepository;

    public FlowersController(
            FlowersFileRepository flowersFileRepository,
            FlowerRepository flowerRepository) {
        this.flowersFileRepository = flowersFileRepository;
        this.flowerRepository = flowerRepository;
    }

    @PostMapping
    public int add(@RequestBody Flower flower) {
        Flower saved = flowerRepository.save(flower);
        return saved.getId();
    }

    @GetMapping
    public Iterable<Flower> findAll() {
        Iterable<Flower> flowers = flowerRepository.findAll();
        return flowers;
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable int id) {
        try {
            byte[] image = flowersFileRepository.getImage(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{id}/image")
    public boolean updateImage(@PathVariable int id,
                               @RequestParam MultipartFile file) {
        try {
            return flowersFileRepository.updateImage(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
