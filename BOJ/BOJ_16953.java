import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [S2] A -> B
public class BOJ_16953 {
    static class Number {
        long value;
        int count;

        Number(long value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        Queue<Number> queue = new ArrayDeque<>();
        int result = -1;
        queue.offer(new Number(A, 0));

        while(!queue.isEmpty()) {
            Number n = queue.poll();
            if(n.value == B) {
                result = n.count + 1;
                break;
            }

            if(n.value * 2 <= B) {
                queue.offer(new Number(n.value * 2, n.count + 1));
            }

            if(n.value * 10 + 1 <= B) {
                queue.offer(new Number(n.value * 10 + 1, n.count + 1));
            }
        }

        System.out.println(result);
    }
}
