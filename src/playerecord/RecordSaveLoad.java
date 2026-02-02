package playerecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * 데이터를 불러오고 저장하는 클래스
 */

public class RecordSaveLoad {

    public static HashMap<String,PlayerRecord> record = new HashMap<>();
    private static final Path path = Paths.get("C:/playerdata/players.ser");

    public static void saveRecord(){

        try{
            if(Files.notExists(path.getParent())){
                Files.createDirectories(path.getParent());
            }

            OutputStream os = Files.newOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(record);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadRecord(){

        // 외부 파일 불러오기
        try{

            if(!Files.exists(path)){
                return;
            }

            InputStream isr = Files.newInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(isr);

            record = (HashMap<String, PlayerRecord>) ois.readObject();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch(ClassNotFoundException e){
            throw new ClassCastException();
        }
    }
}
