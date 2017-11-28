import java.util.ArrayList;

public class StrategyBelcher extends Strategy
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

    // the potential set of commands sent by team player
    public static final int[] code = new int[]{1, 1, 0, 0, 1, 1, 1, 0};

    // the response sent by this class once it is determined to be team player
    public static final int[] responseCode = new int[]{1, 1, 1, 0, 0};

    // tick starts at 0 by the time it gets to nextNextMove()
    protected int tick = -1;

    // if false then permanetely defer to nextNextMove()
    private boolean isTeamPlayer = true;

    @Override
    public int nextMove()
    {
        tick++;
        if(tick == 0)
        {
            // Defers the first turn to nextNextMove() always
            return nextNextMove();
        }
        int curOpponentTick = tick - 1;
        if(isTeamPlayer)
        {
            int tickResponse = curOpponentTick - code.length;
            // checks if the commands being sent are the same ones in the code array
            if (curOpponentTick < code.length) {
                if(opponentLastMove != code[curOpponentTick])
                {
                    isTeamPlayer = false;
                }
                return nextNextMove();
            }
            // once it is determined to be TeamPlayer, send the response so Team Player knows it's cheat code
            else if(tickResponse < responseCode.length)
            {
                return responseCode[tickResponse];
            }
            else
            {
                // after determining that the opponent is team player, if for whatever reason they attack
                // it is now assumed someone got lucky with the code and will now defer back to nextNextMove()
                // from now on
                if(opponentLastMove == 0)
                {
                    isTeamPlayer = false;
                    return nextNextMove();
                }
                return 0;
            }

        }
        else {
            // if determined that the opponent is not team player, then permanetely return nextNextMove()
            return nextNextMove();
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