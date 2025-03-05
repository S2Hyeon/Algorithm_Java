package 줄서는방법;

// Lv. 2 줄 서는 방법
import java.util.*;

class Solution {

    public int[] solution(int n, long k) {
        if(n == 1) {
            return new int[]{1};
        }

        int[] answer = new int[n];
        long[] factorial = new long[n + 1]; // 팩토리얼 미리 계산
        ArrayList<Integer> list = new ArrayList<>(); // 사용 가능한 수
        factorial[0] = 1;

        for(int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
            list.add(i);
        }

        k--; // k번 째 수 -> 인덱스로 치환 -> k - 1

        // 첫 번째 자리에 올 수 있는 숫자 n개 -> 첫 번째 숫자가 나오는 개수 (n - 1)!개
        // 두 번째 자리에 올 수 있는 숫자 n - 1개 -> 두번째 숫자가 나오는 개수 (n - 2)!개
        // 각 자리수마다 k를 기준으로 인덱스 계산 후,
        // 사전순이므로 list에 저장된 수를 사용 후 제거.
        for(int i = 0; i < n; i++) {
            int index = (int)(k / factorial[n - i - 1]);
            answer[i] = list.get(index);
            list.remove(index);
            k %= factorial[n - i - 1];
        }

        return answer;
    }
}
