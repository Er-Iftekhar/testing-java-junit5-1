package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.*;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_1 = "redirect:/owners/1";
    @Mock
    OwnerService ownerService;
    @Mock
    BindingResult bindingResult;
    @Mock
    Model model;

    @InjectMocks
    OwnerController ownerController;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
                .willAnswer(invocation ->{
                    List<Owner> ownerList = new ArrayList<>();

                    String name = invocation.getArgument(0  );
                    if (name.equals("%Buck%")){
                        ownerList.add(new Owner(5L, "Joe", "Buck"));
                        return ownerList;
                    }else if(name.equals("%DontFindMe%")){
                        return ownerList;
                    }else if (name.equals("%FoundMany%")){
                        ownerList.add(new Owner(5L, "Joe", "Buck"));
                        ownerList.add(new Owner(6L,"Joe","Biden"));
                        return ownerList;
                    }
                    throw new RuntimeException("Invalid Argument");
                });
    }

    @Test
    void processFindFormWildcardStringAnnotation() {
        //given
        Owner owner = new Owner(5L, "Joe", "Buck");
//        List<Owner> ownerList = new ArrayList<>();
//        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);

        //when
        String viewName = ownerController.processFindForm(owner,bindingResult,null);

        //then
        assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/owners/5").isEqualToIgnoringCase(viewName   );
        verifyZeroInteractions(model);
    }

    @Test
    void processFindFormWildcardNotFound() {
        //given
        Owner owner = new Owner(5L, "Joe", "DontFindMe");

        verifyNoMoreInteractions(ownerService);
        //when
        String viewName = ownerController.processFindForm(owner,bindingResult,null);

        //then
        assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(viewName   );
        verifyZeroInteractions(model);


    }

    @Test
    void processFindFormWildcardListFound() {
        //given
        Owner owner = new Owner(5L, "Joe", "FoundMany");
        InOrder inOrder = inOrder(ownerService, model);
        //when
        String viewName = ownerController.processFindForm(owner,bindingResult, model);

        //then
        assertThat("%FoundMany%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName   );

        //inOrder asserts
        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model, times(1)).addAttribute(anyString(), anyList());
        verifyNoMoreInteractions(model);
    }

    @Test
    void processFindFormWithBindingError() {
        //given
        Owner owner = new Owner(5L, "Shane", null);
        List<Owner> ownerList = new ArrayList<>();
//        ownerList.add(owner);
//        given(ownerService.findAllByLastNameLike(anyString())).willReturn(ownerList);
        given(bindingResult.hasErrors()).willReturn(true);

        //when
        String view = ownerController.processCreationForm(owner, bindingResult);
        assertEquals("owners/findOwners", view);
        //then
        then(ownerService).should().findAllByLastNameLike(anyString());
    }

    @Test
    void processFindFormWithNoBindingError() {
        //given
        Owner owner = new Owner(5L, "Shane", null);
        List<Owner> ownerList = new ArrayList<>();
        ownerList.add(owner);
        given(ownerService.findAllByLastNameLike(anyString())).willReturn(ownerList);

        //when
        String view = ownerController.processFindForm(owner,bindingResult, new ModelMapImpl());
        assertEquals("redirect:/owners/5", view);
        //then
        then(ownerService).should().findAllByLastNameLike(anyString());
    }

    @Test
    void processFindFormWithNoBindingErrorWithMultipleOwner() {
        //given
        Owner owner1 = new Owner(5L, "Shane", null);
        Owner owner2 = new Owner(6L, "Jonty", null);
        List<Owner> ownerList = new ArrayList<>();
        ownerList.add(owner1);
        ownerList.add(owner2);
        given(ownerService.findAllByLastNameLike(anyString())).willReturn(ownerList);

        //when
        String view = ownerController.processFindForm(owner1, bindingResult, new ModelMapImpl());
        assertEquals("owners/ownersList", view);
        //then
        then(ownerService).should().findAllByLastNameLike(anyString());
    }

    @Test
    void testProcessCreationFormWithErrors() {
        //given
        Owner owner = new Owner(1L, "Shane","Bond");
        given(bindingResult.hasErrors()).willReturn(true);
        //when
        String view = ownerController.processCreationForm(owner, bindingResult);
        //then
        assertEquals(OWNERS_CREATE_OR_UPDATE_OWNER_FORM,view);

    }

    @Test
    void testProcessCreationFormWithoutErrors() {
        //given
        Owner owner = new Owner(1L, "Shane","Bond");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(owner)).willReturn(any());
        //when
        String view = ownerController.processCreationForm(owner, bindingResult);
        //then
        assertEquals(REDIRECT_OWNERS_1,view);
        then(ownerService).should().save(any());
    }
}