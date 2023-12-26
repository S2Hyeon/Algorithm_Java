import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// BOJ_2295 세 수의 합
public class BOJ_2295 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] U = new int[N];
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            U[i] = Integer.parseInt(br.readLine());
        }

        /*
        x + y + z = k
        => x + y = k - z
        */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                set.add((U[i] + U[j])); // x + y 의 모든 조합 저장
            }
        }

        Arrays.sort(U);

        for (int i = N - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int temp = U[i] - U[j]; // k - z
                if (set.contains(temp)) { // k - z가 x + y와 일치하는가?
                    System.out.println(U[i]);
                    return;
                }
            }
        }
    }
}
