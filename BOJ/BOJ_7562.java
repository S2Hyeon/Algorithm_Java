import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [S1] 나이트의 이동
public class BOJ_7562 {

    static class Position {
        int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[][] board;
    static int[] dr = {-2, -2, -1, 1, 2, 2, 1, -1};
    static int[] dc = {-1, 1, 2, 2, 1, -1, -2, -2};
    static int N;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int endRow = Integer.parseInt(st.nextToken());
            int endCol = Integer.parseInt(st.nextToken());

            board = new int[N][N];

            sb.append(bfs(startRow, startCol, endRow, endCol)).append("\n");
        }

        System.out.println(sb);
    }

    private static int bfs(int startRow, int startCol, int endRow, int endCol) {
        boolean[][] visited = new boolean[N][N];
        Queue<Position> queue = new ArrayDeque<>();
        int count = 0;
        visited[startRow][startCol] = true;
        queue.offer(new Position(startRow, startCol));

        while(!queue.isEmpty()) {
            int size = queue.size();
            count++;

            while(size-- > 0) {
                Position p = queue.poll();
                int curRow = p.row;
                int curCol = p.col;

                if(curRow == endRow && curCol == endCol) {
                    return count - 1;
                }

                for(int i = 0; i < dr.length; i++) {
                    int nextRow = curRow + dr[i];
                    int nextCol = curCol + dc[i];

                    if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                        queue.offer(new Position(nextRow, nextCol));
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }

        return count;
    }


    private static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }
}
