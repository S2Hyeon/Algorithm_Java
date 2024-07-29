import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// N과 M (5)
public class BOJ_15654 {
    static int N, M;
    static int[] numbers, input;
    static boolean[] selected;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N];
        numbers = new int[M];
        selected = new boolean[N];
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);

        permutation(0);
        System.out.println(sb);

    }

    private static void permutation(int index) {
        if(index == M) {
            for(int i = 0; i < M; i++) {
                sb.append(numbers[i]).append(" ");
            }

            sb.append("\n");
            return;
        }

        for(int i = 0; i < N; i++) {
            if(selected[i]) {
                continue;
            }

            numbers[index] = input[i];
            selected[i] = true;
            permutation(index + 1);
            selected[i] = false;
        }
    }

}
