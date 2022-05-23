import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class EmptyBallTest {
    private String input;
    private EmptyBall eb1;
    private EmptyBall eb2;
    private String color1;
    private String color2;
    private int amount1;
    private int amount2;


    @BeforeEach
    void setUp(){
        input = """
                EMPTY BALL [10]
                Red
                Oh no! This ball is completely empty. Better luck on the next one!""";
        color1 = "Red";
        color2 = "Purple";
        eb1 = new EmptyBall(color1);
    }
    @Test
    void testConstructor() {
        assertNotNull(eb1);
    }

    @Test
    void read() {
        eb2 = EmptyBall.read(new Scanner(input)).get(0);
        assertEquals(eb1, eb2);
    }

    @Test
    void testToString() {
        assertEquals(eb1.toString(), "Empty ball (red), it contains... nothing.");
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(eb1, eb1);
    }
    @Test
    void testEqualsDifferentObject() {
        eb2 = new EmptyBall(color1);
        assertEquals(eb1, eb2);
    }
    @Test
    void testNotEqualsDifferentObject() {
        eb2 = new EmptyBall(color2);
        assertNotEquals(eb1, eb2);
    }
}