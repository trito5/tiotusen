
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
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
        ArrayList<Integer> diceList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        int roundSum = 0;

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
                break;
            }
            else {

                while (true) {
                    System.out.print("\nYour score this round: " + roundSum + " + " + sum + " = " + (roundSum + sum) +". Store score(s) or Roll again (r)");
                    roundSum = roundSum + sum;
                    String userInput = scanner.next();
                    if(userInput.equals("s"))  {
                        score += roundSum;
                        System.out.println("Save scores. Your total score is " + score + ".");
                        diceList.clear();
                        sum = 0;
                        roundSum = 0;
                        break;

                    }
                    if(userInput.equals("r")) {
                        System.out.println("Roll again!");
                        break;

                        
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
        //List<Integer> newDices = diceList.subList(storedDices, diceList.size());
        ArrayList<Integer> newDices = new ArrayList<>();
        for( int i = storedDices; i < diceList.size(); i ++) {
            newDices.add(diceList.get(i));
        }

        List<Integer> oldDices = new ArrayList<>();
        if (storedDices > 0) {
            for( int i = 0; i < storedDices; i ++) {
                oldDices.add(diceList.get(i));
            }
            //oldDices = diceList.subList(0, storedDices);
        }

        int numberDice1 = Collections.frequency(newDices, 1);
        int numberDice2 = Collections.frequency(newDices, 2);
        int numberDice3 = Collections.frequency(newDices, 3);
        int numberDice4 = Collections.frequency(newDices, 4);
        int numberDice5 = Collections.frequency(newDices, 5);
        int numberDice6 = Collections.frequency(newDices, 6);

        if (numberDice1 == 1 && numberDice2 == 1 && numberDice3 == 1 && numberDice4 == 1 && numberDice5 == 1 && numberDice6 == 1) {
            sum += 1500;
            oldDices.add(1);
            oldDices.add(2);
            oldDices.add(3);
            oldDices.add(4);
            oldDices.add(5);
            oldDices.add(6);

        }
        else {

            for (int i = 1; i<= 6; i++) {
                int numberDice = Collections.frequency(newDices, i);
                int multifier = 100;
                if (i == 1) {
                    multifier = 1000;
                }

                if (numberDice == 3 || numberDice == 4 ||numberDice == 5 || numberDice == 6){
                    sum+= i * multifier * Math.pow(2, numberDice - 3);
                    // 3 st 2:or blev 100..
                    // 100* 5^2          500
                    // 5 * 100 * 2^1    1000 Math.pow(2, numberDice - 3)
                    // 5 * 100 * 2^2    2000
                    // 5 * 100 * 2^3    4000
                    for (int j = 1; j <= numberDice; j++) {
                        oldDices.add(i);
                    }

                }
            }

            if (numberDice1 == 1) {
                sum += 100;
                oldDices.add(1);
            }
            if (numberDice1 == 2) {
                sum += 200;
                oldDices.add(1);
                oldDices.add(1);
            }
            if (numberDice5 == 1) {
                sum += 50;
                oldDices.add(5);
            }
            if (numberDice5 == 2) {
                sum += 100;
                oldDices.add(5);
                oldDices.add(5);
            }
        }
        diceList.clear();

       // diceList.addAll(oldDices);

        if (oldDices.size() != 6) {
            for (Integer dice : oldDices) {
                diceList.add(dice);
            }
        }
        return sum;
    }




}
