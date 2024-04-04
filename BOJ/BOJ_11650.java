import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// [S5] 좌표 정렬하기
public class BOJ_11650 {

    static class Position implements Comparable<Position> {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Position p) {
            if(this.x == p.x) {
                return this.y - p.y;
            }

            return this.x - p.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Position> list = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.add(new Position(x, y));
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            Position p = list.get(i);
            sb.append(p.x).append(" ").append(p.y).append("\n");
        }

        System.out.println(sb);
    }
}
