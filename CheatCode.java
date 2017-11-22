public abstract class CheatCode extends Strategy
{
    public static final int[] code = new int[]{1, 1, 0, 0, 1, 1, 1, 1, 0, 1};

    public static final int[] responseCode = new int[]{0, 0, 1, 1, 1};

    protected int tick = -1;

    private boolean isTeamPlayer = true;

    public int nextMove()
    {
        tick++;
        if(isTeamPlayer) {
            int tickResponse = tick - code.length + 1;
            if (tick < code.length) {
                if(opponentLastMove != CheatCode.code[tick])
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
                if(opponentLastMove == 1)
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