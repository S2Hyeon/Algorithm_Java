import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    BOJ_1379 강의실 2
    강의가 시작하는 시점에 끝난 강의가 하나라도 있다면 끝난 강의의 강의실 번호를 부여받고, 끝난 강의 없다면 강의실 추가
*/
public class BOJ_1379 {
    static class Lecture {
        int lectureNumber, startTime, endTime, roomNumber;

        public Lecture(int lectureNumber, int startTime, int endTime, int roomNumber) {
            this.lectureNumber = lectureNumber;
            this.startTime = startTime;
            this.endTime = endTime;
            this.roomNumber = roomNumber; // 배정받을 강의실 번호
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 진행중이거나, 이미 끝난 강의들
        // 즉, pq에는 진행중인 강의들과 새로 시작되는 강의에 강의실 번호를 물려줄 이미 끝난 강의가 함께 저장된다.
        PriorityQueue<Lecture> runningQueue = new PriorityQueue<>((lecture1, lecture2) -> {
            if(lecture1.endTime == lecture2.endTime) { // 끝시간, 강의번호를 우선순위로
                return lecture1.lectureNumber - lecture2.lectureNumber;
            }

            return lecture1.endTime - lecture2.endTime;
        });

        int N = Integer.parseInt(br.readLine());
        ArrayList<Lecture> schedule = new ArrayList<>(); // 입력값으로 주어지는 강의 스케줄
        int[] lectureRoomNumber = new int[N + 1]; // 강의별로 배정받은 강의실 번호들을 저장
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int lectureNumber = Integer.parseInt(st.nextToken());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            schedule.add(new Lecture(lectureNumber, startTime, endTime, -1));
        }

        schedule.sort((lecture1, lecture2) -> {
            if(lecture1.startTime == lecture2.startTime) { // 시작시간, 강의번호를 우선순위로
                return lecture1.lectureNumber - lecture2.lectureNumber;
            }

            return lecture1.startTime - lecture2.startTime;
        });

        // 제일 먼저 시작하는 강의를 pq에 넣고, 강의실 번호 배정
        Lecture firstLecture = schedule.get(0);
        runningQueue.offer(firstLecture);
        int roomNumber = 1;
        firstLecture.roomNumber = roomNumber;
        lectureRoomNumber[firstLecture.lectureNumber] = roomNumber;

        for(int i = 1; i < N; i++) {
            Lecture lecture = schedule.get(i); // 새롭게 시작할 강의
            Lecture runningLecture = runningQueue.poll(); // 진행중이거나, 이미 끝난 강의

            if(runningLecture.endTime <= lecture.startTime) { // 끝난 강의가 있다면
                // 끝난 강의의 강의실 번호를 그대로 사용
                int receivedRoomNumber = runningLecture.roomNumber;
                lecture.roomNumber = receivedRoomNumber;
                lectureRoomNumber[lecture.lectureNumber] = receivedRoomNumber;
            }
            else { // 끝난 강의가 없다면
                runningQueue.offer(runningLecture); // 진행중이었던 강의 다시 pq에 넣기
                lecture.roomNumber = ++roomNumber; // 강의실 추가
                lectureRoomNumber[lecture.lectureNumber] = roomNumber;
            }

            runningQueue.offer(lecture);
        }

        // pq에 남는 강의의 수만큼 강의실이 필요하다
        System.out.println(runningQueue.size());
        for(int i = 1; i <= N; i++) {
            sb.append(lectureRoomNumber[i]).append("\n");
        }

        System.out.println(sb);
    }
}
