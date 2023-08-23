package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroo")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
    }
    @GetMapping("/")
    public List<Kangaroo> get(){
        return kangaroos.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Kangaroo getById(@PathVariable int id){
        ZooValidation.isIdNotValid(id);
        ZooValidation.doesKangarooNotExists(kangaroos,id);
        return kangaroos.get(id);
    }

    @PostMapping("/")
    public Kangaroo save (@RequestBody Kangaroo kangaroo){
        ZooValidation.isIdNotValid(kangaroo.getId());
        ZooValidation.doesKangarooExists(kangaroos,kangaroo.getId());
        ZooValidation.isKangarooNotValid(kangaroo);
        kangaroos.put(kangaroo.getId(),kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo update (@PathVariable int id,@RequestBody Kangaroo kangaroo){
        ZooValidation.doesKangarooNotExists(kangaroos,id);
        ZooValidation.isKangarooNotValid(kangaroo);
        kangaroos.put(id,kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo delete (@PathVariable int id){
        ZooValidation.doesKangarooNotExists(kangaroos,id);
        Kangaroo deletedKangaroo = kangaroos.get(id);
        kangaroos.remove(id);
        return deletedKangaroo;
    }


}
