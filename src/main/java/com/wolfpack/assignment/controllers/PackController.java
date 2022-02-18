package com.wolfpack.assignment.controllers;

import com.wolfpack.assignment.model.Pack;
import com.wolfpack.assignment.model.Wolf;
import com.wolfpack.assignment.exceptions.ResourceNotFoundException;
import com.wolfpack.assignment.repositories.PackRepository;
import com.wolfpack.assignment.repositories.WolfRepository;
import com.wolfpack.assignment.services.PackService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(value = "test1", produces = "application/json")
public class PackController {

    @Autowired
    private PackService packService;
    @Autowired
    PackRepository packRepository;
    @Autowired
    WolfRepository wolfRepository;

    @GetMapping("/packs")
    public ResponseEntity<List<Pack>> getAllPacks() {
        return packService.getPacks();
    }

    @PostMapping("/packs/{firstWolfId}")
    public ResponseEntity<Pack> createPack(@RequestBody Pack pack, @PathVariable Long firstWolfId) {
        Optional<Pack> createdPack = packService.addNewPack(pack, firstWolfId);
        return createdPack.isEmpty() ?
                new ResponseEntity<>(null, HttpStatus.CONFLICT) :
                new ResponseEntity<>(createdPack.get(), HttpStatus.CREATED);
    }

    @PostMapping("/packs/{packId}/wolves/{wolfId}")
    public ResponseEntity<Wolf> addWolf(@PathVariable(value = "packId") Long packId, @PathVariable Long wolfId) {
        Wolf addedWolf = packService.addWolfToPack(packId, wolfId);
        return new ResponseEntity<>(addedWolf, HttpStatus.CREATED);
    }

    @DeleteMapping("/packs/{packId}/wolves/{wolfId}")
    public ResponseEntity<HttpStatus> deleteWolfFromPack(@PathVariable(value = "packId") Long packId, @PathVariable(value = "wolfId") Long wolfId) {
        Pack packToUpdate = packRepository.findById(packId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Pack with id = " + packId));
        packToUpdate.removeWolf(wolfId);
        packRepository.save(packToUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
