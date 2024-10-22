import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

// [G3] 최소비용 구하기 2
public class BOJ_11779 {
    static class Node implements Comparable<Node> {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return this.weight - n.weight;
        }
    }
    static int N, M;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        M = parseInt(br.readLine());
        graph = new ArrayList[N + 1];

        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = parseInt(st.nextToken());
            int to = parseInt(st.nextToken());
            int weight = parseInt(st.nextToken());

            graph[from].add(new Node(to, weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = parseInt(st.nextToken());
        int end = parseInt(st.nextToken());
        int[] history = new int[N + 1]; // 경로 추적 배열

        StringBuilder sb = dijkstra(history, start, end);
        tracePath(history, start, end, sb);

        System.out.println(sb);

    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }

    private static StringBuilder dijkstra(int[] history, int start, int end) {
        StringBuilder sb = new StringBuilder();
        int[] distance = new int[N + 1]; // start정점부터 각 정점까지 최단거리
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        pq.offer(new Node(start, distance[start]));

        while(!pq.isEmpty()) {
            int currentVertex = pq.poll().vertex;

            if(visited[currentVertex]) continue;
            visited[currentVertex] = true;

            for(Node next : graph[currentVertex]) {
                // 기존에 next정점까지 오는데 소모한 비용이 현재 정점에서 next정점까지 가는 비용보다 크다면
                if(distance[next.vertex] > distance[currentVertex] + next.weight) {
                    distance[next.vertex] = distance[currentVertex] + next.weight;
                    pq.offer(new Node(next.vertex, distance[next.vertex]));
                    history[next.vertex] = currentVertex; // next정점이 현재 정점에서 온 것임을 표시
                }
            }
        }

        sb.append(distance[end]).append("\n");

        return sb;
    }

    private static void tracePath(int[] history, int start, int end, StringBuilder sb) {
        Stack<Integer> stack = new Stack<>();

        while(end != start) { // 도착점부터 시작해서 경로 역추적
            stack.push(end);
            end = history[end];
        }

        stack.push(start);
        sb.append(stack.size()).append("\n");

        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
    }
}