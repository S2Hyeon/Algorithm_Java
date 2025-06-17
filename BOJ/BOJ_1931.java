import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// [G5] 회의실 배정
public class BOJ_1931 {
    static class Time implements Comparable<Time> {
        int start, end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time t) {
            if(this.end != t.end) {
                return this.end - t.end;
            }

            // 같은 시간에 끝나는 회의가 있다면 시작 시간을 오름차순으로 정렬
            // 먼저 시작한 회의와 나중에 시작한 회의 둘 다 진행할 수 있는 경우가 있다.
            // ex)
            // 3
            // 2 2
            // 1 2
            // 2 3
            // -> 3개 모두 진행 가능
            return this.start - t.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Time> timeList = new ArrayList<>();
        int count = 1;

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            timeList.add(new Time(start, end));
        }

        Collections.sort(timeList);
        int endTime = timeList.get(0).end;

        for(int i = 1; i < N; i++) {
            Time t = timeList.get(i);
            int nextStartTime = t.start;
            if(nextStartTime >= endTime) {
                endTime = t.end;
                count++;
            }
        }

        System.out.println(count);
    }
}
