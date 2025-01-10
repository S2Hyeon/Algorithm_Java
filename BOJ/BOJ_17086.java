import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [S2] 아기 상어 2
public class BOJ_17086 {

    static class Location {
        int row, col, distanceFromShark;

        public Location(int row, int col, int distanceFromShark) {
            this.row = row;
            this.col = col;
            this.distanceFromShark = distanceFromShark;
        }
    }

    static int[][] map;
    static Queue<Location> queue;
    static boolean[][] visited;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int N, M, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        queue = new ArrayDeque<>();
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    queue.offer(new Location(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        // 상어의 위치들을 시작으로 각 칸까지의 거리를 구한다.
        bfs();
        System.out.println(max);
    }

    private static void bfs() {
        while(!queue.isEmpty()) {
            Location l = queue.poll();
            int curRow = l.row;
            int curCol = l.col;
            int distanceFromShark = l.distanceFromShark;
            max = Math.max(max, distanceFromShark);

            for(int i = 0; i < dx.length; i++) {
                int nextRow = curRow + dx[i];
                int nextCol = curCol + dy[i];

                // 특정 칸에 방문 표시가 이미 된 경우 = 해당 칸은 다른 상어로부터의 거리가 더 가깝다
                if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new Location(nextRow, nextCol, distanceFromShark + 1));
                }
            }
        }
    }

    private static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}
