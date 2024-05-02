package edu.iu.jsinnett.finalproject.controllers;


import edu.iu.jsinnett.finalproject.model.Flower;
import edu.iu.jsinnett.finalproject.repository.FlowerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/flowers")
public class FlowerController {

    private final FlowerRepository flowerRepository;

    public FlowerController(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
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
}
