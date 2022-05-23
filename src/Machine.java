import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Machine {
    private List<Ball> currentBalls;
    private List<Ball> finishedBalls;
    private boolean hasLegendaryBall;

    public Machine(){
        currentBalls = new ArrayList<>();
        finishedBalls = new ArrayList<>();
        hasLegendaryBall = true;
    }

    public List<Ball> getCurrentBalls() {
        return currentBalls;
    }

    public List<Ball> getFinishedBalls() {
        return finishedBalls;
    }

    public boolean isHasLegendaryBall() {
        return hasLegendaryBall;
    }

    public void setHasLegendaryBall(boolean hasLegendaryBall) {
        this.hasLegendaryBall = hasLegendaryBall;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i<currentBalls.size(); i++){
            result = result + currentBalls.get(i).toString();
            if (i != currentBalls.size() - 1){
                result = result + "\n";
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Machine machine = (Machine) o;
        return Objects.equals(currentBalls, machine.currentBalls) && Objects.equals(finishedBalls, machine.finishedBalls);
    }
}
