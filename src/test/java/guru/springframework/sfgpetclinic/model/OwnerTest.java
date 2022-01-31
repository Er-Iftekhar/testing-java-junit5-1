package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1L, "Joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("1234567890");
        assertAll("Properties test",
                ()-> assertAll("Person Properties",
                        ()->assertEquals("Joe", owner.getFirstName(), "First name did not match"),
                        ()->assertEquals("Buck", owner.getLastName(), "Last name did not match")),
                ()-> assertAll("Owner Properties",
                        ()->assertEquals("Key West", owner.getCity()),
                        ()->assertEquals("1234567890", owner.getTelephone())
                    ));
        assertThat(owner.getCity(), is("Key West"));
    }
}