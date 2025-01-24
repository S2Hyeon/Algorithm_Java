import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [G3] 다리 만들기
public class BOJ_2146 {
    static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        min = Integer.MAX_VALUE;
        StringTokenizer st;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        numbering(); // 섬마다 번호 설정

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 0) {
                    int num = checkNear(i, j); // 주위에 있는 섬 번호 구하기
                    if(num > 0) { // 섬과 인접한 바다라면
                        bridgeBfs(i, j, num); // 다리 놓기
                    }
                }
            }
        }

        System.out.println(min);
    }

    // 섬마다 번호를 지정한다.
    private static void numbering() {
        int num = 1;
        boolean[][] visited = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j] && map[i][j] == 1) {
                    numberingBfs(visited, i, j, num++);
                }
            }
        }
    }

    // 번호 표시를 위한 bfs
    private static void numberingBfs(boolean[][] visited, int startRow, int startCol, int num) {
        Queue<Location> queue = new ArrayDeque<>();
        visited[startRow][startCol] = true;
        queue.offer(new Location(startRow, startCol));
        map[startRow][startCol] = num;

        while(!queue.isEmpty()) {
            Location curLocation = queue.poll();

            for(int i = 0; i < dx.length; i++) {
                int nextRow = curLocation.row + dx[i];
                int nextCol = curLocation.col + dy[i];

                if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
                    map[nextRow][nextCol] = num;
                    queue.offer(new Location(nextRow, nextCol));
                    visited[nextRow][nextCol] = true;
                }
            }
        }
    }

    // 범위 내 좌표인지 확인
    private static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

    // 주위에 있는 섬 번호 확인
    private static int checkNear(int row, int col) {
        for(int i = 0; i < dx.length; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];

            if(isIn(nextRow, nextCol) && map[nextRow][nextCol] != 0) {
                return map[nextRow][nextCol];
            }
        }

        return -1;
    }

    // 다리 놓기 bfs
    private static void bridgeBfs(int startRow, int startCol, int num) {
        Queue<Location> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        visited[startRow][startCol] = true;
        queue.offer(new Location(startRow, startCol));
        int count = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            count++;
            while(--size >= 0) {
                Location curLocation = queue.poll();
                int curRow = curLocation.row;
                int curCol = curLocation.col;

                for(int i = 0; i < dx.length; i++) {
                    int nextRow = curRow + dx[i];
                    int nextCol = curCol + dy[i];
                    if(isIn(nextRow, nextCol)) {
                        // 시작했던 섬 번호와 다른 섬에 도착했다면 최소값 갱신
                        if(map[nextRow][nextCol] > 0 && map[nextRow][nextCol] != num) {
                            min = Math.min(min, count);
                            return;
                        }

                        if(!visited[nextRow][nextCol] && map[nextRow][nextCol] == 0) {
                            queue.offer(new Location(nextRow, nextCol));
                            visited[nextRow][nextCol] = true;
                        }
                    }
                }
            }
        }
    }
}
