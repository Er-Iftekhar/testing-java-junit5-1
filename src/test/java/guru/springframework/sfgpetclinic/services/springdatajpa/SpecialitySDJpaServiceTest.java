package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
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
    void findByIdTest() {
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
        //given
        Speciality speciality = new Speciality();
        //when
        specialitySDJpaService.delete(speciality);
        //then
        then(specialtyRepository).should(times(1)).delete(any(Speciality.class));
//        verify below does the same as then above
//        verify(specialtyRepository).delete(any(Speciality.class));
    }



    @Test
    void testDeleteById() {
        //given - none
        //when
        specialitySDJpaService.deleteById(  1L);
        specialitySDJpaService.deleteById(  1L);
        //then
        then(specialtyRepository).should(times(2)).deleteById(1L);
//        verify below does the same as then above
//        verify(specialtyRepository, times(2)).deleteById(1L);
    }

    @Test
    void testDeleteByIdAtLeastOnce() {
//        given - none
//        when
        specialitySDJpaService.deleteById(  1L);
//        then
        then(specialtyRepository).should(atLeastOnce()).deleteById(anyLong());
//        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void testDeleteByIdAtMost() {
//        given
//        when
        specialitySDJpaService.deleteById(  1L);
//        then
        then(specialtyRepository).should(atMost(5)).deleteById(1L);
//        verify(specialtyRepository, atMost(5)).deleteById(1L);
    }
    @Test
    void testDeleteByIdNever() {
//        given
//        when
        specialitySDJpaService.deleteById(  1L);
//        then
//        then(specialtyRepository).should(never()).deleteById(anyLong());
//        verify(specialtyRepository, never()).deleteById(1L);
    }

    @Test
    void testDelete()
    {
        //given
        Speciality speciality = new Speciality();
        //when
        specialitySDJpaService.delete(speciality);
        //then
        then(specialtyRepository).should().delete(speciality);
    }
}