import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [G5] 수 고르기
public class BOJ_2230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int left = 0;
        int right = 0;
        int min = Integer.MAX_VALUE;

        while(left <= right && right < N) {
            // 배열 내 원소가 음수일 수 있기 때문에 절대값으로 구한다.
            int sub = Math.abs(arr[right] - arr[left]);
            if(sub >= M) { // 두 수의 차이가 M 이상이라면
                min = Math.min(min, sub);
                left++;
            } else {
                right++;
            }
        }

        System.out.println(min);
    }
}
