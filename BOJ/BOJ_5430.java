import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// BOJ_5430 AC
public class BOJ_5430 {

    static Deque<Integer> deque;
    static int direction; // 0이면 정방향, 1이면 역방향;

    public static boolean executeCommand(String commands) {

        int commandsLength = commands.length();

        for (int i = 0; i < commandsLength; i++) {
            char command = commands.charAt(i);

            if ('R' == command) { // R이면 방향 뒤집기(1 -> 0, 0 -> 1)
                direction ^= 1;
            } else if ('D' == command) {
                if(deque.isEmpty()) { // 덱이 비어있다면 error
                    return false;
                }
                if (direction == 0) { // 방향에 따른 덱 요소 poll
                    deque.pollFirst();
                } else if (direction == 1) {
                    deque.pollLast();
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            String commands = br.readLine();
            deque = new ArrayDeque<>();
            direction = 0;
            int n = Integer.parseInt(br.readLine());
            String stringArray = br.readLine();
            stringArray = stringArray.substring(1, stringArray.length() - 1);
            String[] splitArr = stringArray.split(",");

            for (int i = 0; i < n; i++) {
                deque.add(Integer.parseInt(splitArr[i]));
            }

            if(!executeCommand(commands)) {
                sb.append("error").append("\n");
                continue;
            }

            int dequeSize = deque.size();
            sb.append("[");
            for(int j = 0; j < dequeSize; j++) {
                if(direction == 0) {
                    sb.append(deque.pollFirst()).append(",");
                }
                else if (direction == 1) {
                    sb.append(deque.pollLast()).append(",");
                }
            }

            if(dequeSize != 0) { // 마지막 요소 뒤의 , 없애기
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]").append("\n");

        }
        System.out.println(sb);
    }
}
