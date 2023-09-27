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

        comb(0, 0);

        System.out.println(answer);
    }

    private static void comb(int cnt, int r) {
        if(r == 2) { // 조합으로 2명의 학생 뽑기
            int left = cnt;
            int right = N;
            int sum = selectedNums[0] + selectedNums[1];

            // 나머지 1명의 학생은 이분탐색으로 뽑는다.
            int lower = lowerBound(left, right, sum);
            int upper = upperBound(left, right, sum);

            // 0을 만들 수 있는 경우에만 답에 더해준다.
            if(lower != -1 && upper != -1) {
                answer += upper - lower + 1;
            }
            return;
        }

        if(cnt >= N) return;

        selectedNums[r] = students[cnt];
        comb(cnt + 1, r + 1);
        comb(cnt + 1, r);
    }

    private static int lowerBound(int left, int right, int sum) {
        while(left < right) {
            int mid = (left + right) / 2;
            int result = sum + students[mid];

            if(result < 0) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        // 값을 찾지 못한 경우 left가 N까지 이동하므로 이 경우를 제외한다.
        if(left < N && sum + students[left] == 0) {
            return left;
        }

        return -1;

    }

    private static int upperBound(int left, int right, int sum) {
        while(left < right) {
            int mid = (left + right) / 2;
            int result = sum + students[mid];

            if(result <= 0) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        // upperBound값을 찾은것이므로 -1을 해주어야 한다.
        // 만약 값을 찾지 못한 경우에도 left(== N) - 1이기 때문에 범위 초과가 나지 않는다.
        if(sum + students[left - 1] == 0) {
            return left - 1;
        }

        return -1;
    }

}
