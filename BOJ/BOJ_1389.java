import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// [S1] 케빈 베이컨의 6단계 법칙
public class BOJ_1389 {
    static ArrayList<Integer>[] edgeList;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList[N + 1];
        int min = Integer.MAX_VALUE;
        int answer = 0;

        for(int i = 0; i <= N; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            edgeList[A].add(B);
            edgeList[B].add(A);
        }

        for(int i = 1; i <= N; i++) {
            int sum = 0; // 케빈 베이컨 수
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;
                sum += find(i, j);
            }

            if(min > sum) { // 최소 케빈 베이컨 수이면
                min = sum; // 최소 값 갱신
                answer = i; // 유저 번호 갱신
            }
        }

        System.out.println(answer);
    }

    private static int find(int start, int target) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        queue.offer(start);
        visited[start] = true;
        int cnt = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            while(--size >= 0) {
                int cur = queue.poll();
                if(cur == target) {
                    return cnt;
                }

                for(int i = 0; i < edgeList[cur].size(); i++) {
                    int next = edgeList[cur].get(i);
                    if(!visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }

            cnt++;
        }

        return cnt;
    }
}
