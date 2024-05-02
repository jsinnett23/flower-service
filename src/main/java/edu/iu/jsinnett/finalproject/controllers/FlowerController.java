package edu.iu.jsinnett.finalproject.controllers;


import edu.iu.jsinnett.finalproject.model.Flower;
import edu.iu.jsinnett.finalproject.repository.FlowerRepository;
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
public class FlowerController {

    private final FlowerRepository flowerRepository;
    private final ImageStorageService imageStorageService;
    public FlowerController(FlowerRepository flowerRepository, ImageStorageService imageStorageService) {
        this.flowerRepository = flowerRepository;
        this.imageStorageService = imageStorageService;
    }


    @PostMapping
    public ResponseEntity<Flower> addFlower(@RequestBody Flower flower) {
        Flower savedFlower = flowerRepository.save(flower);
        return new ResponseEntity<>(savedFlower, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Flower>> getAllFlowers() {
        return new ResponseEntity<>(flowerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flower> getFlowerById(@PathVariable int id) {
        return flowerRepository.findById(id)
                .map(flower -> new ResponseEntity<>(flower, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<?> uploadImage(@PathVariable int id, @RequestParam("image") MultipartFile file) {
        try {
            imageStorageService.store(id, file);
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        try {
            Flower flower = flowerRepository.findById(id).orElseThrow();
            byte[] image = imageStorageService.loadAsResource(id, flower.getName());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
