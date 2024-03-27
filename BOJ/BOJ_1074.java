import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [S1] Z
public class BOJ_1074 {
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        recursive(N, r, c);
        System.out.println(answer);
    }

    private static void recursive(int N, int r, int c) {
        int size = (int)Math.pow(2, N - 1); // 분할 크기
        int quadrantR = r / size;
        int quadrantC = c / size;
        int flag = quadrantR * 2 + quadrantC; // 사분면 정하기
        int base = (int)Math.pow(4, N - 1); // 현재 크기에서 각 사분면의 첫번째 값
        // 변수 base 예시 : N = 3 => base = 16
        // 1사분면 => +0, 2사분면 => +16, 3사분면 => +32, 4사분면 => +48

        switch (flag) {
            case 0: // 1사분면(없어도 되는 부분)
                break;
            case 1: // 2사분면
                answer += base;
                break;
            case 2: // 3사분면
                answer += base * 2;
                break;
            case 3: // 4사분면
                answer += base * 3;
                break;
        }

        if(N == 1) return;
        // 분할 정복을 위해 N - 1 & 행, 열의 나머지 값을 이용해 줄어든 크기에서 목표값 위치 조정
        recursive(N - 1, r % size, c % size);
    }
}
