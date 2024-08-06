import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [G4] 가장 긴 바이토닉 부분 수열
public class BOJ_11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N];
        int[] increase = new int[N]; // 증가 수열
        int[] decrease = new int[N]; // 감소 수열
        Arrays.fill(increase, 1); // 최소 길이는 1이므로 1로 초기화
        Arrays.fill(decrease, 1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < N; i++) {
            for(int j = 0; j < i; j++) {
                // 현재값과 이전값들을 비교하여 가장 긴 수열 찾기
                if(input[i] > input[j]) {
                    increase[i] = Math.max(increase[i], increase[j] + 1);
                }

                // 감소 수열은 끝에서부터 시작
                if(input[N - i - 1] > input[N - j - 1]) {
                    decrease[N - i - 1] = Math.max(decrease[N - i - 1], decrease[N - j - 1] + 1);
                }
            }
        }

        int result = 0;
        // 모든 인덱스를 돌며 부분 증가 수열 + 부분 감소 수열의 최대값 찾기
        for(int i = 0; i < N; i++) {
            result = Math.max(result, increase[i] + decrease[i]);
        }

        // 고점이 되는 인덱스의 값은 증가수열에도, 감소수열에도 포함되어 계산 되었기 때문에 -1을 해준다.
        System.out.println(result - 1);
    }
}
