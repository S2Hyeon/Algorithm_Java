import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// [S2] 알고리즘 수업 - 깊이 우선 탐색 1
public class BOJ_24479 {

    static int[] visited;
    static ArrayList<Integer>[] edgeList;
    static int N, M, R, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        count = 1;

        visited = new int[N + 1];
        edgeList = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            edgeList[from].add(to);
            edgeList[to].add(from);
        }

        for(int i = 1; i <= N; i++) {
            Collections.sort(edgeList[i]);
        }

        dfs(R);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            sb.append(visited[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int cur) {
        visited[cur] = count++;

        for(int next : edgeList[cur]) {
            if(visited[next] == 0) {
                dfs(next);
            }
        }
    }
}
