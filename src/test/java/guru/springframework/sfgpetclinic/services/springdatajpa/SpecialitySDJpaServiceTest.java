package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService specialitySDJpaService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testDeleteById() {
        specialitySDJpaService.deleteById(  1L);
        verify(specialtyRepository, times(2)).deleteById(1L);
    }

    @Test
    void testDeleteByIdAtLeastOnce() {
        specialitySDJpaService.deleteById(  1L);
        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void testDeleteByIdAtMost() {
        specialitySDJpaService.deleteById(  1L);
        verify(specialtyRepository, atMost(5)).deleteById(1L);
    }
    @Test
    void testDeleteByIdNever() {
        specialitySDJpaService.deleteById(  1L);
        verify(specialtyRepository, never()).deleteById(1L);
    }

    @Test
    void testDelete() {
        specialitySDJpaService.delete(new Speciality());
    }
}