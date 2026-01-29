package domain.pokergamble;

import domain.base.GameResult;
import domain.base.GameTemplate;
import util.InputHandler;

class PokerGambleLauncher extends GameTemplate {

    PokerGambleLauncher(InputHandler input, PokerGambleOption option){
        super(input);
        five = option.getFS();
        weight = option.getWeight();
    }

    private final boolean five;
    private final int weight;

    private HandCard playerCard;
    private HandCard cpuCard;

    @Override
    protected void initialize() {

        System.out.println("포커겜블 게임입니다.");

    }

    @Override
    protected void render() {

    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update() {

    }

    @Override
    protected void finish(GameResult result) {

    }
}
