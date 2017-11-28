import java.util.ArrayList;

public class StrategyBelcher extends CheatCode
{
    ArrayList<Integer> memo = new ArrayList<Integer>();
    float defectCount = 0;
    float cooperateCount = 0;
    int numDefects;
    boolean random = false;
    float prob = 0;

    public StrategyBelcher()
    {
        name = "StrategyBelcher";
        numDefects = 0;
    }

    public void saveOpponentMove(int move)
    {
        opponentLastMove = move;
        memo.add(move);
        if(move == 0)
        {
            defectCount++;
        }
        if(move == 1)
        {
            cooperateCount++;
        }
    }

    public int nextNextMove()
    {
        //Play Tit for Two Tats for first 50 moves
        if(memo.size() < 50)
        {
            if (opponentLastMove == 0)  
            {
                numDefects++;
            }

            if (opponentLastMove == 1)
            {
                numDefects = 0;
                return 1;
            }
            else
            {
                if (opponentLastMove == 0 && numDefects < 2)
                return 1;
            else  
            {
                return 0;
            }
         }
        }
        // Store with what probability the other player is defecting
        else if(memo.size() == 50)
        {
            prob = (defectCount/50);
        }
        // Defect with that same probability for the rest of the game
        if(Math.random() <= prob)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}