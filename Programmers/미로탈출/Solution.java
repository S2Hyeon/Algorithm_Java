package 미로탈출;

import java.util.ArrayDeque;
import java.util.Queue;

// Lv. 2 미로 탈출
class Solution {
    class LocationInfo {
        int row, col;

        public LocationInfo(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    int N, M;

    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        LocationInfo S = new LocationInfo(0, 0); // 시작점
        LocationInfo L = new LocationInfo(0, 0); // 레버 위치
        LocationInfo E = new LocationInfo(0, 0); // 출구

        char[][] map = new char[N][M];

        for(int i = 0; i < N; i++) {
            map[i] = maps[i].toCharArray();
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 'S') {
                    S.row = i;
                    S.col = j;
                } else if(map[i][j] == 'L') {
                    L.row = i;
                    L.col = j;
                } else if(map[i][j] == 'E') {
                    E.row = i;
                    E.col = j;
                }
            }
        }

        int toLever = bfs(map, S, L); // 시작점 -> 레버까지의 최단 거리
        if(toLever == -1) {
            return -1;
        }

        int toExit = bfs(map, L, E); // 레버 -> 출구까지의 최단 거리
        if(toExit == -1) {
            return -1;
        }

        return toLever + toExit;
    }

    private int bfs(char[][] map, LocationInfo start, LocationInfo end) {
        int count = -1;
        boolean[][] visited = new boolean[N][M];
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        Queue<LocationInfo> queue = new ArrayDeque<>();
        visited[start.row][start.col] = true;
        queue.offer(new LocationInfo(start.row, start.col));

        while(!queue.isEmpty()) {
            int size = queue.size();
            count++;

            while(--size >= 0) {
                LocationInfo curLocationInfo = queue.poll();
                int curRow = curLocationInfo.row;
                int curCol = curLocationInfo.col;

                if(curRow == end.row && curCol == end.col) {
                    return count;
                }

                for(int i = 0; i < dr.length; i++) {
                    int nextRow = curRow + dr[i];
                    int nextCol = curCol + dc[i];

                    if(isIn(nextRow, nextCol) && map[nextRow][nextCol] != 'X' && !visited[nextRow][nextCol]) {
                        queue.offer(new LocationInfo(nextRow, nextCol));
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }

        return -1;
    }

    private boolean isIn(int row, int col) {
        return 0 <= row && row < N && 0 <= col && col < M;
    }
}