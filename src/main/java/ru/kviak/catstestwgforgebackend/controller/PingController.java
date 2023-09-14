package ru.kviak.catstestwgforgebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kviak.catstestwgforgebackend.model.Cat;
import ru.kviak.catstestwgforgebackend.service.CatService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PingController {

    private final CatService catService;
    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("Cats Service. Version 0.1");
    }

    @GetMapping("/cats")
    public ResponseEntity<List<Cat>> getCats(@RequestParam(defaultValue = "name", name = "attribute") String attribute,
                                             @RequestParam(defaultValue = "desc",name = "order") String order,
                                             @RequestParam(defaultValue = "0",name = "offset") int offset,
                                             @RequestParam (defaultValue = "100",name ="limit") int limit){
        return ResponseEntity.ok(catService.getAllCats(attribute, order, offset, limit));
    }

    @PostMapping("/cat")
    public ResponseEntity<?> addCat(@RequestBody Cat cat){
        catService.saveCat(cat);
        return ResponseEntity.ok("New Cat Added!");
    }
}
