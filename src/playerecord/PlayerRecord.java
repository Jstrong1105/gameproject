package playerecord;

import engine.GameHub;

import java.io.Serializable;
import java.util.HashMap;

public class PlayerRecord implements Serializable {

   private static final long serialVersionUID = 1L;

   private final HashMap<GameHub,Integer> clearCount = new HashMap<>();
   private final HashMap<GameHub,Integer> clearTime = new HashMap<>();

   public PlayerRecord(){

      for(GameHub name : GameHub.values()){
         clearCount.put(name,0);
         clearTime.put(name,Integer.MAX_VALUE);
      }
   }

   public int getCount(GameHub name){

      valueCheck(name);

      return clearCount.get(name);
   }

   public int getTime(GameHub name){

      valueCheck(name);

      return clearTime.get(name);
   }

   public void addCount(GameHub name){

      valueCheck(name);

      clearCount.put(name,clearCount.get(name)+1);
   }

   public void setTime(GameHub name, int time){
      valueCheck(name);

      if(clearTime.get(name) > time){
         clearTime.put(name,time);
      }
   }

   private void valueCheck(GameHub name){
      if(!clearCount.containsKey(name)){
         clearCount.put(name,0);
         clearTime.put(name,Integer.MAX_VALUE);
      }
   }
}
