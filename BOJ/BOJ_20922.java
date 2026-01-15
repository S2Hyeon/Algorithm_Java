import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [S1] 겹치는 건 싫어
public class BOJ_20922 {
    /*
        투 포인터
        현재 숫자(right)의 개수 카운트를 일단 증가시킨다.
        현재 숫자(right) 개수 > K이면
        위 조건 만족시킬때까지 left를 증가시키면서 left 숫자 개수 카운트 감소시키기
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] input = new int[N];
        int[] count = new int[100001];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int result = 0;

        while(right < N) {
            count[input[right]]++;

            while(count[input[right]] > K) {
                count[input[left]]--;
                left++;
            }

            result = Math.max(result, right - left + 1);
            right++;
        }

        System.out.println(result);
    }
}
