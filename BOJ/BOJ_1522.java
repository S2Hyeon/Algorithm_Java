import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [S1] 문자열 교환
public class BOJ_1522 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int min = Integer.MAX_VALUE;
        int countA = 0;

        for(int i = 0; i < str.length(); i++) {
            if('a' == str.charAt(i)) {
                countA++;
            }
        }

        // a의 개수만큼의 윈도우로 b의 개수의 최소를 찾는다.
        // 윈도우 내 모든 요소가 a가 되어야 하므로 b의 개수만큼 교환이 필요하기 때문
        for(int i = 0; i < str.length(); i++) {
            int countB = 0;
            for(int j = i; j < i + countA; j++) {
                if('b' == str.charAt(j % str.length())) {
                    countB++;
                }
            }

            min = Math.min(min, countB);
        }

        System.out.println(min);
    }
}
