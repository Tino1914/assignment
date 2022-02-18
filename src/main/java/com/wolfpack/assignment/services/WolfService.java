package com.wolfpack.assignment.services;

import com.wolfpack.assignment.model.Wolf;
import com.wolfpack.assignment.repositories.WolfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WolfService {

    @Autowired
    private WolfRepository wolfRepository;


    public ResponseEntity<List<Wolf>> getWolves() {
        List<Wolf> wolves = new ArrayList<Wolf>();
        wolfRepository.findAll().forEach(wolves::add);
        if (wolves.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(wolves, HttpStatus.OK);
    }

    public Wolf addNewWolf(Wolf wolf) {
        Optional<Wolf> wolfOptional = wolfRepository.findWolfByName(wolf.getName());
        if (wolfOptional.isPresent()) {
            return null;
        }
        return wolfRepository.save(wolf);
    }

    public boolean deleteWolf(Long wolfId) {
        boolean exists = wolfRepository.existsById(wolfId);
        //check for the wolf before deleting
        if (exists) wolfRepository.deleteById(wolfId);
        return exists;
    }


    public Optional<Wolf> updateWolf(long id, Wolf wolf) {
        Optional<Wolf> wolfOptional = wolfRepository.findById(id);
        if (wolfOptional.isPresent()) {
            Wolf _wolf = wolfOptional.get();
            _wolf.setName(wolf.getName());
            _wolf.setGender(wolf.getGender());
            _wolf.setDateOfBirth(wolf.getDateOfBirth());
            _wolf.setLongitude(wolf.getLongitude());
            _wolf.setLatitude(wolf.getLatitude());

            //save the updated wolf
            return Optional.ofNullable(wolfRepository.save(_wolf));
        } else {
            return null;
        }
    }
}
