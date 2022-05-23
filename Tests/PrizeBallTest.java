import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PrizeBallTest {
    private String input;
    private PrizeBall pb1;
    private PrizeBall pb2;
    private String color1;
    private String color2;
    private int amount1;
    private int amount2;
    private String type1;
    private String type2;
    private String prize1;
    private String prize2;


    @BeforeEach
    void setUp(){
        //for test sake, list of prizes are the same
        input = """
                NORMAL PRIZE BALL [12]
                Pink
                This ball contains a normal prize from the sea life range.
                {Dolphin, Dolphin, Dolphin, Dolphin}""";
        color1 = "Pink";
        prize1 = "Dolphin";
        color2 = "White";
        type1 = "Normal";
        type2 = "Legendary";
        prize2 = "Marmot";
        pb1 = new PrizeBall(color1, type1, prize1);
    }

    @Test
    void testConstructor() {
        assertNotNull(pb1);
    }

    @Test
    void read() {
        pb2 = PrizeBall.read(new Scanner(input)).get(0);
        assertEquals(pb1, pb2);
    }

    @Test
    void getType() {
        assertEquals(pb1.getType(), type1);
    }

    @Test
    void getPrizes() {
        assertEquals(pb1.getPrize(), prize1);
    }

    @Test
    void testToString() {
        assertEquals(pb1.toString(), "Normal Prize Ball (pink), it contains Dolphin.");
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(pb1, pb1);
    }
    @Test
    void testEqualsDifferentObject() {
        pb2 = new PrizeBall(color1, type1, prize1);
        assertEquals(pb1, pb2);
    }
    @Test
    void testNotEqualsDifferentObject() {
        pb2 = new PrizeBall(color2, type2, prize2);
        assertNotEquals(pb1, pb2);
    }
}