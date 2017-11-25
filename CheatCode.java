public abstract class CheatCode extends Strategy
{
    public static final int[] code = new int[]{1, 1, 0, 0, 1, 1, 1, 0};

    public static final int[] responseCode = new int[]{1, 1, 1, 0, 0};

    protected int tick = -1;

    private boolean isTeamPlayer = true;

    @Override
    public int nextMove()
    {
        tick++;
        if(tick == 0)
        {
            return nextNextMove();
        }
        int curOpponentTick = tick - 1;
        if(isTeamPlayer)
        {
            int tickResponse = curOpponentTick - code.length;
            if (curOpponentTick < code.length) {
                if(opponentLastMove != code[curOpponentTick])
                {
                    isTeamPlayer = false;
                }
                return nextNextMove();
            }
            else if(tickResponse < responseCode.length)
            {
                return responseCode[tickResponse];
            }
            else
            {
                if(opponentLastMove == 0)
                {
                    isTeamPlayer = false;
                    return nextNextMove();
                }
                return 0;
            }

        }
        else {
            return nextNextMove();
        }
    }

    public abstract int nextNextMove();
}