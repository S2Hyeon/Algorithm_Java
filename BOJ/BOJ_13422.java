import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ_13422 도둑
public class BOJ_13422 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 집의 개수
            int M = Integer.parseInt(st.nextToken()); // 연속된 집의 개수
            int K = Integer.parseInt(st.nextToken()); // 최소 돈

            int[] arr = new int[N + M - 1];
            st = new StringTokenizer(br.readLine());
            int index = 0;
            long sum = 0;
            int count = 0;

            if (N == M) { // N == M인 경우 모두 같은 집의 돈을 훔치게 된다.
                for (int i = 0; i < N; i++) {
                    sum += Integer.parseInt(st.nextToken());
                }

                if (sum < K) {
                    count = 1;
                }
            } else {
                for (int i = 0; i < N; i++) {
                    arr[index++] = Integer.parseInt(st.nextToken());
                }

                for (int i = 0; i < M - 1; i++) {
                    arr[index++] = arr[i]; // 배열 끝에 앞에서부터 M - 1개 이어 붙이기
                    sum += arr[i]; // 맨 처음 앞 M - 1개 미리 더해주기
                }

                for (int i = M - 1; i < arr.length; i++) {
                    sum += arr[i]; // 다음 값 더하기

                    if (sum < K) {
                        count++;
                    }

                    sum -= arr[i - M + 1]; // 윈도우의 맨 앞쪽 값 빼기
                }
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}
