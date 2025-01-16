import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [S1] 그림
public class BOJ_1926 {

    static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static boolean[][] visited;
    static int N, M, count, maxArea;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && board[i][j] == 1) {
                    int area = bfs(i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        System.out.println(count);
        System.out.println(maxArea);
    }

    private static int bfs(int startRow, int startCol) {
        Queue<Location> queue = new ArrayDeque<>();
        visited[startRow][startCol] = true;
        queue.offer(new Location(startRow, startCol));
        int area = 0;
        count++;

        while(!queue.isEmpty()) {
            Location curLocation = queue.poll();
            int curRow = curLocation.row;
            int curCol = curLocation.col;
            area++;

            for(int i = 0; i < dx.length; i++) {
                int nextRow = curRow + dx[i];
                int nextCol = curCol + dy[i];

                if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol] && board[nextRow][nextCol] == 1) {
                    queue.offer(new Location(nextRow, nextCol));
                    visited[nextRow][nextCol] = true;
                }
            }
        }

        return area;
    }

    private static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}
