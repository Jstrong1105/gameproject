package domain.pokergamble;

import java.util.ArrayList;
import java.util.List;

class PokerRankingResult implements Comparable<PokerRankingResult> {
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

    @Override
    public int compareTo(PokerRankingResult o) {

        int result = Integer.compare(this.ranking.getPower(),o.ranking.getPower());

        if(result != 0){
            return result;
        }

        int size = Math.min(this.kicker.size(),o.kicker.size());

        for(int i = 0; i < size; i++){

            int a = this.kicker.get(i);
            int b = o.kicker.get(i);

            if(a != b){
                return Integer.compare(a,b);
            }
        }

        return 0;
    }
}
