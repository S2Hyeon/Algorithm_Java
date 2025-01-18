import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

// [S2] 키로거
public class BOJ_5397 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 0; tc < T; tc++) {
            String str = br.readLine();
            LinkedList<Character> list = new LinkedList<>();
            ListIterator<Character> listIterator = list.listIterator();

            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                switch(c) {
                    case '<':
                        left(listIterator);
                        break;
                    case '>':
                        right(listIterator);
                        break;
                    case '-':
                        remove(listIterator);
                        break;
                    default:
                        listIterator.add(c);
                        break;
                }
            }

            for(Character character : list) {
                sb.append(character);
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void left(ListIterator<Character> listIterator) {
        if(listIterator.hasPrevious()) {
            listIterator.previous();
        }
    }

    private static void right(ListIterator<Character> listIterator) {
        if(listIterator.hasNext()) {
            listIterator.next();
        }
    }

    private static void remove(ListIterator<Character> listIterator) {
        if(listIterator.hasPrevious()) {
            listIterator.previous();
            listIterator.remove();
        }
    }
}
