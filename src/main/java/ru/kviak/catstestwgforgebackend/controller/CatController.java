package ru.kviak.catstestwgforgebackend.controller;

import com.google.common.util.concurrent.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kviak.catstestwgforgebackend.model.Cat;
import ru.kviak.catstestwgforgebackend.service.CatService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;
    private final RateLimiter rateLimiter = RateLimiter.create(10);

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        rateLimiter.acquire(1);
        return ResponseEntity.ok("Cats Service. Version 0.1");
    }
    @GetMapping("/cats")
    public ResponseEntity<List<Cat>> getCats(@RequestParam(defaultValue = "name", name = "attribute") String attribute,
                                             @RequestParam(defaultValue = "desc",name = "order") String order,
                                             @RequestParam(defaultValue = "0",name = "offset") int offset,
                                             @RequestParam (defaultValue = "100",name ="limit") int limit){
        rateLimiter.acquire(1);
        return ResponseEntity.ok(catService.getAllCats(attribute, order, offset, limit));
    }

    @PostMapping("/cat")
    public ResponseEntity<?> addCat(@RequestBody Cat cat){
        catService.saveCat(cat);
        return ResponseEntity.ok("New Cat Added!");
    }
}
