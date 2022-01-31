package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class IndexControllerTest {

    IndexController indexController;

    @BeforeEach
    void setUp() {
        indexController = new IndexController();
    }

    @DisplayName("Test proper View name is returned for index page")
    @Test
    void index() {
        assertEquals("index", indexController.index());
        assertEquals("index", indexController.index(), "Wrong View Returned");
        assertEquals("index", indexController.index(), ()-> "Another expensive message" +
                "Make me only if you have to");
    }

    @DisplayName("Test exception")
    @Test
    void oupsHandler() {

        assertThrows(ValueNotFoundException.class, ()->{
            indexController.oupsHandler();
        });
//        assertTrue("notimplemented".equals(indexController.oupsHandler()),  () -> "This is some expensive "+
//                "Message to build " +
//                "for my test");
    }

    @Disabled("Demo of timeout")
    @Test
    void testTimeOut() {
        assertTimeout(Duration.ofMillis(100), ()->{
            Thread.sleep(5000);
            System.out.println("I got here");
        });
    }

    @Disabled("Demo of timeout")
    @Test
    void testTimeOutPrempt() {
        assertTimeout(Duration.ofMillis(100), ()->{
            Thread.sleep(5000);
            System.out.println("I got here 12345678890");
        });
    }

    @Test
    void testAssumptionTrue() {
        assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
    }
    @Test
    void testAssumptionTrueAssumptionIsTrue() {
        assumeTrue("GURU".equalsIgnoreCase("GURU_RUNTIME"));
    }
}