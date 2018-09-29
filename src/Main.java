
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    	/*Roll 6 dices. Store the dices that gives you scores. Decide if you want to stop and get the score. Or, roll the
	 rest of the dices to try and get more score. As long as you get at least one dice that gives a score you can
	 continue deciding if you want to quit your turn and store the score, or roll the rest of the dices. If you have
	 scores on all 6 dices, you get to roll them all six again. If you, in one roll, receive no scoring dices, you will
	 loose all your points won in your turn and the turn is over. When you have 10.000 scores you win.
      ----------------------------
	  POSSIBLE SCORES
	  ----------------------------
	            1     2   3       4     5       6
	  Dice 1    100   -   1000   2000   4000    8000
	  Dice 2    -     -    200    400    800    1600
	  Dice 3    -     -    300    600   1200    2400
	  Dice 4    -     -    400    800   1600    3200
	  Dice 5    50    -    500   1000   2000    4000
	  Dice 6    -     -    600   1200   2400    4800

	  Dice sequence 1-6 1500p
	  ____________________________

	  */

    public static void main(String[] args) {
        Player player1 = new Player();
        ArrayList<Integer> diceList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);


        while (true) {
            int storedDices = diceList.size(); //how many dices were stored from the round before. Will be zero for a new round.

            diceList = rollDices(diceList);

            for (int i = 0; i < diceList.size(); i++) {
                System.out.print(diceList.get(i) + " - ");
            }
            System.out.print("\b\b");

            int sum = checkDicesForScores(diceList, storedDices);
            if (sum == 0){
                System.out.println("\nYour turn is over.");
                storedDices = 0;
                break;
            }
            else {
                score += sum;
                while (true) {
                    System.out.print("\nYour score this round: " + sum + ". Store score(s) or Roll again (r)");
                    String userInput = scanner.next();
                    if(userInput.equals("s"))  {
                        System.out.println("Save score");
                        break;
                    }
                    if(userInput.equals("r")) {
                        System.out.println("Roll again!");


                    }
                }


            }








    }


        
    }

    private static ArrayList<Integer> rollDices( ArrayList<Integer> diceList) {
        int listSize = diceList.size();
        for (int i = 0; i < (6 - listSize); i ++){
            diceList.add(ThreadLocalRandom.current().nextInt(1, 7));
        }
        return diceList;
    }

    private static int checkDicesForScores(List<Integer> diceList, int storedDices) {
        int sum = 0;
        List<Integer> newDices = diceList.subList(storedDices, diceList.size());
        storedDices = diceList.size() - newDices.size();

        int numberDice1 = Collections.frequency(newDices, 1);
        int numberDice2 = Collections.frequency(newDices, 2);
        int numberDice3 = Collections.frequency(newDices, 3);
        int numberDice4 = Collections.frequency(newDices, 4);
        int numberDice5 = Collections.frequency(newDices, 5);
        int numberDice6 = Collections.frequency(newDices, 6);


        if (numberDice1 == 3) { sum += 1000; }
        if (numberDice1 == 4) { sum += 2000; }
        if (numberDice1 == 5) { sum += 4000; }
        if (numberDice1 == 6) { sum += 8000; }

        if (numberDice2 == 3) { sum += 200; }
        if (numberDice2 == 4) { sum += 400; }
        if (numberDice2 == 5) { sum += 800; }
        if (numberDice2 == 6) { sum += 1600; }

        if (numberDice3 == 3) { sum += 300; }
        if (numberDice3 == 4) { sum += 600; }
        if (numberDice3 == 5) { sum += 900; }
        if (numberDice3 == 6) { sum += 1800; }

        if (numberDice4 == 3) { sum += 400; }
        if (numberDice4 == 4) { sum += 800; }
        if (numberDice4 == 5) { sum += 1600; }
        if (numberDice4 == 6) { sum += 3200; }

        if (numberDice5 == 3) { sum += 500; }
        if (numberDice5 == 4) { sum += 1000; }
        if (numberDice5 == 5) { sum += 2000; }
        if (numberDice5 == 6) { sum += 4000; }

        if (numberDice6 == 3) { sum += 600; }
        if (numberDice6 == 4) { sum += 1200; }
        if (numberDice6 == 5) { sum += 2400; }
        if (numberDice6 == 6) { sum += 4800; }

        if (numberDice1 == 1 && numberDice2 == 1 && numberDice3 == 1 && numberDice4 == 1 && numberDice5 == 1 && numberDice6 == 1) {
            sum += 1500;
        }
        else if (numberDice1 == 1) { sum += 100; }
        else if (numberDice6 == 1) { sum += 100; }

    return sum;
    }
}
