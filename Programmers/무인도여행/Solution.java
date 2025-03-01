package 무인도여행;

import java.util.*;

// Lv. 2 무인도 여행
class Solution {
    class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    char[][] map;
    boolean[][] visited;
    int N, M;

    public int[] solution(String[] maps) {
        List<Integer> list = new ArrayList<>();
        N = maps.length;
        M = maps[0].length();
        map = new char[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            map[i] = maps[i].toCharArray();
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] !='X' && !visited[i][j]) {
                    visited[i][j] = true;
                    list.add(bfs(i, j));
                }
            }
        }

        int[] answer;
        if(list.isEmpty()) {
            answer = new int[]{-1};
        } else {
            Collections.sort(list);
            answer = new int[list.size()];
            for(int i = 0; i < answer.length; i++) {
                answer[i] = list.get(i);
            }
        }

        return answer;
    }

    public int bfs(int startRow, int startCol) {
        Queue<Location> q = new ArrayDeque<>();
        q.offer(new Location(startRow, startCol));
        int amount = 0;

        while(!q.isEmpty()) {
            Location curLocation = q.poll();
            int curRow = curLocation.row;
            int curCol = curLocation.col;
            amount += map[curRow][curCol] - '0';

            for(int i = 0; i < dx.length; i++) {
                int nextRow = curRow + dx[i];
                int nextCol = curCol + dy[i];
                if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] != 'X') {
                    visited[nextRow][nextCol] = true;
                    q.offer(new Location(nextRow, nextCol));
                }
            }
        }

        return amount;
    }

    private boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}