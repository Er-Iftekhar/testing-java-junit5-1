package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.assertj.core.internal.bytebuddy.matcher.StringMatcher;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTests {

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

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} of {totalRepetitions}" )
    @DisplayName("My Repeated Test - can be repeated n number of times")
    @Test
    void myRepeatedTests() {
        //todo - impl
    }
}