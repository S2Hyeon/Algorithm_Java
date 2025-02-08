import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// [S1] 절대값 힙
public class BOJ_11286 {
    static class Number implements Comparable<Number> {
        int originNumber, absNumber;

        public Number(int originNumber, int absNumber) {
            this.originNumber = originNumber;
            this.absNumber = absNumber;
        }

        @Override
        public int compareTo(Number n) {
            if(this.absNumber == n.absNumber) {
                return this.originNumber - n.originNumber;
            }
            return this.absNumber - n.absNumber;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Number> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());

            if(input == 0) {
                if(pq.isEmpty()) {
                    sb.append(0);
                } else {
                    sb.append(pq.poll().originNumber);
                }

                sb.append("\n");
            } else {
                pq.offer(new Number(input, Math.abs(input)));
            }
        }

        System.out.println(sb);
    }
}
