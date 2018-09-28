import java.util.ArrayList;
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
        ArrayList<Integer> diceList = new ArrayList<Integer>();

        diceList = rollDices(diceList);

        for (int i = 0; i < diceList.size(); i++) {
            System.out.print(diceList.get(i) + " - ");
        }
        System.out.print("\b\b");


	/*


	while (true) {

    }
 */
        //new GameBoard();
        
    }

    public static ArrayList<Integer> rollDices( ArrayList<Integer> diceList) {
        int listSize = diceList.size();
        for (int i = 0; i < (6 - listSize); i ++){
            diceList.add(ThreadLocalRandom.current().nextInt(1, 7));
        }
        return diceList;
    }
    
}
