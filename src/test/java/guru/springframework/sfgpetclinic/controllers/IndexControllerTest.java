package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue("notimplemented".equals(indexController.oupsHandler()),  () -> "This is some expensive "+
                "Message to build " +
                "for my test");
    }
}