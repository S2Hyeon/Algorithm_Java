import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [S2] N과 M (12)
public class BOJ_15666 {
    static int N, M;
    static int[] numbers, input;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N];
        numbers = new int[M];
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);

        dfs(0, 0);
        System.out.println(sb);

    }

    private static void dfs(int index, int select) {
        if(select == M) {
            for(int i = 0; i < M; i++) {
                sb.append(numbers[i]).append(" ");
            }

            sb.append("\n");
            return;
        }

        int prev = -1;
        for(int i = index; i < N; i++) {
            int next = input[i];
            if(prev != next) { // 수열 내의 중복된 숫자는 넣지 않는다.
                prev = next;
                numbers[select] = input[i];
                dfs(i, select + 1);
            }
        }
    }
}
