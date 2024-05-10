import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// [G3] 소문난 칠공주
/*
    5 * 5 칸에서 무작위로 7명을 뽑은 뒤,
    7명이 서로 연결되어 있으면서 S의 개수가 4개 이상인지 판별한다.
 */
public class BOJ_1941 {
    static class Position {
        int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, answer;
    static final int SELECT_NUM = 7;
    static Position[] wholePosition;
    static Position[] selected;
    static char[][] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = 5;
        wholePosition = new Position[N * N]; // 모든 학생의 위치
        selected = new Position[SELECT_NUM]; // 무작위 학생의 위치
        input = new char[N][N];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++) {
                input[i][j] = str.charAt(j);
                wholePosition[i * N + j] = new Position(i, j);
            }
        }

        combination(0, 0);

        System.out.println(answer);
    }

    public static void combination(int cnt, int r) {
        if(r == SELECT_NUM) {
            if(bfs()) {
                answer++;
            }

            return;
        }

        if(N * N <= cnt) return;

        selected[r] = wholePosition[cnt];
        combination(cnt + 1, r + 1);
        combination(cnt + 1, r);
    }

    public static boolean bfs() {
        boolean[][] visited = new boolean[N][N];
        boolean[][] marked = new boolean[N][N]; // 무작위로 뽑힌 학생들 마킹
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Position> queue = new ArrayDeque<>();
        Position startPosition = selected[0];
        queue.offer(startPosition);
        visited[startPosition.row][startPosition.col] = true;
        int sCount = 0;
        int searchCount = 0;

        for(int i = 0; i < SELECT_NUM; i++) {
            Position position = selected[i];
            marked[position.row][position.col] = true;
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            while(--size >= 0) {
                Position curPos = queue.poll();
                int curRow = curPos.row;
                int curCol = curPos.col;
                if('S' == input[curRow][curCol]) {
                    sCount++;
                }

                searchCount++;

                for(int i = 0; i < dx.length; i++) {
                    int nextRow = curRow + dx[i];
                    int nextCol = curCol + dy[i];
                    // 마킹된 학생인지까지 확인
                    if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol] && marked[nextRow][nextCol]) {
                        queue.offer(new Position(nextRow, nextCol));
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }

        // S가 4개 이상이고 마킹된 모든 학생들을 탐색했는지
        return sCount >= 4 && searchCount == SELECT_NUM;
    }

    public static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

}