package com.workintech.zoo.exceptions;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ZooValidation {

    public static void isIdNotValid(int id) {
        if (id <= 0) {
            throw new ZooException("Id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }
    public static void doesKangarooNotExists(Map<Integer, Kangaroo> kangaroos, int id) {
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo with given id does not exist: " + id, HttpStatus.NOT_FOUND);
        }
    }

    public static void doesKangarooExists(Map<Integer, Kangaroo> kangaroos, int id) {
        if (kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo with given id is already exist: " + id, HttpStatus.NOT_FOUND);
        }
    }
    public static void isKangarooNotValid(Kangaroo kangaroo) {
        if (kangaroo.getName() == null || kangaroo.getWeight()<15  || kangaroo.getHeight() <15 || kangaroo.getGender().isEmpty() ||(!kangaroo.getGender().equalsIgnoreCase("male")&&!kangaroo.getGender().equalsIgnoreCase("female"))){
            throw new ZooException("Kangaroo credentials are not valid. Please check again", HttpStatus.BAD_REQUEST);
        }
    }

    public static void doesKoalaNotExists(Map<Integer, Koala> koalas, int id) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala with given id does not exist: " + id, HttpStatus.NOT_FOUND);
        }
    }

    public static void doesKoalaExists(Map<Integer, Koala> koalas, int id) {
        if (koalas.containsKey(id)) {
            throw new ZooException("Koala with given id is already exist: " + id, HttpStatus.NOT_FOUND);
        }
    }
    public static void isKoalaNotValid(Koala koala) {
        if (koala.getName() == null || koala.getWeight()<5|| koala.getGender().isEmpty() || koala.getSleepHour() <2  ||(!koala.getGender().equalsIgnoreCase("male")&&!koala.getGender().equalsIgnoreCase("female"))||(!koala.getIsAggressive().equalsIgnoreCase("true")&&!koala.getIsAggressive().equalsIgnoreCase("false"))){
            throw new ZooException("Koala credentials are not valid. Please check again", HttpStatus.BAD_REQUEST);
        }
    }

}
