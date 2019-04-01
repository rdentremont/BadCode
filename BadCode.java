import java.util.Random;
import java.util.Scanner;

/**
 * This class simulates a game of nim, where a human and a computer take turns removing marbles from a pile. Whoever takes the last marble from the pile 
 * loses.
 * 
 * @author Ryan D'Entremont 
 * @version 4/5/2017
 */
public class GameOfNim
{
    private int numMarbles;
    private boolean isPlayerTurn;
    private boolean isDifficult;
    
    /**
     * Constructor sets the initial pile of marbles to a random size between the specified minimum and maximum.
     */
    public GameOfNim(int minPileSize, int maxPileSize)
    {
      Random randomGenerator = new Random();
      this.numMarbles = minPileSize + randomGenerator.nextInt(maxPileSize);
    }

    /**
     * This method generates a random integer between 0 and 1, and uses the value to make yes or no decisions.
     */
    private boolean randomDecider()
    {
       Random randomGenerator = new Random();
       int flag = randomGenerator.nextInt(2); 
       if(flag == 0)
       {
           return false;
       }
       else return true;
    }

    /**
     * This method prompts the user to enter the number of marbles to be removed from the pile
     */
    private int playerTurn()
    {
        Scanner humanTurn = new Scanner(System.in);
        int marblesRemoved = humanTurn.nextInt();

        while(marblesRemoved < 1 || marblesRemoved > numMarbles/2)
          {
              System.out.print("\n" + "Please enter a number between 1 and half of the remaining marbles: ");
              marblesRemoved = humanTurn.nextInt();
          }
        return marblesRemoved;
    }

    /**
     * The CPU takes a random number of marbles from the pile, between 1 and half of the pile size.
     */
    private int calculateRandomMove()
    {
         Random compTurn = new Random();
         int marblesRemoved = 1 + compTurn.nextInt(numMarbles /2);
         return marblesRemoved;
    }

    /**
     * The CPU tries to make the pile size a power of 2 minus 1. 
     * It does this by finding the closest power of 2 that is still less than the pile size, and decrements the result.  
     */
    
    private int calculateBestMove()
    {
        int marblesRemoved = 2; 
        while (marblesRemoved *2 < numMarbles) 
        { 
            marblesRemoved *= 2;
        }
        marblesRemoved--;
        return marblesRemoved;
    }

    /**
     * Subtract the specified amount from the pile size. 
     */
    private int subtractMarbles(int amount)
    {
        this.numMarbles -= amount;
    }

    /**
     * Check the win condition to see if the game is over.
     */
    private boolean checkWinCondition()
    {
        if(this.numMarbles == 1)
        {
            return true;
        }
        else return false;
    }

    /**
     * This method simulates the game. After the first turn and difficulty are decided randomly, the human and CPU
     * take turns removing marbles until there is only one marble left. The one who takes the last marble loses.
     */
    public void play()
    {
        int marblesRemoved;
        this.isPlayerTurn = randomDecider();
        this.isDifficult = randomDecider();
        System.out.println("Game begins");
        System.out.println("Initially there are " + numMarbles + " marbles in the pile");

       while(true)
       {
            if (isPlayerTurn)
            {
                System.out.print("Human's Turn: ");
                marblesRemoved = playerTurn();
                subtractMarbles(marblesRemoved);
                    if(checkWinCondition();)
                    {
                        System.out.print("Human Wins!!!" + "\n");
                        break;
                    }
                isPlayerTurn = false;
            }

            else
            {
                System.out.print("Computer's turn: ");
                    if(isDifficult)
                    {
                        marblesRemoved = calculateBestMove();
                    }  
                    else
                    {
                        marblesRemoved = calculateRandomMove(); 
                    }
                        subtractMarbles(marblesRemoved);
                        if(checkWinCondition();)
                        {
                            System.out.print("Computer Wins!!!" + "\n");
                            break;
                        }
               isPlayerTurn = true;
            }
        }   
    }
}
