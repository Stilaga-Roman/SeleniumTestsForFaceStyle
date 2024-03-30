import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;

public class ExampleTest {
    @Test
    public void testNumbers() {
        String yandex = "I will be a great test automation engineer";
        MatcherAssert.assertThat(yandex, allOf(startsWith("I will"), endsWith("automation engineer")));
    }
} 