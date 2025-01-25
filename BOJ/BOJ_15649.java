import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [S3] N과 M (1)
public class BOJ_15649 {
    static int[] numbers;
    static boolean[] selected;
    static int N, M;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        numbers = new int[M];
        selected = new boolean[N + 1];

        permutation(0);
        System.out.println(sb);

    }

    private static void permutation(int index) {
        if(index == M) {
            for(int number : numbers) {
                sb.append(number).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i <= N; i++) {
            if(!selected[i]) {
                numbers[index] = i;
                selected[i] = true;
                permutation(index + 1);
                selected[i] = false;
            }
        }
    }
}
