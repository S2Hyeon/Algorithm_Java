import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [G2] 보석 도둑
public class BOJ_1202 {

    static class Jewel {
        int m, v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 보석 개수
        int K = Integer.parseInt(st.nextToken()); // 가방 개수
        ArrayList<Jewel> jewelList = new ArrayList<>();
        ArrayList<Integer> bagList = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewelList.add(new Jewel(m, v));
        }

        for(int i = 0; i < K; i++) {
            bagList.add(Integer.parseInt(br.readLine()));
        }

        jewelList.sort((o1, o2) -> {
            return o1.m - o2.m;
        });
        Collections.sort(bagList);
        PriorityQueue<Jewel> pq = new PriorityQueue<>((o1, o2) -> { // 가치가 크고, 무게가 작은 순 정렬
            if(o2.v == o1.v) {
                return o1.m - o2.m;
            }

            return o2.v - o1.v;
        });

        int index = 0;
        long sum = 0;
        // 가방 하나씩 돌면서 현재 가방 무게에 담을 수 있는 모든 보석을 pq에 넣는다.
        for(int i = 0; i < bagList.size(); i++) {
            int m = bagList.get(i);
            while(index < jewelList.size() && jewelList.get(index).m <= m) {
                pq.offer(jewelList.get(index++));
            }

            if(!pq.isEmpty()) { // 가장 큰 가치의 보석을 더해준다.
                sum += pq.poll().v;
            }
        }

        System.out.println(sum);
    }
}
