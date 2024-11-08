import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// [G3] 중량제한
public class BOJ_1939 {

    static class Edge {
        int to, weightLimit;

        public Edge(int to, int weightLimit) {
            this.to = to;
            this.weightLimit = weightLimit;
        }

    }

    static ArrayList<Edge>[] adjList;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1];

        for(int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weightLimit = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, weightLimit));
            adjList[to].add(new Edge(from, weightLimit));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int left = 1;
        int right = 1_000_000_000;
        int answer = 0;

        while(left <= right) {
            int mid = (left + right) / 2; // 가능한 최대 무게

            if(bfs(start, end, mid)) { // 현재 무게(mid)로 도착점에 도달할 수 있다면
                left = mid + 1; // 더 큰 무게로도 도달할 수 있는지 확인하기 위해 오른쪽 탐색
                answer = mid; // 현재 무게가 최대무게일 수 있으므로 정답에 저장
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean bfs(int start, int end, int maxWeight) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int i = 0; i < adjList[cur].size(); i++) {
                Edge nextEdge = adjList[cur].get(i);
                int nextVertex = nextEdge.to;
                int nextWeightLimit = nextEdge.weightLimit; // 다음 섬으로 가기 위한 다리의 중량제한
                // 현재 무게(maxWeight)로 다음 섬까지 갈 수 있다면
                if(!visited[nextVertex] && nextWeightLimit >= maxWeight) {
                    if(nextVertex == end) {
                        return true;
                    }
                    queue.offer(nextVertex);
                    visited[nextVertex] = true;
                }
            }
        }

        return false;
    }
}
