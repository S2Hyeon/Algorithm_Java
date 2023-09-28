import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// BOJ_10845 큐
public class BOJ_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> queue = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "push":
                    int num = Integer.parseInt(st.nextToken());
                    queue.offer(num);
                    break;

                case "pop":
                    if(queue.isEmpty()) {
                        sb.append(-1);
                    }
                    else {
                        sb.append(queue.poll());
                    }

                    sb.append("\n");
                    break;

                case "size":
                    sb.append(queue.size()).append("\n");
                    break;

                case "empty":
                    if(queue.isEmpty()) {
                        sb.append(1);
                    }
                    else {
                        sb.append(0);
                    }

                    sb.append("\n");
                    break;

                case "front":
                    if(queue.isEmpty()) {
                        sb.append(-1);
                    }
                    else {
                        sb.append(queue.peekFirst());
                    }

                    sb.append("\n");
                    break;

                case "back":
                    if(queue.isEmpty()) {
                        sb.append(-1);
                    }
                    else {
                        sb.append(queue.peekLast());
                    }

                    sb.append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
