package domain.minesweeper;

/**
 * 지뢰찾기 옵션 값
 */
public class MinesweeperOption {

    private final int MIN_SIZE = 10;
    private final int MAX_SIZE = 20;
    private int size = MIN_SIZE;
    int getSize() { return size; }

    public void setSize(int size)
    {
        if(size < MIN_SIZE || size > MAX_SIZE){
            throw new IllegalArgumentException("허용하지 않는 값입니다.");
        }
        this.size = size;
    }

    private final int MIN_WEIGHT = 1;
    private final int MAX_WEIGHT = 3;
    private int weight = MIN_WEIGHT;
    int getWeight() { return weight; }

    public void setWeight(int weight)
    {
        if(weight < MIN_WEIGHT || weight > MAX_WEIGHT){
            throw new IllegalArgumentException("허용하지 않는 값입니다.");
        }
        this.weight = weight;
    }
}
