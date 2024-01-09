import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ_2211 네트워크 복구
public class BOJ_2211 {

    static class Node implements Comparable<Node> {
        int vertex, weight;
        Node next;

        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }

        @Override
        public int compareTo(Node o) {
            if (o.weight == this.weight) {
                return this.vertex - o.vertex;
            }

            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] D = new int[N + 1];     // 1번 -> N번 까지 거리의 최소 비용
        int[] recoveryVertex = new int[N + 1]; // N번 까지 최소 비용으로 오는 직전 정점(중간 정점)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node[] adjList = new Node[N + 1];
        boolean[] visited = new boolean[N + 1];

        Arrays.fill(D, Integer.MAX_VALUE);
        int start = 1; // 1번이 시작 정점(슈퍼컴퓨터)
        int result = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[A] = new Node(B, weight, adjList[A]);
            adjList[B] = new Node(A, weight, adjList[B]); // 무방향 그래프 이므로 교차된 회선도 추가해준다.
        }

        pq.offer(new Node(start, 0, adjList[start]));
        D[start] = 0;

        while (!pq.isEmpty()) {
            Node minNode = pq.poll();
            int minVertex = minNode.vertex;

            if (visited[minVertex]) {
                continue;
            }

            visited[minVertex] = true;

            for (Node temp = adjList[minVertex]; temp != null; temp = temp.next) {
                if (!visited[temp.vertex] && D[temp.vertex] > D[minVertex] + temp.weight) {
                    D[temp.vertex] = D[minVertex] + temp.weight;
                    // 어떤 정점을 거쳐 왔을 때 최소 비용이 갱신 됐으므로 중간 정점과 현재 정점을 잇는 간선이 필요하다.
                    // 따라서 거치게 된 중간 정점을 저장한다.
                    recoveryVertex[temp.vertex] = minVertex;
                    pq.offer(new Node(temp.vertex, D[temp.vertex], adjList[temp.vertex]));
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (recoveryVertex[i] != 0) {
                result++;
                sb.append(recoveryVertex[i]).append(" ").append(i).append("\n");
            }
        }

        System.out.println(result);
        System.out.println(sb);
    }
}
