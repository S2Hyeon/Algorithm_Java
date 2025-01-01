import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [G4] 불
public class BOJ_5427 {

    static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static Queue<Location> fireLocation;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Location playerLocation;
    static int w, h, turn;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            char[][] map = new char[h][w];
            fireLocation = new ArrayDeque<>(); // 옮겨진 불들의 위치

            for(int i = 0; i < h; i++) {
                String str = br.readLine();
                for(int j = 0; j < w; j++) {
                    map[i][j] = str.charAt(j);
                    if(map[i][j] == '@') {
                        playerLocation = new Location(i, j);
                        map[i][j] = '.'; // 상근이의 위치는 별도로 저장하므로 빈 공간으로 간주한다
                    } else if(map[i][j] == '*') {
                        fireLocation.add(new Location(i, j));
                    }
                }
            }

            if(bfs(map)) {
                sb.append(turn);
            } else {
                sb.append("IMPOSSIBLE");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static boolean isIn(int row, int col) {
        return row >= 0 && row < h && col >= 0 && col < w;
    }

    // 건물의 테두리 쪽으로 나온 경우 탈출에 성공
    private static boolean canEscape(int row, int col) {
        return row == 0 || row == h - 1 || col == 0 || col == w - 1;
    }

    private static void fireMove(char[][] map) {
        int size = fireLocation.size();

        for(int i = 0; i < size; i++) {
            Location l = fireLocation.poll();

            for(int j = 0; j < dx.length; j++) {
                int nextRow = l.row + dx[j];
                int nextCol = l.col + dy[j];

                // 빈 공간인 경우에만 불이 옮겨진다
                if(isIn(nextRow, nextCol) && map[nextRow][nextCol] == '.') {
                    map[nextRow][nextCol] = '*';
                    fireLocation.add(new Location(nextRow, nextCol));
                }
            }
        }
    }

    private static boolean bfs(char[][] map) {
        boolean[][] visited = new boolean[h][w];
        Queue<Location> queue = new ArrayDeque<>();
        int startRow = playerLocation.row;
        int startCol = playerLocation.col;
        visited[startRow][startCol] = true;
        queue.add(new Location(startRow, startCol));
        turn = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            turn++;

            // 불이 붙으려는 칸에 갈 수 없고, 상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로
            // 이동 할 수 있으므로 불이 퍼지는 작업을 먼저 해준다.
            fireMove(map);

            // 불이 옮겨진 이후 상근이가 움직이기 때문에 갈 수 있는 모든 경로를 한 번에 처리한다.
            while(--size >= 0) {
                Location l = queue.poll();
                int curRow = l.row;
                int curCol = l.col;
                if(canEscape(curRow, curCol)) {
                    return true;
                }

                for(int i = 0; i < dx.length; i++) {
                    int nextRow = curRow + dx[i];
                    int nextCol = curCol + dy[i];

                    if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] == '.') {
                        visited[nextRow][nextCol] = true;
                        queue.add(new Location(nextRow, nextCol));
                    }
                }
            }
        }

        return false;
    }
}
