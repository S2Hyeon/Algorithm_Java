import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [G3] 불!
public class BOJ_4179 {
    static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static char[][] map;
    static Queue<Location> fireLocation;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        fireLocation = new ArrayDeque<>();
        int startRow = 0;
        int startCol = 0;

        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'J') {
                    startRow = i;
                    startCol = j;
                } else if(map[i][j] == 'F') {
                    fireLocation.offer(new Location(i, j));
                }
            }
        }

        int result = bfs(startRow, startCol);

        if(result == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(result);
        }

    }

    private static int bfs(int startRow, int startCol) {
        boolean[][] visited = new boolean[R][C];
        Queue<Location> queue = new ArrayDeque<>();
        visited[startRow][startCol] = true;
        queue.offer(new Location(startRow, startCol));
        int count = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            count++;
            // 현재 시간에 불이 옮기는 곳으로 이동하면 안되므로
            // 불이 옮겨붙는 것을 먼저 처리한다.
            fireMove();

            while(--size >= 0) {
                Location location = queue.poll();
                int curRow = location.row;
                int curCol = location.col;

                if(canEscape(curRow, curCol)) {
                    return count;
                }

                for(int i = 0; i < dx.length; i++) {
                    int nextRow = curRow + dx[i];
                    int nextCol = curCol + dy[i];

                    if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] == '.') {
                        visited[nextRow][nextCol] = true;
                        queue.offer(new Location(nextRow, nextCol));
                    }
                }
            }
        }

        return -1;
    }

    private static void fireMove() {
        int size = fireLocation.size();

        while(--size >= 0) {
            Location location = fireLocation.poll();
            int curRow = location.row;
            int curCol = location.col;

            for(int i = 0; i < dx.length; i++) {
                int nextRow = curRow + dx[i];
                int nextCol = curCol + dy[i];

                if(isIn(nextRow, nextCol) && map[nextRow][nextCol] == '.') {
                    fireLocation.offer(new Location(nextRow, nextCol));
                    map[nextRow][nextCol] = 'F';
                }
            }
        }
    }

    private static boolean isIn(int row, int col) {
        return row >= 0 && row < R && col >= 0 && col < C;
    }

    private static boolean canEscape(int row, int col) {
        return row == 0 || row == R - 1 || col == 0 || col == C - 1;
    }
}
