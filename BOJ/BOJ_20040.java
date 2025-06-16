import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [G4] 사이클 게임
public class BOJ_20040 {

    static int[] parents;
    static int N, M;

    private static void init() {
        for(int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    private static int find(int n) {
        if(parents[n] == n) {
            return n;
        }

        return parents[n] = find(parents[n]);
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) {
            return false;
        }

        parents[rootB] = rootA;

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N];
        boolean isFinished = false;
        int count = 1;

        init();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if(!union(v, e)) {
                isFinished = true;
                break;
            }

            count++;
        }

        if(!isFinished) {
            count = 0;
        }

        System.out.println(count);
    }
}