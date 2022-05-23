import java.util.*;

public class ExtraBall extends Ball{

    private int extra;

    public ExtraBall(String color, int extra) {
        super(color);
        this.extra = extra;
    }

    public int getExtra() {
        return extra;
    }

    public static List<ExtraBall> read(Scanner ballBlock) {
        int amount;
        String color;
        int extra;
        List<ExtraBall> extraBall = new ArrayList<>();
        while(ballBlock.hasNextLine()) {
            String firstLine = ballBlock.nextLine();
            amount = Integer.parseInt(firstLine.substring(18, 19));
            color = ballBlock.nextLine();
            ballBlock.nextLine(); //skip description
            extra = Integer.parseInt(ballBlock.nextLine());

            for (int i = 0; i<amount; i++){
                extraBall.add(new ExtraBall(color, extra));
            }
        }
        return extraBall;
    }

    @Override
    public String toString() {
        //Extra Ball Ball (light green), it gives 2 extra draws.
        return "Extra Ball Ball (" + getColor().toLowerCase() +
                "), " + "it gives " + getExtra() + " extra draws.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExtraBall extraBall = (ExtraBall) o;
        return extra == extraBall.extra;
    }

}
