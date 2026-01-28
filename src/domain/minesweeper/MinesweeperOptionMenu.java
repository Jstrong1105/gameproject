package domain.minesweeper;

import util.InputHandler;

enum MinesweeperOptionMenu {

    SIZE(1,"사이즈","지뢰찾기의 가로, 세로 길이를 결정합니다.",
        (input,option) ->
            { int size = input.readIntRange("변경할 사이즈를 입력해주세요.",
              option.getMinSize(),option.getMaxSize());
              option.setSize(size);
            }),
    WEIGHT(2,"가중치","폭탄의 수를 조절합니다. 클수록 더 많아집니다.",
            (input,option)->
            { int weight = input.readIntRange("변경할 가중치를 입력해주세요.",
                option.getMinWeight(),option.getMaxWeight());
                option.setWeight(weight);
            })
    ;

    MinesweeperOptionMenu(int number,String name,String explain, OptionSetter setter)
    {
        NUMBER = number;
        NAME = name;
        EXPLAIN = explain;
        SETTER = setter;
    }

    private final int NUMBER;
    private final String NAME;
    private final String EXPLAIN;
    private final OptionSetter SETTER;

    @FunctionalInterface
    interface OptionSetter{
        void setOption(InputHandler input, MinesweeperOption option);
    }

    int getNumber() { return NUMBER; }
    String getName() { return NAME; }
    String getExplain() { return EXPLAIN; }

    void setOption(InputHandler input, MinesweeperOption option)
    {
        SETTER.setOption(input,option);
        System.out.println("설정이 변경되었습니다.");
    }

    static MinesweeperOptionMenu getMinesweeperOptionMenu(int number) {

        for(MinesweeperOptionMenu menu : MinesweeperOptionMenu.values()){

            if(menu.getNumber() == number){
                return menu;
            }
        }

        throw new IllegalArgumentException("존재하지않는 옵션번호입니다.");
    }
}
