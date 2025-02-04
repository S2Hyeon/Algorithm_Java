import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [S1] 쉬운 최단거리
public class BOJ_14940 {

    static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[][] map, resultMap;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        resultMap = new int[N][M];
        int startRow = 0;
        int startCol = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    startRow = i;
                    startCol = j;
                } else if(map[i][j] == 1) {
                    // 갈 수 있는 땅이지만 도달할 수 없는 위치는 -1로 표시해주기 위해 미리 -1로 초기화
                    resultMap[i][j] = -1;
                }
            }
        }

        bfs(startRow, startCol);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                sb.append(resultMap[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int startRow, int startCol) {
        Queue<Location> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        queue.offer(new Location(startRow, startCol));
        visited[startRow][startCol] = true;
        int count = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            count++;

            while(--size >= 0) {
                Location curLocation = queue.poll();
                int curRow = curLocation.row;
                int curCol = curLocation.col;

                for(int i = 0; i < dr.length; i++) {
                    int nextRow = curRow + dr[i];
                    int nextCol = curCol + dc[i];

                    if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
                        queue.offer(new Location(nextRow, nextCol));
                        visited[nextRow][nextCol] = true;
                        resultMap[nextRow][nextCol] = count;
                    }
                }
            }
        }
    }

    private static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}
