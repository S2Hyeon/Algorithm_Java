import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [G4] 좋다
public class BOJ_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int answer = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j) continue;
                if(binarySearch(arr, i, j)) {
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);

    }

    private static boolean binarySearch(int[] arr, int resultIndex, int value1Index) {
        int value2 = arr[resultIndex] - arr[value1Index]; // 탐색할 값 = 결과값 - 사용할 요소
        int left = 0;
        int right = arr.length - 1;

        while(left <= right) {
            int mid = (left + right) / 2;

            // 결과값과 이미 사용된 요소는 중복 사용할 수 없음
            if(value2 == arr[mid] && mid != resultIndex && mid != value1Index) {
                return true;
            } else if(value2 > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}