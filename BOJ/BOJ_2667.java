import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

// [S1] 단지번호붙이기
public class BOJ_2667 {

    static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Integer> list;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        list = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j] && map[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }

        Collections.sort(list);
        System.out.println(list.size());

        for(int n : list) {
            System.out.println(n);
        }
    }

    private static void bfs(int startRow, int startCol) {
        Queue<Location> queue = new ArrayDeque<>();
        queue.offer(new Location(startRow, startCol));
        visited[startRow][startCol] = true;
        int count = 0;

        while(!queue.isEmpty()) {
            Location curLocation = queue.poll();
            int curRow = curLocation.row;
            int curCol = curLocation.col;
            count++;

            for(int i = 0; i < dx.length; i++) {
                int nextRow = curRow + dx[i];
                int nextCol = curCol + dy[i];

                if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
                    queue.offer(new Location(nextRow, nextCol));
                    visited[nextRow][nextCol] = true;
                }
            }
        }

        list.add(count);
    }

    private static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }
}
