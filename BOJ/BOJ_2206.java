import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [G3] 벽 부수고 이동하기
public class BOJ_2206 {
    static class Location {
        int row, col, depth;

        public Location(int row, int col, int depth) {
            this.row = row;
            this.col = col;
            this.depth = depth;
        }
    }

    static int[][][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M, depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        depth = 2;
        map = new int[N][M][depth];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                for(int k = 0; k < depth; k++) {
                    map[i][j][k] = str.charAt(j) - '0';
                }
            }
        }

        System.out.println(bfs(0, 0));
    }

    private static int bfs(int startRow, int startCol) {
        Queue<Location> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][depth];
        queue.offer(new Location(startRow, startCol, 0));
        visited[startRow][startCol][0] = true;
        int count = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            count++;

            while(--size >= 0) {
                Location curLocation = queue.poll();
                int curRow = curLocation.row;
                int curCol = curLocation.col;
                int curDepth = curLocation.depth;

                if(curRow == N - 1 && curCol == M - 1) {
                    return count;
                }

                for(int i = 0; i < dx.length; i++) {
                    int nextRow = curRow + dx[i];
                    int nextCol = curCol + dy[i];

                    if(isIn(nextRow, nextCol)) {
                        if(map[nextRow][nextCol][curDepth] == 1 && curDepth == 0) {
                            // 벽을 부술 수 있다면(현재 depth가 0이라면) 벽 부수고 이동
                            visited[nextRow][nextCol][1] = true;
                            queue.offer(new Location(nextRow, nextCol, 1));
                        } else if(map[nextRow][nextCol][curDepth] == 0 && !visited[nextRow][nextCol][curDepth]) {
                            visited[nextRow][nextCol][curDepth] = true;
                            queue.offer(new Location(nextRow, nextCol, curDepth));
                        }
                        // 다음 위치에 벽이 있지만 현재 depth가 1이면 벽을 부술 수 없으므로 스킵
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}
