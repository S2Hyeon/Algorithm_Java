package 보석쇼핑;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Lv. 3 보석 쇼핑
public class Solution {
    int left;
    int right;

    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>();
        Map<String, Integer> amountOfGems = new HashMap<>();
        int length = Integer.MAX_VALUE;

        // 보석 종류 구하기
        for(int i = 0; i < gems.length; i++) {
            set.add(gems[i]);
        }

        int kind = set.size();
        left = right = 0;

        while(right < gems.length) {
            amountOfGems.put(gems[right], amountOfGems.getOrDefault(gems[right], 0) + 1);

            // 현재 범위에서 left가 가리키고 있는 보석의 개수가 2개 이상 이라면
            while(amountOfGems.get(gems[left]) > 1) {
                // 모든 보석 종류를 포함하는 가장 짧은 범위를 구해야 하므로 left를 오른쪽으로 이동시킨다.
                amountOfGems.put(gems[left], amountOfGems.get(gems[left]) - 1);
                left++;
            }

            // 정답 조건 검사(모든 보석 종류 포함 여부, 최소 길이 여부)
            if(amountOfGems.size() == kind && length > (right - left)) {
                length = right - left;
                answer[0] = left + 1;
                answer[1] = right + 1;
            }

            right++;
        }

        return answer;
    }
}
