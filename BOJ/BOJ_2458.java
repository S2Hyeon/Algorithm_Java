import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// [G4] 키 순서
/*
 본인의 키가 몇번 째 인지 알 수 있다
   => 본인보다 키가 작은사람과 큰 사람 모두 그래프상 본인과 연결되어야 한다.
      즉, 키가 작은사람들은 모두 본인에게 접근 가능해야하고,
      본인은 본인보다 큰 사람에게 모두 접근 가능해야 한다.

 따라서, N명이 있을 때 본인의 키가 몇번 째 인지 알 수 있으려면
 본인에게 접근 가능한 인원 수 + 본인이 접근 가능한 인원 수 = N - 1이 된다.
 */
public class BOJ_2458 {

    static boolean[][] canGo;
    static ArrayList<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        canGo = new boolean[N + 1][N + 1];
        adjList = new ArrayList[N + 1];

        for(int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList[start].add(end);
        }

        for(int i = 1; i <= N; i++) {
            dfs(i, i);
        }

        int answer = 0;

        for(int i = 1; i <= N; i++) {
            int sum = 0;

            for(int j = 1; j <= N; j++) {
                if(i == j) continue;

                if(canGo[i][j]) { // 본인이 접근 가능한 인원 수 카운트
                    sum++;
                }

                if(canGo[j][i]) { // 본인에게 접근 가능한 인원 수 카운트
                    sum++;
                }
            }

            if(sum == N - 1) { // 자신을 제외한 모든 인원과 연결되어 있는가
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int start, int cur) {
        canGo[start][cur] = true;

        for(int i = 0; i < adjList[cur].size(); i++) {
            int next = adjList[cur].get(i);
            if(!canGo[start][next]) {
                dfs(start, next);
            }
        }
    }
}
