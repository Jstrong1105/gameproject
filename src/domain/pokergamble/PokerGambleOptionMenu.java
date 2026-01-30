package domain.pokergamble;

import domain.base.GameOptionTemplate;

public enum PokerGambleOptionMenu implements GameOptionTemplate<PokerGambleOption> {

    MODE(1,"포커 / 7포커","포커의 장수를 결정합니다.",
            (input,option) -> {
            int mode = input.readIntRange("1. 5포커 / 2. 7포커",1,2);
            option.setFiveSeven(mode == 1);
            }),
    TARGET(2,"목표 코인","목표 코인을 결정합니다.",
            (input,option) -> {
            int targetCoin = input.readIntRange("변경할 목표코인을 입력하세요.",option.getMinTargetCoin(),option.getMaxTargetCoin());
            option.setTargetCoin(targetCoin);
            }),
    WEIGHT(3,"가중치","승리시 획득하는 코인 배율을 결정합니다.",
            (input,option) -> {
            int weight = input.readIntRange("변경할 가중치를 입력하세요.",option.getMinWeight(),option.getMaxWeight());
            option.setWeight(weight);
            })
    ;

    PokerGambleOptionMenu(int number, String name, String explain, OptionSetter<PokerGambleOption> setter){
        NUMBER = number;
        NAME = name;
        EXPLAIN = explain;
        SETTER = setter;
    }

    private final int NUMBER;
    private final String NAME;
    private final String EXPLAIN;
    private final OptionSetter<PokerGambleOption> SETTER;

    @Override
    public int getNumber() { return NUMBER; }
    @Override
    public String getName() { return NAME; }
    @Override
    public String getExplain() { return EXPLAIN; }

    @Override
    public OptionSetter<PokerGambleOption> getSetter(){
        return SETTER;
    }
}
