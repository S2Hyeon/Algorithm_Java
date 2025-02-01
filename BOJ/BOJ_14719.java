import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [G5] 빗물
public class BOJ_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[][] map = new int[H][W];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < W; i++) {
            int N = Integer.parseInt(st.nextToken());
            for(int j = 0; j < N; j++) {
                map[H - j - 1][i] = 1;
            }
        }

        int count = 0;

        for(int i = 0; i < H; i++) {
            boolean flag = false; // 블록 발견 여부
            int left = 0; // 최근 발견한 블록의 위치
            for(int j = 0; j < W; j++) {
                if(map[i][j] == 1) { // 블록을 발견했을 때
                    if(!flag) { // 이번 행에서 처음 발견한 블록이라면
                        flag = true;
                    } else {
                        count += j - left - 1; // 빈 공간의 갯수만큼 count에 합산
                    }

                    left = j; // 현재 블록의 위치 저장
                }
            }
        }

        System.out.println(count);
    }
}
