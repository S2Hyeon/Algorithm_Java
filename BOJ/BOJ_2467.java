import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [G5] 용액
public class BOJ_2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int answer1 = 0;
        int answer2 = 0;

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;

        // 첫번째 용액 arr[i]는 고정, 두번째 용액을 arr[i + 1]부터 이분탐색으로 찾는다.
        for(int i = 0; i < N; i++) {
            int left = i + 1;
            int right = N - 1;

            while(left <= right) {
                int mid = (left + right) / 2;
                int sum = Math.abs(arr[i] + arr[mid]);

                if(sum < min) { // 혼합 결과가 기존보다 0에 가깝다면
                    min = sum;
                    answer1 = arr[i];
                    answer2 = arr[mid];
                }

                // 용액 혼합 결과가 음수면 0에 가까워지기 위해 더 큰 값을 탐색해야 한다.
                if(arr[i] + arr[mid] < 0) {
                    left = mid + 1; // 오른쪽 탐색
                } else {
                    right = mid - 1;
                }
            }
        }

        System.out.println(answer1 + " " + answer2);
    }
}
