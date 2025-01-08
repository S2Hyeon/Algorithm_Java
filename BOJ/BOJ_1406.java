import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// [S2] 에디터
public class BOJ_1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String initString = br.readLine();
        Stack<Character> front = new Stack<>();
        Stack<Character> back = new Stack<>();

        for(int i = 0; i < initString.length(); i++) {
            front.push(initString.charAt(i));
        }

        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch(command) {
                case "L":
                    if(!front.isEmpty()) {
                        back.push(front.pop());
                    }
                    break;
                case "D":
                    if(!back.isEmpty()) {
                        front.push(back.pop());
                    }
                    break;
                case "B":
                    if(!front.isEmpty()) {
                        front.pop();
                    }
                    break;
                case "P":
                    front.add(st.nextToken().charAt(0));
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!front.isEmpty()) {
            back.push(front.pop());
        }

        while(!back.isEmpty()) {
            sb.append(back.pop());
        }

        System.out.println(sb);
    }
}
