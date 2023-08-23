package com.workintech.zoo.controller;


import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import com.workintech.zoo.exceptions.ZooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koala")
public class KoalaController {
    private Map<Integer, Koala> koalas;
    @PostConstruct
    public void init(){
        koalas = new HashMap<>();
        //get i bos gelmesin diye kangroos.put(id,kangaroo)
    }
    @GetMapping("/")
    public List<Koala> get(){
        return koalas.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Koala getById(@PathVariable int id){
        ZooValidation.isIdNotValid(id);
        ZooValidation.doesKoalaNotExists(koalas,id);
        return koalas.get(id);
    }

    @PostMapping("/")
    public Koala save (@RequestBody Koala koala){
        ZooValidation.isIdNotValid(koala.getId());
        ZooValidation.doesKoalaExists(koalas, koala.getId());
        ZooValidation.isKoalaNotValid(koala);
        koalas.put(koala.getId(),koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala update (@PathVariable int id,@RequestBody Koala koala){
        ZooValidation.doesKoalaNotExists(koalas,id);
        ZooValidation.isKoalaNotValid(koala);
        koalas.put(id,koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala delete (@PathVariable int id){
        ZooValidation.doesKoalaNotExists(koalas,id);
        Koala deletedKoala = koalas.get(id);
        koalas.remove(id);
        return deletedKoala;
    }



}
