import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ_1374 강의실
public class BOJ_1374 {
    static class Lecture implements Comparable<Lecture> {
        int lectureNum, startTime, endTime;

        public Lecture(int lectureNum, int startTime, int endTime) {
            this.lectureNum = lectureNum;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Lecture lecture) {
            if(this.startTime == lecture.startTime) {
                return this.endTime - lecture.endTime;
            }

            return this.startTime - lecture.startTime;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Lecture> list = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 진행중인 강의들
        StringTokenizer st;
        int result = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int lectureNum = Integer.parseInt(st.nextToken());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            list.add(new Lecture(lectureNum, startTime, endTime));
        }

        Collections.sort(list); // 시작시간이 빠른 순으로 정렬

        for(int i = 0; i < N; i++) {
            // 강의의 시작 시간이 진행중인 강의의 가장 빠른 종료 시간보다 크거나 같으면
            // 진행중인 강의가 종료된 후에 시작하는 것이므로 poll
            while(!pq.isEmpty() && pq.peek() <= list.get(i).startTime) {
                pq.poll();
            }

            pq.offer(list.get(i).endTime); // 종료시간이 빠른 순으로 offer
            result = Math.max(result, pq.size()); // pq의 크기 == 진행중인 강의의 개수
        }

        System.out.println(result);
    }
}
