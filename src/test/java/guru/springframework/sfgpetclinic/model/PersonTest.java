package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("model")
class PersonTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void groupedAssertions() {
        //given
        Person person = new Person(1L, "Joe","Buck");
        //then
        assertAll("Test properties set",
                ()-> assertEquals("Joe", person.getFirstName()),
                ()-> assertEquals("Buck", person.getLastName())
        );
    }

    @Test
    void groupedAssertions2() {
        //given
        Person person = new Person(1L, "Joe","Buck");
        //then
        assertAll("Test properties set",
                ()-> assertEquals("Joe", person.getFirstName() , "First name fails"),
                ()-> assertEquals("Buck", person.getLastName() , "Last name fails")
        );
    }
}