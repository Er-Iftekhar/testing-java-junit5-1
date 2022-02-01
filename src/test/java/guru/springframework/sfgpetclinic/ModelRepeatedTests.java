package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.*;

@Tag("repeated")
public interface ModelRepeatedTests {

    //    @RepeatedTest(3)
    @DisplayName("Before all repetition test")
    @BeforeEach
    default void commonBeforeAllTest(TestInfo testInfo, RepetitionInfo repetitionInfo){
        System.out.println(testInfo.getDisplayName()+ " " + repetitionInfo.getCurrentRepetition());
    }
}
