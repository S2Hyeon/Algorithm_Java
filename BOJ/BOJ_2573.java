import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [G4] BOJ_2573 빙산
public class BOJ_2573 {
    static class Ice {
        int height, row, col;

        public Ice(int height, int row, int col) {
            this.height = height;
            this.row = row;
            this.col = col;
        }

        public Ice(int row, int col) {
            this(0, row, col);
        }
    }

    static Queue<Ice> remain;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        remain = new ArrayDeque<>(); // 남아있는 빙산들
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] != 0) {
                    remain.offer(new Ice(map[i][j], i, j));
                }
            }
        }

        int result = 0;
        while(true) {
            int size = remain.size();
            int[][] nextMap = new int[N][M]; // 다음 년차 맵

            for(int i = 0; i < size; i++) {
                Ice ice = remain.poll();
                int row = ice.row;
                int col = ice.col;
                melt(ice); // 현재 빙산 녹이기
                nextMap[row][col] = ice.height;

                if(ice.height > 0) { // 녹은 빙산이 없어지지 않았다면
                    remain.offer(ice); // 다시 큐에 넣어준다
                }
            }

            if(remain.isEmpty()) { // 빙산이 다 녹을때까지 분리되지 않은 경우
                result = 0;
                break;
            }

            result++;
            map = nextMap;
            Ice ice = remain.peek();

            if(isDivide(ice.row, ice.col)) { // 빙산이 분리됐다면
                break;
            }

        }
        System.out.println(result);

    }

    private static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }

    private static void melt(Ice ice) {
        int count = 0; // 녹는 수치
        int row = ice.row;
        int col = ice.col;

        for(int i = 0; i < dx.length; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];

            if(isIn(nextRow, nextCol) && map[nextRow][nextCol] == 0) {
                count++; // 바닷물과 접해있는 만큼 count++
            }
        }

        if(map[row][col] - count < 0) {
            ice.height = 0;
        } else {
            ice.height -= count;
        }
    }

    private static boolean isDivide(int row, int col) {
        boolean[][] visited = new boolean[N][M];
        Queue<Ice> queue = new ArrayDeque<>();
        queue.offer(new Ice(row, col));
        visited[row][col] = true;
        int count = 0; // 연결된 빙산 갯수

        while(!queue.isEmpty()) {
            Ice ice = queue.poll();
            count++;

            for(int i = 0; i < dx.length; i++) {
                int nextRow = ice.row + dx[i];
                int nextCol = ice.col + dy[i];

                if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] != 0) {
                    queue.offer(new Ice(nextRow, nextCol));
                    visited[nextRow][nextCol] = true;
                }
            }
        }

        // 연결된 빙산 갯수와 현재 남아있는 빙산 갯수 비교 => 빙산이 분리되어 있는가
        return count != remain.size();
    }
}
