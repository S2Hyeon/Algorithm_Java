import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// BOJ_20303 할로윈의 양아치
public class BOJ_20303 {
    static class Group {
        int memberNumber, candies;

        public Group(int memberNumber, int candies) {
            this.memberNumber = memberNumber; // 친구 그룹에 포함되어 있는 친구들 수
            this.candies = candies; // 친구 그룹의 받은 사탕의 갯수 합
        }
    }
    static int N, M, K;
    static int[] parents;

    static public void initParents() {
        parents = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    static public int find(int n) {
        if(parents[n] == n) {
            return n;
        }

        return parents[n] = find(parents[n]);
    }

    static public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA != rootB) {
            parents[rootB] = rootA;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] candies = new int[N + 1]; // 받은 사탕 갯수 배열
        Map<Integer, Group> group = new HashMap<>(); // 친구 그룹
        initParents();
        st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i <= N; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        for(int i = 1; i <= N; i++) {
            find(i);
        }

        for(int i = 1; i <= N; i++) {
            if(group.containsKey(parents[i])) { // 친구 그룹에 이미 있는 그룹이라면
                Group g = group.get(parents[i]);
                group.put(parents[i], new Group(g.memberNumber + 1, g.candies + candies[i]));
            }
            else {
                group.put(parents[i], new Group(1, candies[i]));
            }
        }

        List<Group> groupList = new ArrayList<>();
        for(Integer i : group.keySet()) {
            groupList.add(group.get(i));
        }

        int[][] dp = new int[group.size() + 1][K];
        for(int i = 1; i <= group.size(); i++) {
            for(int j = 1; j < K; j++) { // j : 우는 아이들 수
                if(groupList.get(i - 1).memberNumber > j) {
                    dp[i][j] = dp[i - 1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - groupList.get(i - 1).memberNumber] + groupList.get(i - 1).candies);
                }
            }
        }

        System.out.println(dp[group.size()][K - 1]);
    }
}