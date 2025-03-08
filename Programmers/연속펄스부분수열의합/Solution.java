package 연속펄스부분수열의합;

// Lv. 3 연속 펄스 부분 수열의 합
class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        int[] pulse1 = new int[sequence.length]; // [1, -1, 1, ...]이 곱해진 펄스 수열
        int[] pulse2 = new int[sequence.length]; // [-1, 1, -1, ...]이 곱해진 펄스 수열
        int temp = 1;

        for(int i = 0; i < sequence.length; i++) {
            pulse1[i] = sequence[i] * temp;
            pulse2[i] = sequence[i] * -temp;
            temp *= -1;
        }

        long[] dp1 = new long[sequence.length];
        long[] dp2 = new long[sequence.length];
        dp1[0] = pulse1[0];
        dp2[0] = pulse2[0];

        // 연속된 부분 수열이므로 이전까지의 최대값 + 현재 값 vs 현재 값 비교
        for(int i = 1; i < sequence.length; i++) {
            dp1[i] = Math.max(dp1[i - 1] + pulse1[i], pulse1[i]);
            dp2[i] = Math.max(dp2[i - 1] + pulse2[i], pulse2[i]);
        }

        for(int i = 0; i < sequence.length; i++) {
            long max = Math.max(dp1[i], dp2[i]);
            answer = Math.max(answer, max);
        }

        return answer;
    }
}