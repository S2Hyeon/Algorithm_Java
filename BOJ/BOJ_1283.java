import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// [S1] 단축키 지정
public class BOJ_1283 {

    static Set<Character> alphabet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        alphabet = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            sb.append(setKey(str)).append("\n");
        }

        System.out.println(sb);
    }

    private static String setKey(String str) {
        String[] strArr = str.split(" ");
        StringBuilder sb = new StringBuilder();
        int index = 0;
        boolean isFound = false;

        // 첫 글자 검사
        while(index < strArr.length) {
            String token = strArr[index]; // 단어
            char c = token.charAt(0); // 단어의 첫 글자

            if(!isFound) {
                if(!alphabet.contains(c)) { // 단축키에 없다면
                    alphabet.add(Character.toUpperCase(c)); // 단축키에 등록(대문자)
                    alphabet.add(Character.toLowerCase(c)); // 단축키에 등록(소문자)
                    StringBuilder newToken = new StringBuilder(token);
                    newToken.insert(0, "[");
                    newToken.insert(2, "]");
                    token = newToken.toString();
                    isFound = true;
                }
            }

            sb.append(token).append(" ");
            index++;
        }

        if(isFound) { // 첫 글자로 단축키가 지정되었다면
            return sb.toString();
        }

        // 초기화
        sb = new StringBuilder();
        index = 0;

        // 왼쪽부터 차례대로 알파벳 검사
        while(index < strArr.length) {
            String token = strArr[index];

            if(!isFound) {
                for(int i = 1; i < strArr[index].length(); i++) {
                    char c = token.charAt(i);
                    if(!alphabet.contains(c)) {
                        alphabet.add(Character.toUpperCase(c));
                        alphabet.add(Character.toLowerCase(c));
                        StringBuilder newToken = new StringBuilder(token);
                        newToken.insert(i, "[");
                        newToken.insert(i + 2, "]");
                        token = newToken.toString();
                        isFound = true;
                        break;
                    }
                }
            }

            sb.append(token).append(" ");
            index++;
        }

        return sb.toString();
    }
}
