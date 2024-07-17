import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// [S2] 트리의 부모 찾기
public class BOJ_11725 {

    static ArrayList<Integer>[] adjList;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        parents = new int[N + 1];
        adjList = new ArrayList[N + 1];

        Arrays.fill(parents, -1);
        for(int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }

        for(int i = 1; i <= N; i++) {
            if(parents[i] == -1) {
                bfs(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= N; i++) {
            sb.append(parents[i]).append("\n");
        }

        System.out.println(sb);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int i = 0; i < adjList[cur].size(); i++) {
                int next = adjList[cur].get(i);
                if(parents[next] == -1) { // 다음 노드의 부모가 정해지지 않았다면
                    queue.offer(next); // 다음 노드를 큐에 넣고
                    parents[next] = cur; // 다음 노드의 부모를 현재 노드로 설정
                }
            }
        }
    }
}
