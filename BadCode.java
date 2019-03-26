import java.util.Random;
import java.util.Scanner;

/**
 * This class simulates a game of nim, where a human and a computer take turns removing marbles from a pile. Whoever takes the last marble from the pile 
 * loses.
 * 
 * @author (Ryan D'Entremont) 
 * @version (4/5/2017)
 */
public class GameOfNim
{
    private int mrbls;
    private int turn;
    private int difficulty;
    
    /**
     * Constructor sets the initial pile of marbles to a random number between the specified minimum and maximum.
     * 
     * @param two integers representing the minimum and the maximum size for the initial pile.
     */
    public GameOfNim(int min, int max)
    {
      Random r = new Random();
      this.mrbls = r.nextInt(max) + 1; // generate random int between the min and max.
      if (mrbls < min)
      {
          mrbls = min;
        }
      
    }
    /**
     * This method generates a random integer between 0 and 1, and uses the value to determine who gets the first turn.
     */
    private void turnDecider()
    {
       Random x = new Random();
       turn = x.nextInt(2); 
    }
    /**
     * This method generates a random integer between 0 and 1, and uses the value to determine whether the computer plays on stupid mode or smart mode.
     */
    private void difficultyDecider()
    {
       Random y = new Random();
       difficulty = y.nextInt(2);
    }
    /**
     * This method prompts the user to enter the number of marbles to be removed from the pile, and displays the number removed, and the current size of the pile.
     */
    private void playerTurn()
    {
        Scanner humanTurn = new Scanner(System.in);
        System.out.print("Human's Turn: ");
        int rmvd = humanTurn.nextInt();
        
        while(rmvd < 1 || rmvd > mrbls/2)
          {
              System.out.print("\n" + "Please enter a number between 1 and half of the remaining marbles: ");
              rmvd = humanTurn.nextInt();
          }
           
           mrbls -= rmvd;
           System.out.println("Removed " + rmvd + " marble(s)." + "    Current number of marbles: " + mrbls + "\n");
    }
    /**
     * In this method, the CPU tries to make the pile size a power of 2 minus 1. It does this by finding the closest power of 2 that is less than the current pile size,
     * and subtracts 1 from the result. 
     * 
     */
    
    private void smartMode()
    {
        int power = 2;      
        
        while (power < mrbls) 
        { 
            power *= 2;
        }
        
        power = power / 2; // We want the closest power of 2 that is still less than the number of marbles. After the loop, power is greater than the pile size.
        int target = power -1;
        int rmvd = mrbls - targetMarbles; 
        
        if (mrbls == 2) 
        {
            rmvd = 1;
        }
        
        mrbls -= rmvd;
        System.out.println("Removed " + rmvd + " marble(s)." + "Current number of marbles: " + mrbls);
    }
    /**
     * In this method, the CPU takes a random number of marbles from the pile, between 1 and half of the pile size.
     */
    private void stupidMode()
    {
         Random compTurn = new Random();
         int rmvd = compTurn.nextInt(mrbls/ 2);
          if(rmvd < 1) // Illegal move, must take at least 1.
         {
            rmvd = 1;
         }
         mrbls -= rmvd;
         System.out.println("Removed " + rmvd + " marble(s)." + "   Current number of marbles: " + mrbls);
    }

    /**
     * This method calls all the private methods to determine the parameters of the game. After the first turn and difficulty are decided, the human and CPU
     * take turns removing marbles until there is only one marble left. The one who takes the last marble loses!
     */
    public void play()
    {
       System.out.println("Game begins");
       System.out.println("Initially there are " + mrbls + " marbles in the pile");
       
       turnDecider();
       difficultyDecider();
       
       while(mrbls > 1)
       {
           if (turn == 0)
           {
             playerTurn();
             if(mrbls == 1)
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
                   stupidMode();
                }
            
              if(difficulty == 1) // Smart mode
              {
                 {
                     smartMode();
                 }
               }
               
              if(mrbls == 1)
              {
                  System.out.print("Computer Wins!!!" + "\n");
                  break;
              }
               turn--;
            }
        }
       
    }
}
