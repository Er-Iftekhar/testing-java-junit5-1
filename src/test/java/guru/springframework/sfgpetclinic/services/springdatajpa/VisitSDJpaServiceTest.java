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
        Visit visit1 = new Visit(1L);
        Visit visit2 = new Visit(2L);
         visits = new HashSet<>();
        visits.add(visit1);
        visits.add(visit2);
    }

    @Test
    void findAll() {
        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> visitsFound = visitSDJpaService.findAll();
        assertNotNull(visitsFound);
        verify(visitRepository).findAll();
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visits.stream().findFirst().get()));
        Visit visitFoundById = visitSDJpaService.findById(anyLong());
        assertNotNull(visitFoundById);
        verify(visitRepository).findById(anyLong());
    }

    @Test
    void save() {
        Visit testVisit = new Visit();
        when(visitRepository.save(any(Visit.class))).thenReturn(testVisit);
        Visit savedVisit = visitSDJpaService.save(testVisit);
        assertNotNull(savedVisit);
        verify(visitRepository).save(any(Visit.class));
    }

    @Test
    void delete() {
//        when(visitRepository.delete(any(Visit.class)).then(visits.remove(visits.stream().findFirst().get()));
        Visit testVisit = new Visit(1L);
        visitSDJpaService.delete(testVisit);
        verify(visitRepository, atLeastOnce()).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        visitSDJpaService.deleteById(1L);
        verify(visitRepository, atLeastOnce()).deleteById(anyLong());
    }
}