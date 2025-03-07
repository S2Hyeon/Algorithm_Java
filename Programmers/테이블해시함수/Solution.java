package 테이블해시함수;

import java.util.Arrays;
import java.util.Comparator;

// Lv. 2 테이블 해시 함수
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Comparator<int[]> comparator = (arr1, arr2) -> {
            if(arr1[col - 1] == arr2[col - 1]) {
                return arr2[0] - arr1[0];
            }

            return arr1[col - 1] - arr2[col - 1];
        };

        Arrays.sort(data, comparator);

        for(int i = row_begin - 1; i < row_end; i++) {
            int sum = 0;
            for(int j = 0; j < data[i].length; j++) {
                sum += data[i][j] % (i + 1);
            }

            answer ^= sum;
        }

        return answer;
    }
}
