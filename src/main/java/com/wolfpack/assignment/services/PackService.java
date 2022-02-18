package com.wolfpack.assignment.services;

import com.wolfpack.assignment.exceptions.ResourceNotFoundException;
import com.wolfpack.assignment.model.Pack;
import com.wolfpack.assignment.model.Wolf;
import com.wolfpack.assignment.repositories.PackRepository;
import com.wolfpack.assignment.repositories.WolfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PackService {
    private final PackRepository packRepository;

    @Autowired
    public PackService(PackRepository packRepository) {
        this.packRepository = packRepository;
    }

    @Autowired
    WolfRepository wolfRepository;

    public Optional<Pack> addNewPack(Pack pack, Long firstWolfId) {
        Optional<Pack> packOptional = packRepository.findPackByPackName(pack.getPackName());
        //check if pack is existing
        if (packOptional.isPresent()) {
            throw new IllegalStateException("Pack already existing");
        }
        // Look for the wolf
        Optional<Wolf> wolfToAdd = wolfRepository.findById(firstWolfId);

        // Check if the wolf exists
        if (wolfToAdd.isPresent()) {
            // Add wolf to the newly created pack
            Pack packToCreate = new Pack(pack.getPackName());
            packToCreate.addWolf(wolfToAdd.get());
            Pack createdPack = packRepository.save(packToCreate);
            return Optional.of(createdPack);
        } else {
            return Optional.empty();
        }
    }

    public ResponseEntity<List<Pack>> getPacks() {
        List<Pack> packs = new ArrayList<Pack>();
        packRepository.findAll().forEach(packs::add);
        if (packs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(packs, HttpStatus.OK);
    }

    public Wolf addWolfToPack(Long packId, Long wolfId) {
        Optional<Pack> pack = packRepository.findById(packId);
        //check if the pack exists
        if (pack.isEmpty()) {
            throw new ResourceNotFoundException("Such pack does not exist");
        } else {
            //check if wolf is already there
            Optional<Wolf> wolfToAdd = wolfRepository.findById(wolfId);
            Pack packToUpdate = pack.get();
            packToUpdate.addWolf(wolfToAdd.get());
            packRepository.save(packToUpdate);
            return wolfToAdd.get();
        }
    }
}

