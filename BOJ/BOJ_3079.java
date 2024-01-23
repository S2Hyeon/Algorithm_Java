import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ_3079 입국심사
public class BOJ_3079 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 입국심사대 개수
        int M = Integer.parseInt(st.nextToken()); // 친구 수
        int[] arr = new int[N]; // 입국심사대 배열
        long left = 1;
        long right = 1_000_000_000L * 1_000_000_000; // 최대 시간 : N = 1, M = 10^9, T1 = 10^9일 때

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        while (left < right) {
            long mid = (left + right) / 2;
            long sum = 0;
            for (int i = 0; i < N; i++) {
                sum += mid / arr[i];
                if (sum > M) { // 심사가능 한 인원수가 기준값을 넘어버리면 break(오버 플로우 방지)
                    break;
                }
            }

            if (sum < M) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        System.out.println(left);
    }
}
