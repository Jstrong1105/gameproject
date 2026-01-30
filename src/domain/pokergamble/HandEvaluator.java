package domain.pokergamble;

import domain.TrumpCard.Card;

import java.util.*;

/**
 * 카드 리스트를 받아서 PokerRankingResult를 반환하는 녀석
 */
class HandEvaluator {

    // 받은 카드 뭉치
    private List<Card> handCard;

    // 숫자 카운트
    // 여기에는 2 : 1 , 4 : 2 , 7 : 3 .... 14 : 1 이런식이다.
    private HashMap<Integer,Integer> numberCount;

    // 페어 카운트
    // 여기는 1 : 3 , 2 : 2 ... 4 : 1 이런식이다.
    private HashMap<Integer,Integer> groupCounts;

    // 숫자 정렬
    // 여기는 14 10 7 6 6 6 3 2 이런식이다.
    private List<Integer> numberList;

    // 플러시 여부
    private boolean flush;
    // 여기는 플러시에 해당하는 모양을 가지는 숫자들이
    // 10 10 7 6 5 이런식으로 존재한다.
    private List<Integer> flushNumberList;

    private final List<Evaluator> evaluators = Arrays.asList
            (
                this::straightFlush,
                this::fourOfAKind,
                this::fullHouse,
                this::flush,
                this::straight,
                this::triple,
                this::twoPair,
                this::onePair,
                this::highCard
            );

    // 외부에서 호출할 메소드
    PokerRankingResult getResult(List<Card> handCard){

        this.handCard = handCard;
        PokerRankingResult result;
        ready();

        for(Evaluator evaluator : evaluators){
            result = evaluator.evaluator();
            if(result != null){
                return result;
            }
        }

        throw new IllegalStateException("족보 판별에 에러가 발생했습니다.");
    }

    @FunctionalInterface
    private interface Evaluator{
        PokerRankingResult evaluator();
    }

    // 숫자 리스트를 받아서 스트레이트를 만족하는 지 체크하는 메소드
    private int isStraight(List<Integer> numberList){

        int n = 0;

        int[] count = new int[13];

        for(int i : numberList){
            count[i-2] += 1;
        }

        for(int i = count.length-1; i >= 0; i--){
            if(count[i] >= 1){
                n++;
            }
            else{
                n = 0;
            }
            if(n>=5){
                return i + 6;
            }
        }

        if(count[12] >= 1 && count[0] >= 1 && count[1] >= 1 && count[2] >= 1 && count[3] >= 1){
            return 5;
        }

        return -1;
    }

    private PokerRankingResult straightFlush() {

        // 플러시 조건을 만족하지 못한다면
        if(!flush){
            return null;
        }

        int num = isStraight(flushNumberList);

        // 플러시 조건은 만족했지만
        // 스트레이트 조건은 만족하지 않은 경우
        if(num == -1){
            return null;
        }
        // 스플인 경우
        else {
            PokerRankingResult result;
            // 가장 높은 카드가 14인 경우
            if(num == 14){
                result = new PokerRankingResult(PokerRankingList.LOYAL_FLUSH);
            }
            // 가장 높은 카드가 14가 아닌 경우
            else{
                result = new PokerRankingResult(PokerRankingList.STRAIGHT_FLUSH);
            }
            result.addKicker(num);
            return result;
        }
    }

    private PokerRankingResult fourOfAKind(){
        if(groupCounts.containsKey(4)){
            PokerRankingResult result = new PokerRankingResult(PokerRankingList.FOUR_OF_A_KIND);

            for(int i : numberList){
                if(numberCount.get(i) >= 4){
                    result.addKicker(i);

                    for(int j : numberList){
                        if(j != i){
                            result.addKicker(j);
                            return result;
                        }
                    }
                }
            }
        }

        return null;
    }

    private PokerRankingResult fullHouse(){

        int triple = groupCounts.getOrDefault(3,0);
        int pair = groupCounts.getOrDefault(2,0);

        if(triple >=2 || (triple == 1 && pair >= 1)){
            PokerRankingResult result = new PokerRankingResult(PokerRankingList.FULL_HOUSE);

            for(int i : numberList){
                if(numberCount.get(i) >= 3){
                    result.addKicker(i);

                    for(int j : numberList){
                        if(j != i && numberCount.get(j) >= 2){
                            result.addKicker(j);
                            return result;
                        }
                    }

                    break;
                }
            }
        }

        return null;
    }

    private PokerRankingResult flush(){

        if(!flush){
            return null;
        }

        PokerRankingResult result = new PokerRankingResult(PokerRankingList.FLUSH);

        int n = 0;

        for(int i : flushNumberList){

            result.addKicker(i);
            n++;

            if(n >= 5){
                return result;
            }
        }

        return null;
    }

