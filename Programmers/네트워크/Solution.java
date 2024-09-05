package 네트워크;

import java.util.HashSet;
import java.util.Set;

// Lv. 3 네트워크
class Solution {
    int[] parents;

    public void init(int n) {
        parents = new int[n];
        for(int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    public int find(int n) {
        if(parents[n] == n) {
            return n;
        }

        return parents[n] = find(parents[n]);
    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) {
            return;
        }

        parents[rootB] = rootA;
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        init(n);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    continue;
                }
                if(computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        // parents 배열의 root 정렬(각 노드들의 최상위 루트를 직접 지정하게 함)
        for(int i = 0; i < n; i++) {
            find(i);
        }

        // 최상위 루트의 개수 구하기
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            set.add(parents[i]);
        }
        return set.size();
    }
}
