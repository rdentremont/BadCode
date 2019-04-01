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
    private int currentTurn;
    private int difficulty;
    
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
    private int randomDecider()
    {
       Random randomGenerator = new Random();
       return randomGenerator.nextInt(2); 
    }

    /**
     * This method prompts the user to enter the number of marbles to be removed from the pile
     */
    private int playerTurn()
    {
        Scanner humanTurn = new Scanner(System.in);
        System.out.print("Human's Turn: ");
        int marblesRemoved = humanTurn.nextInt();
        
        while(marblesRemoved < 1 || marblesRemoved > numMarbles/2)
          {
              System.out.print("\n" + "Please enter a number between 1 and half of the remaining marbles: ");
              marblesRemoved = humanTurn.nextInt();
          }
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
     * The CPU takes a random number of marbles from the pile, between 1 and half of the pile size.
     */
    private int calculateRandomMove()
    {
         Random compTurn = new Random();
         int marblesRemoved = 1 + compTurn.nextInt(numMarbles /2);
         return marblesRemoved;
    }

    /**
     * This method simulates the game. After the first turn and difficulty are decided, the human and CPU
     * take turns removing marbles until there is only one marble left. The one who takes the last marble loses.
     */
    public void play()
    {
       System.out.println("Game begins");
       System.out.println("Initially there are " + numMarbles + " marbles in the pile");

       while(numMarbles > 1)
       {
           if (currentTurn == 0)
           {
             playerTurn();
             if(numMarbles == 1)
             {
                System.out.print("Human Wins!!!" + "\n");
                break;
             }
             turn++;
            }
            
           if(turn == 1)
           {
             System.out.print("Computer's turn: ");
               if(difficulty == 0) // Stupid mode
               {
                   calculateRandomMove();
                }
            
              if(difficulty == 1) // Smart mode
              {
                 {
                     smartMode();
                 }
               }
               
              if(numMarbles == 1)
              {
                  System.out.print("Computer Wins!!!" + "\n");
                  break;
              }
               turn--;
            }
        }
       
    }
}
