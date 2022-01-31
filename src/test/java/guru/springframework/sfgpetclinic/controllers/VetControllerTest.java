package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.fauxspring.ModelMapImpl;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;

class VetControllerTest {
    private VetService vetService;
    private VetController vetController;
    private Model model;

    @BeforeEach
    void setUp() {
        vetService = new VetMapService(new SpecialityMapService());
        vetController = new VetController(vetService);
        model = new ModelMapImpl();

        //stub for Vet class
        Vet vet1 = new Vet(1L, "Joe", "buck", new HashSet<>(Arrays.asList(new Speciality())));
        Vet vet2 = new Vet(2L, "Jimmy", "Smith", new HashSet<>(Arrays.asList(new Speciality())));
        vetService.save(vet1);
        vetService.save(vet2);
    }

    @Test
    void listVets() {
        assertEquals("vets/index",vetController.listVets(model));
        assertThat("vets/index").isEqualTo(vetController.listVets(model));
        Set modetAttribute = (Set)((ModelMapImpl)model).getMap().get("vets");
        assertThat(modetAttribute.size()).isEqualTo(2);
    }
}