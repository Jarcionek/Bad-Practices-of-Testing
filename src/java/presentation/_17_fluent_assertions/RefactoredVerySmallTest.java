package presentation._17_fluent_assertions;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RefactoredVerySmallTest {

    @Test
    public void solution1() {
        assertThat("text").matches("\\d+");
    }

    @Test
    public void solution2() {
        assertThat("text".matches("\\d+")).isTrue();
    }

}
