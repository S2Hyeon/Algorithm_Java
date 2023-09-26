import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ_2110 공유기 설치
public class BOJ_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] homes = new int[N];
        int left = 0;
        int right;

        for(int i = 0; i < N; i++) {
            homes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(homes);
        right = homes[N - 1]; // 가장 멀리 있는 집의 위치를 right로

        while(left < right) {
            int mid = (left + right) / 2;
            int count = 0;
            int range = -1; // 유효 범위 : left 시작 위치가 0일수도 있으므로 -1로 설정

            for (int i = 0; i < N; i++) {
                if(range < homes[i]) { // 집이 유효범위 외에 있다면
                    count++;
                    range = homes[i] + mid; // 유효범위를 현재 집의 위치 + 이분탐색 거리(mid)로 설정
                }
            }

            if(count >= C) { // 설치 완료한 공유기 개수가 입력값보다 크거나 같다면
                left = mid + 1; // 거리값이 작다는 것이므로 left 값 조정
            }
            else {
                right = mid; // 작다면 거리값이 크다는 것이므로 right 값 조정
            }
        }

        System.out.println(right);
    }
}
