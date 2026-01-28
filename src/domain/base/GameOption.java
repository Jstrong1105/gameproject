package domain.base;

import domain.minesweeper.MinesweeperOption;

public class GameOption {

    private MinesweeperOption MINESWEEPER_OPTION;
    public MinesweeperOption getMinesweeperOption()
    {
        if (MINESWEEPER_OPTION == null){
            MINESWEEPER_OPTION = new MinesweeperOption();
        }
        return MINESWEEPER_OPTION;
    }
}
