import java.util.*;

public class PrizeBall extends Ball{

    private String type;
    private String prize;

    public PrizeBall(String color, String type, String prize) {
        super(color);
        this.type = type;
        this.prize = prize;
    }

    public static List<PrizeBall> read(Scanner ballBlock) {
        String type = null;
        int amount = 0;
        String color;
        String prizesStr;
        String prizes;
        PrizeBall prizeBall = null;
        String prize;
        List<String> prizeList = new ArrayList<>();
        List<PrizeBall> prizeBalls = new ArrayList<>();
        while(ballBlock.hasNextLine()){
            String firstLine = ballBlock.nextLine();
            if (firstLine.startsWith("NORMAL")){
                type = "Normal";
                amount = Integer.parseInt(firstLine.substring(19, 21));
            }
            else if(firstLine.startsWith("RARE")){
                type = "Rare";
                amount = Integer.parseInt(firstLine.substring(17, 19));
            }
            else if(firstLine.startsWith("EPIC")){
                type = "Epic";
                amount = Integer.parseInt(firstLine.substring(17, 18));
            }
            else if (firstLine.startsWith("LEGENDARY")){
                type = "Legendary";
                amount = Integer.parseInt(firstLine.substring(22, 23));
            }
            color = ballBlock.nextLine();
            ballBlock.nextLine(); //skip description

            prizesStr = ballBlock.nextLine();
            prizes = prizesStr.substring(1, prizesStr.length()-1); //removes brackets
            Scanner prizeScan = new Scanner(prizes).useDelimiter(", ");
            while(prizeScan.hasNext()){
                prize = prizeScan.next();
                prizeList.add(prize);
            }
            //to generate random prize in the list
            Random rand = new Random();
            int numPrizes = prizeList.size();
            //to generate a list of x amount of prize balls
            for (int i = 0; i<amount; i++){
                int randomNumber = rand.nextInt(numPrizes);
                prize = prizeList.get(randomNumber);
                prizeBalls.add(new PrizeBall(color, type, prize));
            }
        }
        return prizeBalls;
    }

    public String getType() {
        return type;
    }

    public String getPrize() {
        return prize;
    }

    @Override
    public String toString() {
        //Normal Prize Ball (white), it contains Rhino.
        return getType() + " Prize Ball (" + getColor().toLowerCase() +
                "), it contains " + getPrize() + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PrizeBall prizeBall = (PrizeBall) o;
        return Objects.equals(type, prizeBall.type) && Objects.equals(prize, prizeBall.prize);
    }
}
