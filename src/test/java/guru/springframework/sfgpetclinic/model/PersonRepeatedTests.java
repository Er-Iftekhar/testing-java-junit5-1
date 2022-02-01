package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelRepeatedTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

public class PersonRepeatedTests implements ModelRepeatedTests {
    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} of {totalRepetitions}" )
    @DisplayName("My Repeated Test - can be repeated n number of times")
    void myRepeatedTests() {
        //todo - impl
    }

    @RepeatedTest(5)
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + " " + repetitionInfo.getCurrentRepetition());
    }

    @RepeatedTest(5)
    void anotherAssignmentTestMethodForRepeatedTests(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
    }
}
