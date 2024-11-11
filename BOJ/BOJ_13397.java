import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [G4] 구간 나누기 2
public class BOJ_13397 {

    static int[] arr;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int left = 0;
        int right = 0;

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, arr[i]);
        }

        while(left < right) {
            int mid = (left + right) / 2; // 최대값의 최소값

            if(check(mid) > M) { // 더 많은 구간이 만들어졌다면
                left = mid + 1; // 최대값의 최소값을 증가시킨다
            } else {
                right = mid; // M과 같은 구간의 개수가 만들어져도 최소값 탐색을 위해 right = mid로 설정
            }
        }

        System.out.println(left);
    }

    private static int check(int mid) {
        int max = 1;
        int min = 10001;
        int count = 1; // 구간의 개수

        for(int i = 0; i < N; i++) {
            max = Math.max(max, arr[i]); // 현재 구간의 최대값
            min = Math.min(min, arr[i]); // 현재 구간의 최소값

            if(max - min > mid) { // 지정했던 최대값의 최소값보다 큰 차이라면
                count++; // 구간 개수 증가
                // 현재 인덱스부터 새로운 구간 시작해야 하므로 max, min값 재설정
                max = arr[i];
                min = arr[i];
            }
        }

        return count;
    }
}