package domain.memorygame;

import domain.base.GameOptionTemplate;
import util.InputHandler;

public enum MemoryGameOptionMenu implements GameOptionTemplate<MemoryGameOption> {

    SIZE(1, "카드의 개수", "맞춰야하는 카드의 개수를 나타냅니다.",
            (input, option) -> {
                int size = input.readIntRange("카드의 개수를 입력해주세요", option.getMinSize(), option.getMaxSize());
                option.setSize(size);
            }),
    PAIR(2, "카드의 페어 수", "맞춰야하는 카드의 페어의 수를 나타냅니다.",
            (input, option) -> {
                int pair = input.readIntRange("카드의 페어를 입력해주세요.", option.getMinPair(), option.getMaxPair());
                option.setPair(pair);
            }),
    WEIGHT(3, "시간 가중치", "카드를 보여주는 시간을 나타냅니다.",
            (input, option) -> {
                int weight = input.readIntRange("시간 가중치를 입력해주세요.", option.getMinWeight(), option.getMAX_WEIGHT());
                option.setWeight(weight);
            });

    MemoryGameOptionMenu(int number, String name, String explain, OptionSetter<MemoryGameOption> setter) {
        NUMBER = number;
        NAME = name;
        EXPLAIN = explain;
        SETTER = setter;
    }

    private final int NUMBER;
    private final String NAME;
    private final String EXPLAIN;
    private final OptionSetter<MemoryGameOption> SETTER;

    @Override
    public int getNumber() {
        return NUMBER;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getExplain() {
        return EXPLAIN;
    }

    @Override
    public OptionSetter<MemoryGameOption> getSetter(){
        return SETTER;
    }
}