    private PokerRankingResult straight(){

        int num = isStraight(numberList);

        if(num == -1){
            return null;
        }

        else{
            PokerRankingResult result;

            if(num == 14){
                result = new PokerRankingResult(PokerRankingList.MOUNTAIN);
            }
            else if(num == 5){
                result = new PokerRankingResult(PokerRankingList.BACK_STRAIGHT);
            }
            else{
                result = new PokerRankingResult(PokerRankingList.STRAIGHT);
            }
            result.addKicker(num);
            return result;
        }
    }

    private PokerRankingResult triple(){

        if(groupCounts.containsKey(3)){

            PokerRankingResult result = new PokerRankingResult(PokerRankingList.TRIPLE);

            for(int i : numberList){
                if(numberCount.get(i) >= 3){
                    result.addKicker(i);

                    int n = 0;

                    for(int j : numberList){

                        if(j != i){
                            result.addKicker(j);
                            n++;
                        }

                        if(n >= 2){
                            return result;
                        }
                    }
                }
            }
        }

        return null;
    }

    private PokerRankingResult twoPair(){

        int pair = groupCounts.getOrDefault(2,0);

        if(pair >= 2){
            PokerRankingResult result = new PokerRankingResult(PokerRankingList.TWO_PAIR);

            int highPair = 0;
            int lowPair = 0;
            int kicker = 0;

            for(int i : numberList){
                if(numberCount.get(i) >= 2){
                    highPair = i;
                    break;
                }
            }

            for(int i : numberList){
                if(i != highPair && numberCount.get(i) >= 2){
                    lowPair = i;
                    break;
                }
            }

            for(int i : numberList){
                if(i != highPair && i != lowPair){
                    kicker = i;
                    break;
                }
            }

            result.addKicker(highPair);
            result.addKicker(lowPair);
            result.addKicker(kicker);
            return result;
        }

        return null;
    }

    private PokerRankingResult onePair(){

        int pair = groupCounts.getOrDefault(2,0);

        if(pair >= 1){

            PokerRankingResult result = new PokerRankingResult(PokerRankingList.ONE_PAIR);

            int kicPair = 0;

            for(int i : numberList){
                if(numberCount.get(i) >= 2){
                    kicPair = i;
                    break;
                }
            }

            int n = 0;

            result.addKicker(kicPair);

            for(int i : numberList){
                if(i != kicPair){
                    result.addKicker(i);
                    n++;
                }
                if(n >= 3){
                    break;
                }
            }
            return result;
        }

        return null;
    }

    private PokerRankingResult highCard(){
        PokerRankingResult result = new PokerRankingResult(PokerRankingList.HIGH_CARD);

        for(int i = 0; i < 5; i++){
            result.addKicker(numberList.get(i));
        }

        return result;
    }

    // 내부에서 결과를 계산하기 위해 준비하는 메소드
    private void ready(){
        flush = false;
        numberCount = new HashMap<>();
        HashMap<Character, Integer> shapeCount = new HashMap<>();
        numberList = new ArrayList<>();
        groupCounts = new HashMap<>();

        for(Card card : handCard){

            int number = card.getNumber();
            char shape = card.getShape();

            // 모든 숫자를 저장함
            numberList.add(number);

            // 숫자와 모양의 개수를 체크함
            if(numberCount.containsKey(number)){
                numberCount.put(number,numberCount.get(number)+1);
            }
            else{
                numberCount.put(number,1);
            }

            if(shapeCount.containsKey(shape)){
                shapeCount.put(shape,shapeCount.get(shape)+1);
            }
            else {
                shapeCount.put(shape,1);
            }
        }

        // 숫자를 내림차순으로 정렬함
        numberList.sort(Collections.reverseOrder());

        // 플러시 조건을 체크함
        for(char shape : shapeCount.keySet()){

            if(shapeCount.get(shape) >= 5){
                flush = true;
                flushNumberList = new ArrayList<>();
                // 플러시 조건을 만족한다면 해당하는 모양을 가진 카드들의 숫자만 저장함
                for(Card card : handCard){

                    if(card.getShape() == shape){
                        flushNumberList.add(card.getNumber());
                    }
                }

                // 숫자를 내림차순으로 정렬함
                flushNumberList.sort(Collections.reverseOrder());

                break;
            }
        }

        for(int i : numberCount.values()){
            if(groupCounts.containsKey(i)){
                groupCounts.put(i,groupCounts.get(i)+1);
            }
            else {
                groupCounts.put(i,1);
            }
        }
    }
}
