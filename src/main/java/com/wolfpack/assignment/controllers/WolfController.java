package com.wolfpack.assignment.controllers;

import com.wolfpack.assignment.model.Wolf;
import com.wolfpack.assignment.services.WolfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class WolfController {

    @Autowired
    private WolfService wolfService;

    @GetMapping(path = "/wolves")
    public ResponseEntity<List<Wolf>> getWolves() {
        return wolfService.getWolves();
    }

    @PostMapping(path = "/wolves")
    public ResponseEntity addNewWolf(@RequestBody Wolf wolf) {
        Wolf returnWolf = wolfService.addNewWolf(wolf);
        if (returnWolf == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Wolf with the provided name exists!");
        }
        return new ResponseEntity<>(returnWolf, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/wolves/{wolfId}")
    public ResponseEntity deleteWolf(@PathVariable("wolfId") Long wolfId) {
        return (wolfService.deleteWolf(wolfId) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wolf with ID " + wolfId + "does not exist!"));
    }


    @PutMapping(path = "wolves/{wolfId}")
    public ResponseEntity<Wolf> updateWolf(@PathVariable("wolfId") long wolfId, @RequestBody Wolf wolf) {
        Optional<Wolf> savedWolf = wolfService.updateWolf(wolfId, wolf);
        if (savedWolf.isPresent())
            return new ResponseEntity(savedWolf, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

