package com.wolfpack.assignment.controllers;

import com.wolfpack.assignment.model.Wolf;
import com.wolfpack.assignment.repositories.WolfRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class WolfControllerTest {
    @Autowired
    private WolfController wolfController;

    @MockBean
    private WolfRepository wolfRepository;

    Wolf[] wolves = new Wolf[]{new Wolf(1L, "Rick",
            "Male",
            LocalDate.of(1995, 01, 5),
            1.2f,
            2.3f), new Wolf(2L, "Jordan",
            "Male",
            LocalDate.of(1973, 3, 5),
            1.2f,
            2.3f)};

    List<Wolf> Wolves = Arrays.asList(wolves);

    @Test
    void getWolves() {
        Mockito.when(wolfRepository.findAll()).thenReturn(Wolves);
        List<Wolf> actualWolves = wolfRepository.findAll();
        assertEquals(Wolves, actualWolves);
    }

    @Test
    void addNewWolf() {
        Wolf wolf = Wolves.get(1);
        Mockito.when(wolfRepository.save(wolf)).thenReturn(wolf);
        ResponseEntity<?> response = wolfController.addNewWolf(Wolves.get(1));
        assertEquals(wolf, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Mockito.verify(wolfRepository).save(wolf);
    }

    @Test
    void deleteWolf() {
        Wolf wolf = Wolves.get(1);
        Mockito.when(wolfRepository.findById(wolf.getId())).thenReturn(java.util.Optional.of(wolf));
        wolfRepository.delete(wolf);
        Mockito.verify(wolfRepository).delete(wolf);
    }

    @Test
    void updateWolf() {
        Wolf wolf1 = Wolves.get(0);
        Wolf updatedWolf = new Wolf(3L, "UpdatedRick",
                "UpdatedMale",
                LocalDate.of(1995, 01, 5),
                1.2f,
                2.3f);
        Wolf wolf = wolfRepository.findById(wolf1.getId()).get();
        wolf.setName(updatedWolf.getName());
        wolf.setGender(updatedWolf.getGender());
        wolfRepository.save(wolf);
        Wolf checkWolf = wolfRepository.findById(wolf1.getId()).get();
        assertThat(checkWolf.getName()).isEqualTo(wolf1.getName());
        assertThat(checkWolf.getGender()).isEqualTo(updatedWolf.getGender());
    }
}