import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [G4] 강의실 배정
public class BOJ_11000 {
    /*
        - 초기 방법(오답)
        강의 끝나는 시간을 기준으로 오름차순
            현재 강의 이전에 끝나는 강의가 있는지 판별하는 과정 생략

        queue에 현재 진행중인 회의만 넣는다.(queue.size = 필요한 강의실 최대 개수)
        이전 강의 끝나는 시간 <= 현재 강의 시작 시간
            => 이전 강의가 끝난 강의실을 바로 쓸 수 있다. (동등 시간 포함)
            => 끝난 강의는 모두 dequeue
        이전 강의 끝나는 시간 > 현재 강의 시작 시간
            => 추가 강의 필요하므로 enqueue

        - 반례
            4
            1 10
            2 3
            2 3
            3 4
            => result : 2
            => answer : 3

            [1, 10] 강의 1개 + [2, 3] 강의 때문에 2개의 회의실 사용하므로
            3개가 되어야 함.([3, 4]는 [2, 3] 2개 강의가 끝난 후 진행하므로 이 때 강의실 사용 개수는 2개)
            즉, 현재 강의보다 일찍 시작하는 강의가 뒤에서 나올 수 있고, 이 경우 문제가 발생한다.
                => 시작 시간을 기준으로 오름차순 정렬

        - 풀이 방법
        강의 시작 시간을 기준으로 오름차순
            가장 일찍 시작하는 강의부터 검토하여 강의 진행중이면 새로운 강의실 사용
            현재 사용중인 강의실 중 가장 빨리 끝나는 시간 > 다음 강의 시작 시간
                => 새로운 강의실 사용
                => PQ 사용.
     */

    static class Lecture implements Comparable<Lecture> {
        int startTime, endTime;

        public Lecture(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Lecture l) {
            if(this.startTime == l.startTime) {
                return this.endTime - l.endTime;
            }

            return this.startTime - l.startTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Lecture> inputList = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            inputList.add(new Lecture(startTime, endTime));
        }

        Collections.sort(inputList);
        pq.offer(inputList.get(0).endTime);

        for(int i = 1; i < N; i++) {
            Lecture lecture = inputList.get(i);
            int startTime = lecture.startTime;
            int endTime = lecture.endTime;;

            if(pq.peek() <= startTime) {
                pq.poll();
            }

            pq.offer(endTime);
        }

        System.out.println(pq.size());
    }
}
