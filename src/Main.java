
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
	/*Roll 6 dices. Store the dices that gives you scores. Decide if you want to stop and bank the score. Or, roll the
	 rest of the dices to try and get more score. As long as you get at least one dice that gives score you can
	 continue. If all 6 dices give scores, you get to roll them all again. If you, in one roll, receive only no scoring
	 dices, you will loose all your points won in your turn and the turn is over. When you have 10.000 scores in total
	 you win.
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
        List<Integer> diceList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        int roundSum = 0;
        boolean gameOver = false;

        while (!gameOver) {
            int storedDices = diceList.size(); //how many dices were stored from the round before. Will be zero for a new round.

            if (roundSum == 0) {
                System.out.println("*** Game 10.000 ,new round ***");
            }
            diceList = rollDices(diceList);
            System.out.print("\nYour dices: ");
            for (int i = 0; i < diceList.size(); i++) {
                System.out.print(diceList.get(i) + " - ");
            }
            System.out.print("\b\b");

            int sum = checkDicesForScores(diceList, storedDices);


            if (sum == 0){
                System.out.println("\n\nSorry, no scores. You are loosing your round score of " + roundSum +". \nPress any key + enter to roll all dices.");
                roundSum = 0;
                sum = 0;
                diceList.clear();
                scanner.next();
            }
            else {

                while (true) {
                    System.out.print("\n\nYour score this round: " + roundSum + " + " + sum + " = " + (roundSum + sum) +". Store score(s) or Roll again (r)");
                    roundSum = roundSum + sum;
                    String userInput = scanner.next();
                    if(userInput.equals("s"))  {
                        score += roundSum;
                        if (!checkWinner(score)) {
                            System.out.println("\n-------------------------------------------------");
                            System.out.println("Save scores. Your total score is " + score + ".");
                            System.out.println("-------------------------------------------------\n");
                            System.out.print("Press any key and enter to roll again.\n");
                            diceList.clear();
                            sum = 0;
                            roundSum = 0;
                            scanner.next();
                        } else {
                            System.out.println("You won! Your score is " + score + ". Congratulations!");
                            gameOver = true;
                        }
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

    private static List<Integer> rollDices(List<Integer> diceList) {
        int listSize = diceList.size();
        for (int i = 0; i < (6 - listSize); i ++){
            diceList.add(ThreadLocalRandom.current().nextInt(1, 7));
        }
        return diceList;
    }

    private static int checkDicesForScores(List<Integer> diceList, int storedDices) {
        int sum = 0;
        List<Integer> newDices = new ArrayList<>();
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

    public static boolean checkWinner(int score) {
        if (score >= 10000) {
            return true;
        }
        else {
            return false;
        }
    }




}
