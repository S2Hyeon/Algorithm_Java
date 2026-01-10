import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// [G4] 숨바꼭질 4
public class BOJ_13913 {

    static int[] history;
    static final int MAX_LENGTH = 100001;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        history = new int[MAX_LENGTH];

        int result = bfs();
        System.out.println(result);
        traceHistory(result);
    }

    private static int bfs() {
        int[] move = {1, -1, 2};
        boolean[] visited = new boolean[MAX_LENGTH];
        Queue<Integer> queue = new ArrayDeque<>();
        visited[N] = true;
        queue.offer(N);
        int count = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            count++;
            while(size-- > 0) {
                int curNum = queue.poll();
                if(curNum == K) {
                    return count - 1;
                }

                for(int i = 0; i < move.length; i++) {
                    int nextNum = curNum + move[i];
                    if(i == 2) {
                        nextNum = curNum * move[i];
                    }

                    if(isIn(nextNum) && !visited[nextNum]) {
                        queue.offer(nextNum);
                        visited[nextNum] = true;
                        history[nextNum] = curNum;
                    }
                }
            }
        }

        return count;
    }

    private static boolean isIn(int n) {
        return n >= 0 && n < MAX_LENGTH;
    }

    private static void traceHistory(int time) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        stack.push(K);
        int index = K;

        for(int i = 0; i <= time; i++) {
            stack.push(history[index]);
            index = history[index];
        }

        stack.pop();

        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }

}
