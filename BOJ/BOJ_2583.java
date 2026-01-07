import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

// [S1] 영역 구하기
public class BOJ_2583 {
    static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[][] board;
    static boolean[][] visited;

    static int M, N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[M][N];
        visited = new boolean[M][N];
        int count = 0;
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());
            int endRow = Integer.parseInt(st.nextToken());
            int endCol = Integer.parseInt(st.nextToken());

            // 좌표값이므로 x, y -> col, row 형태로 변환해서 저장 => 상하반전됨
            for(int j = startCol; j < endCol; j++) {
                for(int k = startRow; k < endRow; k++) {
                    board[j][k] = 1;
                }
            }
        }

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j] && board[i][j] == 0) {
                    count++;
                    list.add(bfs(i, j));
                }
            }
        }

        Collections.sort(list);

        System.out.println(count);
        for(int i : list) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static int bfs(int startRow, int startCol) {
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        Queue<Position> queue = new ArrayDeque<>();
        queue.offer(new Position(startRow, startCol));
        visited[startRow][startCol] = true;
        int count = 0;

        while(!queue.isEmpty()) {
            Position p = queue.poll();
            int curRow = p.row;
            int curCol = p.col;
            count++;

            for(int i = 0; i < dr.length; i++) {
                int nextRow = curRow + dr[i];
                int nextCol = curCol + dc[i];

                if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol] && board[nextRow][nextCol] == 0) {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new Position(nextRow, nextCol));
                }
            }
        }

        return count;
    }

    private static boolean isIn(int row, int col) {
        return row >= 0 && row < M && col >= 0 && col < N;
    }
}
