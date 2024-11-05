import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [G5] 용액 합성하기
public class BOJ_14921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        int answer = 0;

        for(int i = 0; i < N; i++) {
            int left = i + 1;
            int right = N - 1;

            while(left <= right) {
                int mid = (left + right) / 2;
                // 특성값이 0과 가까운지 비교하기 위해 절대값을 사용
                int sum = Math.abs(arr[i] + arr[mid]);

                if(sum < min) {
                    answer = arr[i] + arr[mid]; // 실제 특성값을 답으로 저장
                    min = sum;
                }

                if(arr[i] + arr[mid] < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        System.out.println(answer);
    }
}
