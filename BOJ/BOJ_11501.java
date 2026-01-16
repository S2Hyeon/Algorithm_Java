import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [S2] 주식
public class BOJ_11501 {
    /*
        고점보다 낮은 가격에 사서 고점일 때 판다.
        정방향 탐색을 하면
            매일 현재 가격이 고점인지 확인해야한다. => O(N^2) 시간 초과

        역방향 탐색을 하면
            미래의 고점을 아는 상태이므로 현재 가격에 팔 지, 기다릴 지 판단할 수 있다.
                제일 마지막 값을 maxPrice로 지정한 상태로
                maxPrice <= 현재 가격
                    현재 가격이 고점이므로
                    maxPrice를 현재 가격으로 설정
                maxPrice > 현재 가격
                    현재 가격에 사서 maxPrice일 때 파는 것이므로
                    결과값에 maxPrice - 현재가격을 더해준다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] input = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0; i < N; i++) {
                input[i] = Integer.parseInt(st.nextToken());
            }

            int maxPrice = input[N - 1];
            long result = 0;
            for(int i = N - 2; i >= 0; i--) {
                if(maxPrice <= input[i]) {
                    maxPrice = input[i];
                } else {
                    result += maxPrice - input[i];
                }
            }

            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }
}
