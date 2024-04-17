import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [S3] N과 M (2)
public class BOJ_15650 {
    static int N, M;
    static int[] numbers;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M];
        sb = new StringBuilder();

        combination(0, 1);
        System.out.println(sb);
    }

    private static void combination(int index, int cur) {
        if(index == M) {
            for(int i = 0; i < M; i++) {
                sb.append(numbers[i]).append(" ");
            }

            sb.append("\n");
            return;
        }

        for(int i = cur; i <= N; i++) {
            numbers[index] = i;
            combination(index + 1, i + 1);
        }
    }
}
