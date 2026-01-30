package domain.base;

import util.InputHandler;

public interface GameOptionTemplate<T>{

    @FunctionalInterface
    interface OptionSetter<T>{
        void setOption(InputHandler input, T option);
    }

    int getNumber();
    String getName();
    String getExplain();
    OptionSetter<T> getSetter();

    default void setOption(InputHandler input, T option){
        getSetter().setOption(input,option);
    }

}
