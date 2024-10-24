import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [G5] 물병
/*
    2진수로 표현했을 때 1의 개수가 현재 보유한 물병들로 만들 수 있는 최소 물병이다.
    현재 보유한 물병 수에서 1씩 더해가며 2진수로 표현했을 때 1의 개수가 K개 이하인 수를 찾는다.
 */
public class BOJ_1052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int temp = N;
        while(true) {
            if(Integer.bitCount(temp) <= K) {
                break;
            }

            temp++;
        }

        System.out.println(temp - N);
    }
}
