import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ_1654 랜선 자르기
public class BOJ_1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long[] cables = new long[K];
        long start = 1;
        long end = 0;

        for(int i = 0; i < K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            end = Math.max(end, cables[i]); // 케이블의 최대 길이를 끝 값으로
        }

        end++;

        while(start < end) {
            long mid = (start + end) / 2;
            long count = 0;

            for(int i = 0; i < K; i++) {
                count += cables[i] / mid; // 현재 길이값(mid)으로 케이블당 몇 개 만들 수 있는지 합산
            }

            if(count >= N) { // 케이블 개수가 목표 개수보다 크거나 같다면
                start = mid + 1; // 길이값(mid)이 작다는 뜻이므로 시작 값 조정
            }
            else {
                end = mid; // 작다면 길이값이 크다는 뜻이므로 끝 값 조정
            }
        }

        System.out.println(end - 1); // upper bound 값을 찾은 것이므로 -1을 해준다.
    }
}
