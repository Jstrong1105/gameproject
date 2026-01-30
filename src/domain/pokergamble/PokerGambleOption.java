package domain.pokergamble;

public class PokerGambleOption {

    private boolean fiveSeven = true;

    void setFiveSeven(boolean fiveSeven){
        this.fiveSeven = fiveSeven;
    }

    boolean getFS(){
        return fiveSeven;
    }

    private final int MIN_WEIGHT = 1;
    private final int MAX_WEIGHT = 3;
    private int weight = MIN_WEIGHT;

    int getMinWeight() { return MIN_WEIGHT; }
    int getMaxWeight() { return MAX_WEIGHT; }
    int getWeight() { return weight; }

    void setWeight(int weight){

        if(weight < MIN_WEIGHT || weight > MAX_WEIGHT){
            throw new IllegalArgumentException("허용하지 않는 가중치입니다.");
        }
        this.weight = weight;
    }

    private final int MIN_TARGET_COIN = 1000;
    private final int MAX_TARGET_COIN = 10000;
    private int targetCoin = MIN_TARGET_COIN;

    int getMinTargetCoin() { return MIN_TARGET_COIN; }
    int getMaxTargetCoin() { return MAX_TARGET_COIN; }
    int getTargetCoin() { return targetCoin; }

    void setTargetCoin(int targetCoin){

        if(targetCoin < MIN_TARGET_COIN || targetCoin > MAX_TARGET_COIN){
            throw new IllegalArgumentException("허용하지 않는 목표치 입니다.");
        }
        this.targetCoin = targetCoin;
    }
}
