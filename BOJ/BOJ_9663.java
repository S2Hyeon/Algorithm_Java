import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [G4] N-Queen
public class BOJ_9663 {

    static int[] col;
    static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // 각 행에 퀸을 하나씩만 배치할 수 있으므로
        // 배열의 index가 row, value가 col이 된다.
        col = new int[N + 1];

        dfs(1);
        System.out.println(answer);
    }

    private static void dfs(int row) {
        // 새로 배치한 퀸의 배치 가능 여부 판단
        if(!isAvailable(row - 1)) {
            return; // 배치 불가능하면 이전 행으로 돌아가서 열 값 조정
        }

        if(row > N) { // 마지막 행까지 N개의 퀸을 배치했다면
            answer++;
            return;
        }

        for(int i = 1; i <= N; i++) {
            col[row] = i; // 현재 행의 i번째 열에 퀸을 배치해본다(시도)
            dfs(row + 1); // 다음 행으로 이동
        }
    }

    private static boolean isAvailable(int row) {
        for(int i = 1; i < row; i++) {
            // 열이 같거나 || 대각선상에 있다( |c2 - c1| == r2 - r1 )면
            if(col[row] == col[i] || Math.abs(col[row] - col[i]) == row - i) {
                return false;
            }
        }

        return true;
    }
}
