import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ_9466 텀 프로젝트
public class BOJ_9466 {

    static int[] team;
    static boolean[] visited;
    static boolean[] checked;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            result = n; // 팀에 속하지 않은 학생
            StringTokenizer st = new StringTokenizer(br.readLine());
            team = new int[n + 1];
            visited = new boolean[n + 1]; // 단순 방문 여부
            checked = new boolean[n + 1]; // 사이클 검출 완료 여부
            for (int i = 1; i <= n; i++) {
                team[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!checked[i]) { // 사이클 검출이 완료되지 않은 정점이라면
                    recursive(i);
                }
            }
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

    static void recursive(int cur) {
        visited[cur] = true;

        int next = team[cur];
        if (!visited[next]) {
            recursive(next);
        } else if (!checked[next]) { // 이번 회차에서 생긴 사이클이라면
            /*
            방문(visited)은 했지만, 사이클 검출 완료(checked) 정점이 아니라면 = 이번 회차에 생긴 사이클

            ex) 정점 4에서 시작했을 때 4, 6, 7이 이번 회차에서 생긴 사이클이라면
                        1, 2, 3, 4, 5, 6, 7
             visited = [t, t, t, t, f, t, t]
             checked = [t, t, t, f, f, f, f]
             위와 같은 상태
             */
            result--; // 현재 정점 빼기

            while (cur != next) { // 사이클이 시작되는 정점부터 현재 정점까지 돌면서 카운트 감소
                next = team[next];
                result--;
            }
        }

        checked[cur] = true; // 사이클 검출 완료 표시
    }

}
