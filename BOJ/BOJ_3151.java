import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ_3151 합이 0
public class BOJ_3151 {
    static int[] selectedNums;
    static int[] students;
    static long answer;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        students = new int[N];
        selectedNums = new int[2];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N; i++) {
            students[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(students);

        for(int i = 0; i < N - 2; i++) {
            for(int j = i + 1; j < N - 1; j++) {
                int sum = students[i] + students[j]; // 2명의 학생 먼저 뽑기

                // 나머지 한 명은 이분탐색으로
                // 중복된 숫자가 있으므로 lowerBound와 upperBound를 구한 뒤
                int lower = lowerBound(j + 1, N, -sum);
                int upper = upperBound(j + 1, N, -sum);

                // 결과 갯수만큼 답에 더해준다.
                answer += upper - lower;
            }
        }

        System.out.println(answer);
    }

    private static int lowerBound(int left, int right, int key) {
        while(left < right) {
            int mid = (left + right) / 2;

            if(students[mid] < key) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return right;

    }

    private static int upperBound(int left, int right, int key) {
        while(left < right) {
            int mid = (left + right) / 2;

            if(students[mid] <= key) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return right;
    }

}
