package domain.pokergamble;

import java.util.ArrayList;
import java.util.List;

class PokerRankingResult {
    private final PokerRankingList ranking;
    private final List<Integer> kicker;

    PokerRankingResult(PokerRankingList ranking){
        this.ranking = ranking;
        kicker = new ArrayList<>();
    }

    void addKicker(int kicker){
        this.kicker.add(kicker);
    }

    List<Integer> getKicker(){
        return kicker;
    }

    int getRankingPower(){
        return ranking.getPower();
    }

    String getRankingName(){
        return ranking.getName();
    }
}
