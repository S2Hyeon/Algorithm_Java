import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [G4] 테트로미노
public class BOJ_14500 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int row, int col, int count, int sum) {
        if(count == 4) {
            answer = Math.max(answer ,sum);
            return;
        }

        for(int i = 0; i < dx.length; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];
            if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                if(count == 2) { // ㅜ 모양을 만들기 위해 2번째 칸에서 row, col 유지한 상태로 탐색
                    visited[nextRow][nextCol] = true;
                    dfs(row, col, count + 1, sum + map[nextRow][nextCol]);
                    visited[nextRow][nextCol] = false;
                }

                visited[nextRow][nextCol] = true;
                dfs(nextRow, nextCol, count + 1, sum + map[nextRow][nextCol]);
                visited[nextRow][nextCol] = false;
            }
        }
    }

    private static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}
