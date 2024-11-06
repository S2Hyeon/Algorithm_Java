import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [G3] 세 용액
public class BOJ_2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        long min = Long.MAX_VALUE;
        int[] answer = new int[3];

        for(int i = 0; i < N - 1; i++) {
            for(int j = i + 1; j < N; j++) {
                // 두 개 용액 고정 후 마지막 용액은 이분탐색으로 찾는다.
                long value3 = -(arr[i] + arr[j]); // 찾아야할 값
                int left = 0;
                int right = N - 1;

                while(left <= right) {
                    int mid = (left + right) / 2;
                    long sum = Math.abs(value3 - arr[mid]); // 세 용액의 합

                    if(mid != i && mid != j && sum < min) {
                        min = sum;
                        answer[0] = arr[i];
                        answer[1] = arr[j];
                        answer[2] = arr[mid];
                    }

                    if(value3 < arr[mid]) {
                        right = mid - 1;
                    } else { // 더 작은 합이 있는지 확인하기 위해 두 값이 같을 때도 오른쪽을 탐색한다.
                        left = mid + 1;
                    }
                }
            }
        }

        Arrays.sort(answer);
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }
}
