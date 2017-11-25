public class TeamPlayer extends Strategy
{
    private int tick = -1;
    private boolean isTeamPlayer = true;
    TeamPlayer()
    {
        name = "Team Player";
    }
    @Override
    public int nextMove()
    {
        if(isTeamPlayer) {
            tick++;
            int tickResponse = tick - CheatCode.code.length - 2;
            if (tick < CheatCode.code.length) {
                return CheatCode.code[tick];
            }
            else if (tickResponse < CheatCode.responseCode.length && tickResponse >= 0)
            {
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
