import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [G3] 파티
/*
    파티가 열리는 마을을 시작점으로 다익스트라 알고리즘을 수행한다.

    그래프는 2개를 사용한다.
        (1) 입력에서 주어지는 그래프
        (2) 입력에서 주어지는 그래프의 화살표 방향을 반전 시킨 그래프

    시작점을 파티가 열리는 마을로 하기 때문에
        (1)의 그래프는 파티 마을 -> 각각의 마을 최단거리를 구하는 그래프가 된다.
        (2)의 그래프는 각각의 마을 -> 파티 마을 최단거리를 구하는 그래프가 된다.

    (1)그래프를 이용한 각각의 마을까지 최단거리 + (2)그래프를 이용한 파티 마을까지 최단거리 중에서
    가장 큰 값이 정답이 된다.

 */
public class BOJ_1238 {

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        int partyTown = parseInt(st.nextToken()); // 파티 마을을 시작점으로
        ArrayList<Node>[] toEachTownGraph = new ArrayList[N + 1]; // 파티 마을 -> 각각의 마을 그래프
        ArrayList<Node>[] toPartyTownGraph = new ArrayList[N + 1]; // 각각의 마을 -> 파티 마을 그래프

        for(int i = 1; i <= N; i++) {
            toEachTownGraph[i] = new ArrayList<>();
            toPartyTownGraph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = parseInt(st.nextToken());
            int to = parseInt(st.nextToken());
            int weight = parseInt(st.nextToken());

            toEachTownGraph[from].add(new Node(to, weight)); // 파티 마을 -> 각각의 마을 그래프
            toPartyTownGraph[to].add(new Node(from, weight)); // 각각의 마을 -> 파티 마을 그래프
        }

        int[] toEachTownDistance = new int[N + 1]; // 파티 마을 -> 각각의 마을 최단 거리
        int[] toPartyTownDistance = new int[N + 1]; // 각각의 마을 -> 파티 마을 최단 거리

        dijkstra(toEachTownGraph, toEachTownDistance, partyTown); // 파티 마을을 시작점으로 하는 파티 마을 -> 각각의 마을 최단 거리 계산
        dijkstra(toPartyTownGraph, toPartyTownDistance, partyTown);// 파티 마을을 시작점으로 하는 각각의 마을 -> 파티 마을 최단 거리 계산

        int answer = 0;

        for(int i = 1; i <= N; i++) {
            answer = Math.max(answer, toEachTownDistance[i] + toPartyTownDistance[i]);
        }

        System.out.println(answer);
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }

    private static void dijkstra(ArrayList<Node>[] graph, int[] distance, int start) {
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
                if(distance[next.vertex] > distance[currentVertex] + next.weight) {
                    distance[next.vertex] = distance[currentVertex] + next.weight;
                    pq.offer(new Node(next.vertex, distance[next.vertex]));
                }
            }
        }
    }
}
