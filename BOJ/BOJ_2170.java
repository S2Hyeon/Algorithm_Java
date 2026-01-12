import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [G5] 선 긋기
public class BOJ_2170 {

    static class Line implements Comparable<Line> {
        int x, y;

        public Line(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Line l) {
            if(this.x == l.x) {
                return this.y - l.y;
            }

            return this.x - l.x;
        }

    }

    /*
        모든 선을 시작 점 기준으로 오름차순 정렬하여 처리순서 확정.
            정렬하지 않으면 이전의 선들 중 겹치는 선이 있는지 계속 확인해줘야함. => O(N^2)
        앞선 선과 겹치면 끝점만 연장하고, 아예 끊겨 있다면 새로운 길이를 더한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Line> pq = new PriorityQueue<>();
        int length = 0;

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pq.offer(new Line(x, y));
        }

        Line firstLine = pq.poll();
        int prevX = firstLine.x;
        int prevY = firstLine.y;
        length += prevY - prevX; // 입력값 x < y 보장됨

        while(!pq.isEmpty()) {
            Line line = pq.poll();
            int x = line.x;
            int y = line.y;

            if(prevX <= x && prevY >= x && prevY < y) {
                length += y - prevY;
                prevY = y;
            } else if(prevY < x) {
                length += y - x;
                prevX = x;
                prevY = y;
            }
        }

        System.out.println(length);
    }
}
