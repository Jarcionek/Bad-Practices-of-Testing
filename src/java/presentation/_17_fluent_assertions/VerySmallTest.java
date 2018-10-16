package presentation._17_fluent_assertions;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VerySmallTest {

    @Test
    public void test() {
        assertThat("text".matches("\\d+"));
    }

}
