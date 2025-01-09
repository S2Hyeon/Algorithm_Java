import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [G5] 상범 빌딩
public class BOJ_6593 {

    static class Location {
        int height, row, col;

        public Location(int height, int row, int col) {
            this.height = height;
            this.row = row;
            this.col = col;
        }
    }

    static char[][][] map;
    static int[] dz = {-1, 1, 0, 0, 0, 0};
    static int[] dx = {0, 0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 0, 1, 0, -1};
    static int L, R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);
        StringBuilder sb = new StringBuilder();

        while(!isLastInput()) {
            map = new char[L][R][C];
            Location startLocation = new Location(0, 0, 0);
            Location endLocation = new Location(0, 0, 0);

            for(int i = 0; i < L; i++) {
                for(int j = 0; j < R; j++) {
                    String str = br.readLine();
                    for(int k = 0; k < C; k++) {
                        map[i][j][k] = str.charAt(k);
                        if('S' == map[i][j][k]) {
                            startLocation = new Location(i, j, k);
                        } else if('E' == map[i][j][k]) {
                            endLocation = new Location(i, j, k);
                        }
                    }
                }

                br.readLine();
            }

            int result = bfs(startLocation, endLocation);

            if(result == -1) {
                sb.append("Trapped!");
            } else {
                sb.append("Escaped in ").append(result).append(" minute(s).");
            }

            sb.append("\n");
            input(br);
        }

        System.out.println(sb);
    }

    private static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    }

    private static boolean isLastInput() {
        return L == 0 && R == 0 && C == 0;
    }

    private static int bfs(Location startLocation, Location endLocation) {
        boolean[][][] visited = new boolean[L][R][C];
        Queue<Location> queue = new ArrayDeque<>();
        visited[startLocation.height][startLocation.row][startLocation.col] = true;
        queue.offer(new Location(startLocation.height, startLocation.row, startLocation.col));
        int count = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();

            while(--size >= 0) {
                Location curLocation = queue.poll();
                int curHeight = curLocation.height;
                int curRow = curLocation.row;
                int curCol = curLocation.col;

                if(curHeight == endLocation.height && curRow == endLocation.row && curCol == endLocation.col) {
                    return count;
                }

                for(int i = 0; i < dx.length; i++) {
                    int nextHeight = curHeight + dz[i];
                    int nextRow = curRow + dx[i];
                    int nextCol = curCol + dy[i];

                    if(isIn(nextHeight, nextRow, nextCol)
                            && !visited[nextHeight][nextRow][nextCol]
                            && map[nextHeight][nextRow][nextCol] != '#') {
                        visited[nextHeight][nextRow][nextCol] = true;
                        queue.offer(new Location(nextHeight, nextRow, nextCol));
                    }
                }
            }

            count++;
        }

        return -1;
    }

    private static boolean isIn(int height, int row, int col) {
        return height >= 0 && height < L && row >= 0 && row < R && col >= 0 && col < C;
    }
}
