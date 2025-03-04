package 여행경로;

// Lv. 3 여행 경로
import java.util.*;
class Solution {
    ArrayList<String> result;
    boolean[] visited;
    int N;

    public String[] solution(String[][] tickets) {
        N = tickets.length;
        result = new ArrayList<>();
        visited = new boolean[N];

        dfs(tickets, "ICN", "ICN", 0);
        Collections.sort(result);

        return result.get(0).split(" ");
    }

    private void dfs(String[][] tickets, String path, String curCity, int count) {
        if(count == N) {
            result.add(path);
            return;
        }

        for(int i = 0; i < N; i++) {
            if(tickets[i][0].equals(curCity) && !visited[i]) {
                visited[i] = true;
                dfs(tickets, path + " " + tickets[i][1], tickets[i][1], count + 1);
                visited[i] = false;
            }
        }
    }
}