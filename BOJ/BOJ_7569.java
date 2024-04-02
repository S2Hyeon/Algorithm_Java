import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [G5] 토마토
public class BOJ_7569 {
    static class Position {
        int r, c, h;

        public Position(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }
    }

    static int[][][] tomatoBox;
    static Queue<Position> queue = new ArrayDeque<>();
    static int M, N, H, zeroCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 열
        N = Integer.parseInt(st.nextToken()); // 행
        H = Integer.parseInt(st.nextToken()); // 높이

        tomatoBox = new int[N][M][H];

        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());

                for(int k = 0; k < M; k++) {
                    int n = Integer.parseInt(st.nextToken());

                    if(n == 1) {
                        queue.offer(new Position(j, k, i));
                    } else if(n == 0) {
                        zeroCount++; // 0의 개수 카운트
                    }

                    tomatoBox[j][k][i] = n;
                }
            }
        }

        int result = 0;

        // 모두 익은 토마토가 아니라면 bfs()로
        if(zeroCount != 0) {
            result = bfs();
        }

        // 익지 않은 토마토가 있다면 -1, 없다면 결과값
        System.out.println(zeroCount != 0 ? -1 : result);
    }

    private static int bfs() {
        int day = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int[] dz = {1, -1};

        while(!queue.isEmpty()) {
            int size = queue.size();
            day++;

            while(--size >= 0) {
                Position curPos = queue.poll();
                int curRow = curPos.r;
                int curCol = curPos.c;
                int curHeight = curPos.h;

                // 앞, 오른쪽, 뒤, 왼쪽 먼저 순회
                for(int i = 0; i < dx.length; i++) {
                    int nextRow = curRow + dx[i];
                    int nextCol = curCol + dy[i];

                    if(isIn(nextRow, nextCol) && tomatoBox[nextRow][nextCol][curHeight] == 0) {
                        queue.offer(new Position(nextRow, nextCol, curHeight));
                        tomatoBox[nextRow][nextCol][curHeight] = 1;
                        zeroCount--;
                    }
                }

                // 위, 아래 순회
                for(int i = 0; i < dz.length; i++) {
                    int nextHeight = curHeight + dz[i];

                    if(isIn(nextHeight) && tomatoBox[curRow][curCol][nextHeight] == 0) {
                        queue.offer(new Position(curRow, curCol, nextHeight));
                        tomatoBox[curRow][curCol][nextHeight] = 1;
                        zeroCount--;
                    }
                }
            }

            // 모든 토마토가 익었다면 걸린 일수 반환
            if(zeroCount == 0) {
                return day;
            }

        }

        return day;
    }

    // 행, 열 범위 체크
    private static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }

    // 높이 범위 체크
    private static boolean isIn(int height) {
        return height >= 0 && height < H;
    }
}
