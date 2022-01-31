package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test class with Nested tests")
class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    PetTypeService petTypeService;
    PetService petService;


    @BeforeEach
    void setUp() {
        System.out.println("Outer before each method");
         petTypeService = new PetTypeMapService();
         petService = new PetMapService();
         ownerMapService = new OwnerMapService(petTypeService, petService);
    }

    @DisplayName("Verify Zero Owners")
    @Test
    public void ownersAreZero() {
        int ownerCount = ownerMapService.findAll().size();
        assertThat(ownerCount).isZero();
    }

    @DisplayName("Pet Type--")
    @Nested
    class TestCreatePetTypes{
        @BeforeEach
        void setUp() {
            System.out.println("First Nested Before each");
            PetType petType1 = new PetType(1L,"Dog");
            PetType petType2 = new PetType(2L,"Cat");
            petTypeService.save(petType1);
            petTypeService.save(petType2);
        }

        @Test
        void testPetCount() {
            int petTypeCount = petTypeService.findAll().size();
            assertThat(petTypeCount).isNotZero().isEqualTo(2);
        }
    }

    @DisplayName("Save Owners Tests")
    @Nested
    class SaveOwnersTests{
        @BeforeEach
        void setUp() {
            System.out.println("Second Nested Before each");
            Owner owner1 = new Owner(1L, "Joe", "Smith");

            ownerMapService.save(owner1);

        }

        @Test
        void testOwnerCount() {
            Owner owner2 = new Owner(2L, "Hilary", "Clinton");
            ownerMapService.save(owner2);
            int ownerCount = ownerMapService.findAll().size();
            assertThat(ownerCount).isNotZero().isEqualTo(2);
        }

        @DisplayName("find Saved Owner")
        @Test
        void findSavedOwner() {
            Owner ownerById = ownerMapService.findById(2L);
            assertThat(ownerById).isNull();

        }
    }

    @DisplayName("Verify owners still zero")
    @Test
    void verifyOwnersStillZero() {
        int ownersCount = ownerMapService.findAll().size();
        assertThat(ownersCount).isZero();
    }
}