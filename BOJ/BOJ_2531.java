import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [S1] 회전 초밥
public class BOJ_2531 {
    static Queue<Integer> window;
    static int[] belt;
    static int[] sushi;
    static int kind; // 윈도우 내 초밥 종류

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 접시 수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓 수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
        int max = 0; // 윈도우 내 초밥 종류의 최대값
        window = new ArrayDeque<>(); // 슬라이딩 윈도우
        belt = new int[N]; // 벨트 위 초밥들
        sushi = new int[d + 1]; // 윈도우 내 초밥 종류별 갯수

        for(int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        // 쿠폰으로 먹을 수 있는 초밥은 항상 포함
        sushi[c] = 1;
        kind = 1;

        // 초기 k개의 초밥 접시 윈도우에 삽입
        for(int i = 0; i < k; i++) {
            enQueue(belt[i]);
        }

        // 회전 초밥이므로 N을 넘어가면 0부터 다시 시작
        for(int i = k; i < N + k; i++) {
            deQueue(window.poll()); // 윈도우의 가장 앞 초밥 삭제
            enQueue(belt[i % N]); // 윈도우에 다음 초밥 삽입
            max = Math.max(max, kind);
        }

        System.out.println(max);
    }

    private static void enQueue(int n) {
        if(sushi[n] == 0) { // 윈도우 내 같은 초밥이 없다면
            kind++; // 윈도우 내 초밥 종류 증가
        }

        window.offer(n); // 윈도우에 삽입
        sushi[n]++; // 윈도우 내 초밥 갯수 추가
    }

    private static void deQueue(int n) {
        sushi[n]--; // 윈도우 내 초밥 갯수 감소

        if(sushi[n] == 0) { // 윈도우 내 같은 초밥이 없다면
            kind--; // 윈도우 내 초밥 종류 감소
        }
    }
}
