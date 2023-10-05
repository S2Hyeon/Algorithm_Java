import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ_2606 바이러스
public class BOJ_2606 {
    static ArrayList<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        StringTokenizer st;
        adjList = new ArrayList[V + 1];

        for(int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }

        System.out.println(bfs(V));

    }

    public static int bfs(int V) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[V + 1];
        int cur = 1;
        int count = 0;
        queue.offer(cur);
        visited[cur] = true;

        while(!queue.isEmpty()) {
            cur = queue.poll();
            for(int i = 0, end = adjList[cur].size(); i < end; i++) {
                int ad = adjList[cur].get(i);
                if(visited[ad]) continue;

                visited[ad] = true;
                queue.offer(ad);
                count++;
            }
        }

        return count;
    }
}
