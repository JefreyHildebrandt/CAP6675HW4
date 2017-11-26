public class StrategyHildebrandt extends Strategy
{
    private int tick = -1;
    private boolean isTeamPlayer = true;

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
            int tickResponse = tick - CheatCode.code.length - 2;
            // sends out the code
            if (tick < CheatCode.code.length) {
                return CheatCode.code[tick];
            }
            else if (tickResponse < CheatCode.responseCode.length && tickResponse >= 0)
            {
                // verifies the opponent is CheatCode
                if (opponentLastMove != CheatCode.responseCode[tickResponse])
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
                // if the opponent doesn't constantly attack, assume the opponent is not CheatCode and got lucky
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
