package domain.minesweeper;

import util.InputHandler;

enum MinesweeperOptionMenu {

    SIZE(1,"지뢰찾기판의 사이즈","지뢰찾기의 가로, 세로 길이를 결정합니다.",
        (input,option) ->
            { int size = input.readIntRange("변경할 사이즈를 입력해주세요.",
              option.getMinSize(),option.getMaxSize());
              option.setSize(size);
            }),
    WEIGHT(2,"폭탄 배율","폭탄의 수를 조절합니다. 클수록 더 많아집니다.",
            (input,option)->
            { int weight = input.readIntRange("변경할 배율을 입력해주세요.",
                option.getMinWeight(),option.getMaxWeight());
                option.setWeight(weight);
            })
    ;

    MinesweeperOptionMenu(int number,String name,String explain, OptionSetter setter)
    {
        this.number = number;
        this.name = name;
        this.explain = explain;
        this.setter = setter;
    }

    private final int number;
    private final String name;
    private final String explain;
    private final OptionSetter setter;

    @FunctionalInterface
    interface OptionSetter{
        void setOption(InputHandler input, MinesweeperOption option);
    }

    int getNumber() { return number; }
    String getName() { return name; }
    String getExplain() { return explain; }

    void setOption(InputHandler input, MinesweeperOption option)
    {
        setter.setOption(input,option);
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
