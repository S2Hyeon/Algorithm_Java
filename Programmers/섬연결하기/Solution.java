package 섬연결하기;

import java.util.*;

// Lv. 3 섬 연결하기
class Solution {
    class Bridge implements Comparable<Bridge> {
        int from, to, cost;

        public Bridge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bridge b) {
            return this.cost - b.cost;
        }
    }

    int[] parents;

    public int find(int n) {
        if(parents[n] == n) {
            return n;
        }

        return parents[n] = find(parents[n]);
    }

    public boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) {
            return false;
        }

        parents[rootA] = rootB;
        return true;
    }

    public int solution(int n, int[][] costs) {
        int answer = 0;
        PriorityQueue<Bridge> pq = new PriorityQueue<>();
        parents = new int[n];
        for(int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for(int i = 0; i < costs.length; i++) {
            pq.offer(new Bridge(costs[i][0], costs[i][1], costs[i][2]));
        }

        while(!pq.isEmpty()) {
            Bridge b = pq.poll();
            if(union(b.from, b.to)) {
                answer += b.cost;
            }
        }

        return answer;
    }
}
