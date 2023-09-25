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
            end = Math.max(end, cables[i]);
        }

        end++;

        while(start < end) {
            long mid = (start + end) / 2;
            long count = 0;

            for(int i = 0; i < K; i++) {
                count += cables[i] / mid;
            }

            if(count >= N) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }

        System.out.println(end - 1);
    }
}
