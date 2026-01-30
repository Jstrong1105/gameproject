package domain.minesweeper;

import domain.base.GameOptionTemplate;

public enum MinesweeperOptionMenu implements GameOptionTemplate<MinesweeperOption> {

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

    MinesweeperOptionMenu(int number,String name,String explain, OptionSetter<MinesweeperOption> setter)
    {
        NUMBER = number;
        NAME = name;
        EXPLAIN = explain;
        SETTER = setter;
    }

    private final int NUMBER;
    private final String NAME;
    private final String EXPLAIN;
    private final OptionSetter<MinesweeperOption> SETTER;

    @Override
    public int getNumber() { return NUMBER; }
    @Override
    public String getName() { return NAME; }
    @Override
    public String getExplain() { return EXPLAIN; }

    @Override
    public OptionSetter<MinesweeperOption> getSetter(){
        return SETTER;
    }
}
