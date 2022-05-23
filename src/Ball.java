import java.util.Objects;

abstract public class Ball {

    private String color;

    public Ball(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }


    abstract public String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return Objects.equals(color, ball.color);
    }
}
