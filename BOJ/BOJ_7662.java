import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [G4] 이중 우선순위 큐
public class BOJ_7662 {
    static PriorityQueue<Integer> maxHeap; // 최대 힙
    static PriorityQueue<Integer> minHeap; // 최소 힙
    static Map<Integer, Integer> map; // Map<힙에 존재하는 숫자, 개수>

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 0; tc < T; tc++) {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
            map = new HashMap<>();
            int k = Integer.parseInt(br.readLine());

            for(int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int value = Integer.parseInt(st.nextToken());
                solution(command, value);
            }

            if(map.isEmpty()) {
                sb.append("EMPTY");

            } else {
                int max = maxHeap.poll();
                int min = minHeap.poll();
                // 맵에 존재하는 유효한 숫자가 나올때까지 poll
                while(!map.containsKey(max)) {
                    max = maxHeap.poll();
                }

                // 맵에 존재하는 유효한 숫자가 나올때까지 poll
                while(!map.containsKey(min)) {
                    min = minHeap.poll();
                }

                sb.append(max).append(" ").append(min);
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void solution(String command, int value) {
        if ("I".equals(command)) {
            insert(value);

        } else if ("D".equals(command)) {
            delete(value);
        }
    }

    private static void insert(int value) {
        maxHeap.offer(value);
        minHeap.offer(value);
        if(map.containsKey(value)) { // 맵에 존재하면 개수 증가
            map.put(value, map.get(value) + 1);
        } else { // 맵에 존재하지 않으면 새로 생성
            map.put(value, 1);
        }

    }

    private static void delete(int value) {
        if(map.isEmpty()) return;

        if(value == 1) { // 최대값 삭제
            int number = maxHeap.poll();
            while(!map.containsKey(number)) { // 유효하지 않은 숫자 제거
                number = maxHeap.poll();
            }

            updateMap(number); // 유효숫자 개수 업데이트

        } else if(value == -1) { // 최소값 삭제
            int number = minHeap.poll();
            while(!map.containsKey(number)) { // 유효하지 않은 숫자 제거
                number = minHeap.poll();
            }

            updateMap(number); // 유효숫자 개수 업데이트
        }
    }

    private static void updateMap(int number) {
        if(map.get(number) > 1) { // 2개 이상인경우 개수 감소
            map.put(number, map.get(number) - 1);
        } else { // 1개라면 맵에서 삭제
            map.remove(number);
        }
    }

}
