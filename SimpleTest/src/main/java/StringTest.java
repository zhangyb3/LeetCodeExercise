import java.util.Locale;
import java.util.Scanner;

public class StringTest {

    public static void lastWordLength(String str){
        String[] strings = str.split(" ");
        System.out.println(strings[strings.length-1].length());
    }

    public static int characterTimes(String str, Character character){
        int times = 0;
        str = str.toLowerCase();
        if(character >= 'A' && character <= 'Z')
            character = (char)(character - 'A' + 'a');
        for (int count = 0; count < str.length(); count++){
            str.toLowerCase(Locale.ROOT);
            Character s = str.charAt(count);
            if(s.equals(character)){
                times++;
            }
        }
        return times;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
//        StringTest.lastWordLength(str);
        Character c = sc.nextLine().charAt(0);
        System.out.println(StringTest.characterTimes(str,c));
    }

}
