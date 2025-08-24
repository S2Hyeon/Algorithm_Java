package 순위;

// Lv. 3 순위
public class Solution {
    /*
        문제에서 주어진 입출력 예시
        승리 정보           승리 정보 활용한 모든 선수 간 승패 관계
        0 1 0 0 0          0 1 0 0 1
        0 0 0 0 1          0 0 0 0 1
        0 1 0 0 0    ->    0 1 0 0 1
        0 1 1 0 0          0 1 1 0 1
        0 0 0 0 0          0 0 0 0 0
        2번과 5번 선수의 행 + 열 = n - 1

        주어진 승리 관계를 활용해 승패 관계를 나타내기 위해서는
        각 선수마다 다른 모든 선수와의 승패 결과를 기록해두고,
        승리 횟수와 패배 횟수를 더한 값이 자신을 제외한 모든 선수의 수와 일치하는지 알아야 한다.

        각 정점에서 모든 정점까지 순회하며 도달 가능 여부를 기록해야 한다.
        -> 플로이드-와샬 알고리즘
     */
    public int solution(int n, int[][] results) {
        int answer = 0;
        boolean[][] win = new boolean[n + 1][n + 1];

        for(int i = 0; i < results.length; i++) {
            int from = results[i][0];
            int to = results[i][1];
            win[from][to] = true; // 초기 승리 결과 기록
        }

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(win[i][k] && win[k][j]) { // i 선수가 k 선수를 이겼고, k 선수가 j 선수를 이겼다면
                        win[i][j] = true; // i 선수는 k 선수보다 높은 순위(관계 성립)
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            int sum = 0; // 선수별 성립된 관계의 수
            for(int j = 1; j <= n; j++) {
                if(win[i][j]) {
                    sum++;
                }
                if(win[j][i]) {
                    sum++;
                }
            }

            // 성립된 관계의 수가 자신을 제외한 모든 선수의 수와 같다면 순위 확정 가능
            if(sum == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}
