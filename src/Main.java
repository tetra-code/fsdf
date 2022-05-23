import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Machine read(Scanner file){
        Machine machine = new Machine();
        String ballBlock;
        int j;
        List<PrizeBall> prizeBall;
        //for prize balls
        if (file.hasNextLine()) {
            for (int i = 0; i < 5; i++) {
                j = 4;
                ballBlock = "";
                while (j != 0) {
                    ballBlock = ballBlock + file.nextLine();
                    if (j != 1){
                        ballBlock = ballBlock + "\n";
                    }
                    j--;
                }
                prizeBall = PrizeBall.read(new Scanner(ballBlock));

                machine.getCurrentBalls().addAll(prizeBall);
            }
        }
        //for empty balls
        if(file.hasNextLine()) {
            ballBlock = "";
            for (int i = 0; i < 3; i++) {
                ballBlock = ballBlock + file.nextLine();
                if (i != 2){
                    ballBlock = ballBlock + "\n";
                }
            }
            List<EmptyBall> emptyBall = EmptyBall.read(new Scanner(ballBlock));
            machine.getCurrentBalls().addAll(emptyBall);
        }
        //for extra balls
        List<ExtraBall> extraBall;
        if (file.hasNextLine()) {
            for (int i = 0; i < 2; i++) {
                String extraBlock = "";
                j = 4;
                while (j != 0) {
                    extraBlock = extraBlock + file.nextLine();
                    if (j != 1){
                        extraBlock = extraBlock + "\n";
                    }
                    j--;
                }
                extraBall = ExtraBall.read(new Scanner(extraBlock));
                machine.getCurrentBalls().addAll(extraBall);
            }
        }
        return machine;
    }

    public static void interact(Scanner filePath){
        Machine machine = read(filePath);
        Scanner scan = new Scanner(System.in);
        boolean repeat = true;
        while(repeat) {
            System.out.println("""
                Please make your choice:
                    1 – Show all balls currently in the machine.
                    2 – Show current chance to win the legendary prize.
                    3 – Draw a ball.
                    4 – Write debug output to file.
                    5 – Reset machine state.
                    6 – Quit the application.
                        """);
            int option = scan.nextInt();
            switch(option){
                case 1:{
                    System.out.println(machine);
                    break;
                }
                case 2:{
                    if (machine.isHasLegendaryBall()) {
                        double percentage = 100 * (1 / (double) machine.getCurrentBalls().size());
                        System.out.println(percentage + "%");
                    }
                    else{
                        System.out.println("0%");
                    }
                    break;
                }
                case 3:{
                    String result = null;
                    int extra = 1;
                    while(extra != 0) {
                        Random rand = new Random();
                        int numPrizes = machine.getCurrentBalls().size();
                        int randomNumber = rand.nextInt(numPrizes);
                        Ball randomBall = machine.getCurrentBalls().remove(randomNumber);
                        if (randomBall instanceof PrizeBall) {
                            PrizeBall prizeBall = (PrizeBall) randomBall;
                            result = "You got a " + prizeBall.getType() + " Prize Ball (" +
                                    prizeBall.getColor().toLowerCase() + "), it contains " + prizeBall.getPrize() + ".";
                            if (prizeBall.getType().equals("Legendary")) {
                                machine.setHasLegendaryBall(false);
                            }
                        } else if (randomBall instanceof ExtraBall) {
                            ExtraBall extraBall = (ExtraBall) randomBall;
                            extra = extra + extraBall.getExtra();
                            result = "You got an extra Ball Ball (" + extraBall.getColor().toLowerCase() +
                                    "), " + "it gives " + extraBall.getExtra() + " extra draws.";
                        } else if (randomBall instanceof EmptyBall) {
                            result = "Oh no! This ball is completely empty. " +
                                    "Better luck on the next one!. You get Empty ball (red), it contains... nothing.";
                        }
                        machine.getFinishedBalls().add(randomBall);
                        System.out.println(result);
                        extra--;
                    }
                    break;
                }
                case 4:{
//                    A log of all previous draw actions is written to the file logs.txt. The log should contain a chronological record of all things that happened. A single line of the log file could for instance look like:
//                    1. A Normal Prize Ball (white) containing Rhino was drawn.
                    String result = "";
                    Ball ball;
                    for (int i = 1; i<= machine.getFinishedBalls().size(); i++){
                        ball = machine.getFinishedBalls().get(i-1);
                        if (ball instanceof PrizeBall) {
                            PrizeBall prizeBall = (PrizeBall) ball;
                            result = i + ". A " + prizeBall.getType() + " Prize Ball (" +
                                    prizeBall.getColor().toLowerCase() + ") containing "
                                    + prizeBall.getPrize() + " was drawn.";
                        }
                        else if (ball instanceof ExtraBall) {
                            ExtraBall extraBall = (ExtraBall) ball;
                            result = i + ". An Extra Ball (" +
                                    extraBall.getColor().toLowerCase() + ") was drawn.";
                        }
                        else if (ball instanceof EmptyBall) {
                            EmptyBall emptyBall = (EmptyBall) ball;
                            result = i + ". An Extra Ball (" +
                                    emptyBall.getColor().toLowerCase() + ") was drawn.";
                        }
                        System.out.println(result);
                    }
                    break;
                }
                case 5:{
                    machine.getCurrentBalls().addAll(machine.getFinishedBalls());
                    machine.getFinishedBalls().removeAll(machine.getFinishedBalls());
                    break;
                }
                case 6:{
                    System.out.println("Shutting down program");
                    repeat = false;
                    break;
                }
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Type in file path: ");
        String filePath = scan.next();
        interact(new Scanner(new File(filePath)));
        //or new Scanner(new File ( filepath))
    }
}
