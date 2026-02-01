package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 사용자에게 입력을 받아 처리하는 도구
 */
public class InputHandler {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 문자를 입력받는 메소드
    public static String readString(String prompt)
    {
        try
        {
            System.out.print(prompt + " : ");
            return br.readLine();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    // 숫자만 입력받는 메소드
    public static int readInt(String prompt)
    {
        while(true)
        {
            try
            {
                return Integer.parseInt(readString(prompt));
            }
            catch (NumberFormatException e)
            {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }

    // 숫자를 제한 범위 내에서 입력받는 메소드
    public static int readIntRange(String prompt, int min, int max)
    {
        while(true)
        {
            int num = readInt(prompt + " ( " + min + " ~ " + max + " )");

            if(num >= min && num <= max)
            {
                return num;
            }

            else
            {
                System.out.println(min + " ~ " + max + " 사이로 입력해주세요.");
            }
        }
    }

    // 버퍼를 비우는 메소드
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void clearBuffer()
    {
        try
        {
            while(br.ready()){
                br.read();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}