package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService specialitySDJpaService;

    @Test
    void findByIdBddTest() {
        //given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));
        //when
        Speciality foundSpecialityById = specialitySDJpaService.findById(1L);
        //then
        assertThat(foundSpecialityById).isNotNull();
//        verify(specialtyRepository.findById(anyLong()));
        then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).should(times(1)).findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();

    }

    @BeforeEach
    void setUp() {


    }

    @Test
    void testDeleteByObject() {
        Speciality speciality = new Speciality();
        specialitySDJpaService.delete(speciality);
        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void findByIdTest(){
        Speciality speciality = new Speciality();
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));
        Speciality foundSpeciality = specialitySDJpaService.findById(1L);
        assertThat(foundSpeciality).isNotNull();
        verify(specialtyRepository).findById(1L);
    }

    @Test
    void testDeleteById() {
        specialitySDJpaService.deleteById(  1L);
        verify(specialtyRepository, times(1)).deleteById(1L);
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
//        verify(specialtyRepository, never()).deleteById(1L);
    }

    @Test
    void testDelete() {
        specialitySDJpaService.delete(new Speciality());
    }
}