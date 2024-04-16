import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// [S2] 연결 요소의 개수
public class BOJ_11724 {
    static int[] parents;

    private static int find(int n) {
        if(parents[n] == n) {
            return n;
        }

        return parents[n] = find(parents[n]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA != rootB) {
            parents[rootB] = rootA;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        Set<Integer> set = new HashSet<>();

        for(int i = 1; i <= N; i++) {
            parents[i] = i;
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
            set.add(parents[i]);
        }

        System.out.println(set.size());
    }
}
