package 연속된부분수열의합;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int length = sequence.length;
        int minRange = Integer.MAX_VALUE; // 수열의 최소길이
        int[] sumArr = new int[length + 1]; // 누적합 배열

        for (int i = 1; i <= length; i++) {
            sumArr[i] = sequence[i - 1] + sumArr[i - 1];
        }

        // sequence[i] ~ sequence[j]의 부분합은
        // sumArr[j + 1] - sumArr[i]이므로 endIndex는 1부터 시작
        int startIndex = 0;
        int endIndex = 1;

        while (startIndex <= length && endIndex <= length) {
            int prefixSum = sumArr[endIndex] - sumArr[startIndex]; // 부분합
            if (prefixSum < k) {
                endIndex++;
            } else if (prefixSum > k) {
                startIndex++;
            } else {
                int range = endIndex - startIndex;
                if (range < minRange) {
                    minRange = range;
                    answer[0] = startIndex;
                    answer[1] = endIndex - 1;
                }

                if (endIndex <= length) {
                    endIndex++;
                } else {
                    startIndex++;
                }
            }
        }

        return answer;
    }
}
