public class StrategyHildebrandt extends Strategy
{
    private int tick = -1;
    private boolean isTeamPlayer = true;

    // the potential set of commands sent by team player
    public static final int[] code = new int[]{1, 1, 0, 0, 1, 1, 1, 0};

    // the response sent by this class once it is determined to be team player
    public static final int[] responseCode = new int[]{1, 1, 1, 0, 0};

    StrategyHildebrandt()
    {
        name = "Team Player";
    }

    @Override
    public int nextMove()
    {
        if(isTeamPlayer)
        {
            tick++;
            int tickResponse = tick - code.length - 2;
            // sends out the code
            if (tick < code.length) {
                return code[tick];
            }
            else if (tickResponse < responseCode.length && tickResponse >= 0)
            {
                // verifies the opponent is CheatCode
                if (opponentLastMove != responseCode[tickResponse])
                {
                    isTeamPlayer = false;
                }
            }
            else if (tickResponse < 0)
            {
                return 1;
            }
            else
            {
                // if the opponent doesn't constantly attack, assume the opponent is not a teammate and got lucky
                // guessing the code
                if(opponentLastMove != 0)
                {
                    isTeamPlayer = false;
                    return 0;
                }
            }
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
