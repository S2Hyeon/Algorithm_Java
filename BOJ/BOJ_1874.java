import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// [S2] 스택 수열
public class BOJ_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] target = new int[N];
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            target[i] = Integer.parseInt(br.readLine());
        }

        int targetIndex = 0;
        for(int i = 1; i <= N; i++) {
            // 일단 스택에 넣고
            stack.push(i);
            sb.append("+").append("\n");

            // 스택의 최상위 요소가 목표수열의 index번째 값과 같다면, 다를때까지 pop을 한다.
            while(!stack.isEmpty() && stack.peek() == target[targetIndex]) {
                stack.pop();
                sb.append("-").append("\n");
                targetIndex++;
            }
        }

        // 목표수열의 index가 끝에 다다르지 못했다면 해당 수열은 만들수가 없다는 의미이다.
        if(targetIndex == N) {
            System.out.println(sb);
        } else {
            System.out.println("NO");
        }
    }
}
