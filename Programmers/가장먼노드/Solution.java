package 가장먼노드;

// Lv. 3 가장 먼 노드
import java.util.*;
class Solution {
    class Vertex {
        int no, count;

        public Vertex(int no, int count) {
            this.no = no;
            this.count = count;
        }
    }

    ArrayList<Integer>[] map;
    PriorityQueue<Integer> pq;

    public int solution(int n, int[][] edge) {
        int answer = 0;
        map = new ArrayList[n + 1];
        pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        for(int i = 0; i < edge.length; i++) {
            int from = edge[i][0];
            int to = edge[i][1];
            map[from].add(to);
            map[to].add(from);
        }

        bfs(n);
        int maxNum = pq.peek();
        while(pq.peek() == maxNum) {
            answer++;
            pq.poll();
        }

        return answer;
    }

    private void bfs(int n) {
        boolean[] visited = new boolean[n + 1];
        Queue<Vertex> q = new ArrayDeque<>();
        visited[1] = true;
        q.offer(new Vertex(1, 0));

        while(!q.isEmpty()) {
            Vertex curVertex = q.poll();
            int curNo = curVertex.no;
            int curCount = curVertex.count;
            pq.offer(curCount);

            for(int i = 0; i < map[curNo].size(); i++) {
                int nextNo = map[curNo].get(i);
                if(!visited[nextNo]) {
                    visited[nextNo] = true;
                    q.offer(new Vertex(nextNo, curCount + 1));
                }
            }
        }
    }
}
