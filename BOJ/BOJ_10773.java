import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// [S4] 제로
public class BOJ_10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        int K = Integer.parseInt(br.readLine());

        for(int i = 0; i < K; i++) {
            int next = Integer.parseInt(br.readLine());

            if(next == 0) {
                stack.pop();
            } else {
                stack.push(next);
            }
        }

        int sum = 0;
        while(!stack.isEmpty()) {
            sum += stack.pop();
        }

        System.out.println(sum);
    }
}
