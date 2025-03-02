package 리코쳇로봇;

import java.util.*;

class Solution {
    class Robot {
        int row, col, count;

        public Robot(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }

    char[][] map;
    boolean[][] visited;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int N, M;

    public int solution(String[] board) {
        int answer = 0;
        N = board.length;
        M = board[0].length();
        map = new char[N][M];
        visited = new boolean[N][M];
        int startRow = 0;
        int startCol = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'R') {
                    startRow = i;
                    startCol = j;
                }
            }
        }

        return bfs(startRow, startCol);
    }

    private int bfs(int startRow, int startCol) {
        Queue<Robot> q = new ArrayDeque<>();
        q.offer(new Robot(startRow, startCol, 0));

        while(!q.isEmpty()) {
            Robot curRobot = q.poll();
            int curRow = curRobot.row;
            int curCol = curRobot.col;
            int curCount = curRobot.count;
            if(map[curRow][curCol] == 'G') {
                return curCount;
            }

            for(int i = 0; i < dx.length; i++) {
                int nextRow = curRow + dx[i];
                int nextCol = curCol + dy[i];
                while(isIn(nextRow, nextCol) && map[nextRow][nextCol] != 'D') {
                    nextRow += dx[i];
                    nextCol += dy[i];
                }

                if(!isIn(nextRow, nextCol) || map[nextRow][nextCol] == 'D') {
                    nextRow -= dx[i];
                    nextCol -= dy[i];
                }

                if(!visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    q.offer(new Robot(nextRow, nextCol, curCount + 1));
                }
            }
        }

        return -1;
    }

    private boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}