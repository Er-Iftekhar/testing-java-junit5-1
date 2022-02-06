package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService visitSDJpaService;

    Set<Visit> visits;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findAll() {

//        given
        Visit visit1 = new Visit(1L);
        Visit visit2 = new Visit(2L);
        visits = new HashSet<>();
        visits.add(visit1);
        visits.add(visit2);

//        when
        given(visitRepository.findAll()).willReturn(visits);


//        then
        Set<Visit> visitsFound = visitSDJpaService.findAll();
        assertNotNull(visitsFound);
        then(visitRepository).should(times(1)).findAll();
    }

    @Test
    void findById() {
        //given
        Visit visit1 = new Visit(1L);
        given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit1));
//        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visits.stream().findFirst().get()));

//        when
        Visit visitFoundById = visitSDJpaService.findById(anyLong());
//        then
        assertNotNull(visitFoundById);
        then(visitRepository).should(times(1)).findById(anyLong());
    }

    @Test
    void save() {
//        given
        Visit testVisit = new Visit();
        given(visitRepository.save(testVisit)).willReturn(testVisit);
//        when
//        when(visitRepository.save(any(Visit.class))).thenReturn(testVisit);
        Visit savedVisit = visitSDJpaService.save(testVisit);
//        then
        assertNotNull(savedVisit);
        then(visitRepository).should(times(1)).save(testVisit);
    }

    @Test
    void delete() {
        //given
        Visit testVisit = new Visit(1L);
        //when
        visitSDJpaService.delete(testVisit);
//        then
        then(visitRepository).should().delete(any(Visit.class));
//        verify(visitRepository, atLeastOnce()).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
//        when
        visitSDJpaService.deleteById(1L);
//        then
        then(visitRepository).should(times(1)).deleteById(anyLong());
    }
}