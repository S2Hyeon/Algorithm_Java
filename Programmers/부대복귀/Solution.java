package 부대복귀;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// Lv. 3 부대복귀
class Solution {
    class Node implements Comparable<Node> {
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

    /*
        부대원들이 부대로 복귀하기 위한 최단 거리 = 부대에서 부대원들까지의 최단 거리
        다익스트라 알고리즘으로 부대를 시작점으로 모든 정점에 대한 최단 거리 구한다.
        부대원 위치별로 계산된 최단 거리를 정답 배열에 넣는다.

        roads -> 간선 정보
        sources -> 부대원 위치 -> 목적지
        destination -> 목적지 -> 시작점
    */
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        ArrayList<Node>[] graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < roads.length; i++) {
            int from = roads[i][0];
            int to = roads[i][1];

            graph[from].add(new Node(to, 1));
            graph[to].add(new Node(from, 1));
        }

        int[] distance = dijkstra(graph, n, destination);

        for(int i = 0; i < distance.length; i++) {
            if(distance[i] == Integer.MAX_VALUE) {
                distance[i] = -1;
            }
        }

        for(int i = 0; i < sources.length; i++) {
            answer[i] = distance[sources[i]];
        }

        return answer;
    }

    private int[] dijkstra(ArrayList<Node>[] graph, int n, int start) {
        int[] d = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(d, Integer.MAX_VALUE);

        d[start] = 0;
        pq.offer(new Node(start, d[start]));

        while(!pq.isEmpty()) {
            int currentVertex = pq.poll().vertex;

            if(visited[currentVertex]) {
                continue;
            }

            visited[currentVertex] = true;

            for(Node next : graph[currentVertex]) {
                if(d[next.vertex] > d[currentVertex] + next.weight) {
                    d[next.vertex] = d[currentVertex] + next.weight;
                    pq.offer(new Node(next.vertex, d[next.vertex]));
                }
            }
        }

        return d;
    }
}
