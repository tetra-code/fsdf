import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void read() {
        String input = """
                EXTRA BALLS BALL [5]
                Light Green
                This ball is special, you get to pull a few more balls!
                2
                EXTRA BALLS BALL [1]
                Bright Green
                This ball is special, you get to pull a few more balls!
                5
                """;
        Machine machine = Main.read(new Scanner(input));
        assertEquals(machine.getCurrentBalls().get(0).toString(), "Normal Prize Ball (pink), it contains Dolphin.");
    }
}