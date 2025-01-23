import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [S2] 베르트랑 공준
public class BOJ_4948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] arr = new boolean[123456 * 2 + 1];

        // 에라토스테네스의 체
        // 어떤 수의 약수에 제곱근보다 큰 약수가 있다면 그 약수와 짝을 이루는 약수는
        // 무조건 제곱근보다 작다. 그 짝을 이루는 약수는 이미 이전단계에서 처리가 되었으므로
        // 제곱근보다 큰 약수에 대해서는 검증할 필요가 없다.
        for(int i = 2; i * i < arr.length; i++) {
            if(!arr[i]) {
                for(int j = i + i; j < arr.length; j += i) {
                    arr[j] = true;
                }
            }
        }

        arr[0] = arr[1] = true;
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(n != 0) {
            int count = 0;
            for(int i = n + 1; i <= 2 * n; i++) {
                if(!arr[i]) {
                    count++;
                }
            }

            sb.append(count).append("\n");
            n = Integer.parseInt(br.readLine());
        }

        System.out.println(sb);
    }
}
