package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitService;
    @Spy //@Mock
    PetMapService petService;

    @InjectMocks
    VisitController visitController ;

    @Test
    void loadPetWithVisit() {
        //given
        Pet pet12 = new Pet(12L);
        Pet pet3 = new Pet(3L);
        Map<String, Object> petMap = new HashMap<>();
        petService.save(pet12);
        petService.save(pet3);
        given(petService.findById(anyLong())).willCallRealMethod();  // .willReturn(pet);
        //when
        Visit visit = visitController.loadPetWithVisit(pet12.getId(), petMap);

        //then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        then(petService).should().findById(anyLong());

        assertThat(visit.getPet().getId()).isEqualTo(12L);
        verify(petService,times(1)).findById(anyLong());
    }


    @Test
    void loadPetWithVisitStubbing() {
        //given
        Pet pet12 = new Pet(12L);
        Pet pet3 = new Pet(3L);
        Map<String, Object> petMap = new HashMap<>();
        petService.save(pet12);
        petService.save(pet3);
        given(petService.findById(anyLong())).willReturn(pet3);
        //when
        Visit visit = visitController.loadPetWithVisit(pet12.getId(), petMap);

        //then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        then(petService).should().findById(anyLong());

        assertThat(visit.getPet().getId()).isEqualTo(3L);
        verify(petService,times(1)).findById(anyLong());
    }

}