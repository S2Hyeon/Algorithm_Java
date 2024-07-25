import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [G4] 최소 스패닝 트리
public class BOJ_1197 {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }

    static PriorityQueue<Edge> pq;
    static int[] parents;
    static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pq = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parents = new int[V + 1];
        int result = 0;
        init();

        for(int i = 0 ; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(from, to, weight));
        }

        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            if(union(e.from, e.to)) {
                result += e.weight;
            }
        }

        System.out.println(result);
    }

    private static void init() {
        for(int i = 1; i <= V; i++) {
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

        if(rootA != rootB) {
            parents[rootB] = rootA;
            return true;
        }

        return false;
    }
}
