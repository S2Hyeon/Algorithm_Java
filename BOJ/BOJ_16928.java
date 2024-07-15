import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// [G5] 뱀과 사다리 게임
public class BOJ_16928 {
    static Map<Integer, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        map = new HashMap<>(); // 뱀, 사다리 정보

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            map.put(from, to);
        }

        System.out.println(bfs());
    }

    static int bfs() {
        int count = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[101];
        queue.offer(1); // 1번 칸부터 시작
        visited[1] = true;

        while(!queue.isEmpty()) {
            int size = queue.size();
            while(--size >= 0) {
                int num = queue.poll();
                if(num == 100) {
                    return count;
                }

                // 주사위 굴려서 나올수 있는 모든 칸을 큐에 삽입
                for(int i = 1; i <= 6; i++) {
                    int nextNum = num + i;

                    // 뱀 혹은 사다리를 탈 수 있으면 해당 칸으로 이동
                    if(map.containsKey(nextNum)) {
                        nextNum = map.get(nextNum);
                    }

                    if(nextNum <= 100 && !visited[nextNum]) {
                        queue.offer(nextNum);
                        visited[nextNum] = true;
                    }
                }
            }

            count++;
        }

        return count;
    }
}
