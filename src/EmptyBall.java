import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class EmptyBall extends Ball{

    public EmptyBall(String color) {
        super(color);
    }

    public static List<EmptyBall> read(Scanner ballBlock) {
        int amount;
        String color;
        List<EmptyBall> emptyBall = new ArrayList<>();
        while(ballBlock.hasNextLine()) {
            String firstLine = ballBlock.nextLine();
            amount = Integer.parseInt(firstLine.substring(12, 14));
            color = ballBlock.nextLine();
            ballBlock.nextLine(); //skip description
            for (int i = 0; i<amount; i++){
                emptyBall.add(new EmptyBall(color));
            }
        }
        return emptyBall;
    }


    @Override
    public String toString() {
        //Empty ball (red), it contains... nothing.
        return "Empty ball (" + getColor().toLowerCase() +
                "), " + "it contains... nothing.";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (EmptyBall) o;
        return Objects.equals(ball.getColor(), this.getColor());
    }
}
