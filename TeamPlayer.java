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
            int tickResponse = tick - CheatCode.code.length + 1;
            if (tick < CheatCode.code.length) {
                return CheatCode.code[tick];
            } else if (tickResponse < CheatCode.responseCode.length) {
                if (opponentLastMove != CheatCode.responseCode[tickResponse]) {
                    isTeamPlayer = false;
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
