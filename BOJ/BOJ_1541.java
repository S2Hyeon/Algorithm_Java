import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// [S2] 잃어버린 괄호
// 최소값을 구해야 하므로 최대한 큰 값을 빼주어야 한다.
// 따라서 모든 더하기를 먼저 수행한 뒤, 모든 값을 빼준다.
public class BOJ_1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer minusToken = new StringTokenizer(br.readLine(), "-");
        int answer = Integer.MAX_VALUE;

        while(minusToken.hasMoreTokens()) {
            StringTokenizer plusToken = new StringTokenizer(minusToken.nextToken(), "+");
            int sum = 0;
            while(plusToken.hasMoreTokens()) { // 더하기 연산 먼저 수행
                sum += Integer.parseInt(plusToken.nextToken());
            }

            if(answer == Integer.MAX_VALUE) { // 첫번 째 숫자는 결과값에 그대로 대입한다.
                answer = sum;
            } else { // 이후의 숫자들은 모두 빼준다.
                answer -= sum;
            }
        }

        System.out.println(answer);
    }
}
