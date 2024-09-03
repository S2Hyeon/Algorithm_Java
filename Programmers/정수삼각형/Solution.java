package 정수삼각형;

// Lv. 3 정수 삼각형
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int height = triangle.length;
        int[][] sumTriangle = new int[height][];
        for(int i = 0; i < height; i++) {
            // 피라미드형 배열 생성
            sumTriangle[i] = new int[i + 1];
        }

        sumTriangle[0][0] = triangle[0][0];

        // 다음 행의 왼쪽, 오른쪽에 현재값을 더해준 최대값을 저장
        for(int i = 0; i < height - 1; i++) {
            for(int j = 0; j < sumTriangle[i].length; j++) {
                // 왼쪽
                sumTriangle[i + 1][j] = Math.max(
                        sumTriangle[i + 1][j], // 기존 값
                        sumTriangle[i][j] + triangle[i + 1][j]); // 현재값 + 다음 행의 왼쪽 값

                // 오른쪽
                sumTriangle[i + 1][j + 1] = Math.max(
                        sumTriangle[i + 1][j + 1], // 기존 값
                        sumTriangle[i][j] + triangle[i + 1][j + 1]); // 현재값 + 다음 행의 오른쪽 값
            }
        }

        // 마지막 행의 값들 중 최대값 찾기
        for(int i = 0; i < sumTriangle[height - 1].length; i++) {
            answer = Math.max(answer, sumTriangle[height - 1][i]);
        }

        return answer;
    }
}