import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ_2234 성곽
public class BOJ_2234 {

    static class Position {
        int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static int N, M, maxSize;
    static int[][] map; // 원본 맵
    static int[][] areaMap; // 지역 번호를 표기할 맵
    static boolean[][] visited; // 방문 여부
    static HashMap<Integer, Integer> areaInfo; // 지역 번호 : 지역 크기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        areaMap = new int[M][N];
        visited = new boolean[M][N];
        areaInfo = new HashMap<>();
        int maxMergeSize = 0;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int areaNumber = 1;
        for(int i = 0; i < M; i++) {
            for(int j = 0 ; j < N; j++) {
                if(!visited[i][j]) {
                    bfs(i, j, areaNumber++);
                }
            }
        }

        // 순차 탐색하면서 오른쪽 혹은 아래에 다른 숫자가 있을 때 두 숫자 지역의 크기 합치기
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(isIn(i, j + 1) && areaMap[i][j] != areaMap[i][j + 1]) {
                    maxMergeSize = Math.max(maxMergeSize, areaInfo.get(areaMap[i][j]) + areaInfo.get(areaMap[i][j + 1]));
                }

                if(isIn(i + 1, j) && areaMap[i][j] != areaMap[i + 1][j]) {
                    maxMergeSize = Math.max(maxMergeSize, areaInfo.get(areaMap[i][j]) + areaInfo.get(areaMap[i + 1][j]));
                }
            }
        }

        System.out.println(areaInfo.size());
        System.out.println(maxSize);
        System.out.println(maxMergeSize);
    }

    public static void bfs(int startRow, int startCol, int areaNumber) {
        Queue<Position> queue = new ArrayDeque<>();
        visited[startRow][startCol] = true;
        queue.offer(new Position(startRow, startCol));
        int count = 0; // 지역의 크기 카운터
        int[] dx = {0, -1, 0, 1}; // 서 북 동 남
        int[] dy = {-1, 0, 1, 0};

        while(!queue.isEmpty()) {
            Position position = queue.poll();
            count++;
            int curRow = position.row;
            int curCol = position.col;
            areaMap[curRow][curCol] = areaNumber;
            int wallInfo = map[curRow][curCol];
            for(int i = 0; i < 4; i++) {
                if((wallInfo & (1 << i)) == 0) { // 벽이 없는 곳이라면
                    int nextRow = curRow + dx[i];
                    int nextCol = curCol + dy[i];
                    int nextWallInfo = map[nextRow][nextCol];
                    if((nextWallInfo & (1 << ((i + 2) % 4))) == 0) { // 다음 칸에도 현재 칸에 맞닿아 있는 벽이 없다면
                        if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                            queue.offer(new Position(nextRow, nextCol));
                            visited[nextRow][nextCol] = true;
                        }
                    }
                }
            }

        }

        areaInfo.put(areaNumber, count); // 지역 정보(지역 번호 : 지역 크기) 추가
        maxSize = Math.max(maxSize, count); // 지역 크기의 최대값 갱신
    }

    public static boolean isIn(int row, int col) {
        return row >= 0 && row < M && col >= 0 && col < N;
    }
}
