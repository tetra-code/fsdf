import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ExtraBallTest {
    private String input;
    private ExtraBall eb1;
    private ExtraBall eb2;
    private String color1;
    private String color2;
    private int amount1;
    private int amount2;
    private int extra1;
    private int extra2;


    @BeforeEach
    void setUp(){
        input = """
                EXTRA BALLS BALL [5]
                Light Green
                This ball is special, you get to pull a few more balls!
                2""";
        color1 = "Light Green";
        color2 = "Green";
        extra1 = 2;
        extra2 = 3;
        eb1 = new ExtraBall(color1, extra1);
    }
    @Test
    void testConstructor() {
        assertNotNull(eb1);
    }

    @Test
    void getExtra() {
        assertEquals(eb1.getExtra(), extra1);
    }

    @Test
    void read() {
        eb2 = ExtraBall.read(new Scanner(input)).get(0);
        assertEquals(eb1, eb2);
    }

    @Test
    void testToString() {
        assertEquals(eb1.toString(), "Extra Ball Ball (light green), it gives 2 extra draws.");
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(eb1, eb1);
    }
    @Test
    void testEqualsDifferentObject() {
        eb2 = new ExtraBall(color1, extra1);
        assertEquals(eb1, eb2);
    }
    @Test
    void testNotEqualsDifferentObject() {
        eb2 = new ExtraBall(color1, extra2);
        assertNotEquals(eb1, eb2);
    }
}