import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ_1107 리모컨
public class BOJ_1107 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int min = 1000000;
        boolean[] broken = new boolean[10];

        if(M > 0) { // 고장난 버튼이 있다면
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        if(N == 100) { // 이동하려고 하는 채널이 현재채널(100) 일 때
            System.out.println(0);
            return;
        }

        for(int i = 0; i < 1000000; i++) { // 가능한 모든 채널에 대하여
            String number = String.valueOf(i);
            if(check(number, broken)) { // 채널숫자에 부서진 버튼이 없다면
                // 현재채널와 목표채널과의 차이 + 눌러야 하는 자리 수(5457 이면 4자리)
                min = Math.min(min, Math.abs(i - N) + number.length());
            }
        }

        min = Math.min(min, Math.abs(N - 100));

        System.out.println(min);

    }

    static boolean check(String number, boolean[] broken) {
        for(int j = 0; j < number.length(); j++) {
            if(broken[number.charAt(j) - '0']) {
                return false;
            }
        }

        return true;
    }
}
