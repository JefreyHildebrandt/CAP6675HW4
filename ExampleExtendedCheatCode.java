public class ExampleExtendedCheatCode extends CheatCode
{
    ExampleExtendedCheatCode()
    {
        name = "ExampleExtendedCheatCode";
        opponentLastMove = 0;
    }
    // 0 = defect 1 = cooperate
    public int nextNextMove()
    {
        return 1;
    }
}