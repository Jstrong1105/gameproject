package domain.base;

public interface GameOptionTemplate<T>{

    @FunctionalInterface
    interface OptionSetter<T>{
        void setOption(T option);
    }

    int getNumber();
    String getName();
    String getExplain();
    OptionSetter<T> getSetter();

    default void setOption(T option){
        getSetter().setOption(option);
    }

}
