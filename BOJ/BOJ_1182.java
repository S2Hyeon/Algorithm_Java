import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [S2] 부분수열의 합
public class BOJ_1182 {
    static int[] arr;
    static int N, S, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        count = 0;
        combination(0, 0);

        System.out.println(count);
    }

    private static void combination(int curIndex, int sum) {

        for(int i = curIndex; i < N; i++) {
            // S == 0일 때(처음 메서드에 진입했을 때) 중복되어 카운트되는 것을 방지하기 위해
            // 다음 경우의 수를 미리 계산해서 카운트를 늘려준다.
            if(sum + arr[i] == S) {
                count++;
            }

            combination(i + 1, sum + arr[i]);
        }
    }
}
