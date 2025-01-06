import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// [G5] 숨바꼭질 3
public class BOJ_13549 {

    static class Location {
        int index, time;

        public Location(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[100001]; // 현재 인덱스에 도착한 최소 시간
        Arrays.fill(arr, Integer.MAX_VALUE);

        System.out.println(bfs(N, M));

    }

    private static int bfs(int start, int end) {
        Queue<Location> queue = new ArrayDeque<>();
        queue.offer(new Location(start, 0));
        arr[start] = 0;
        int min = Integer.MAX_VALUE;

        while(!queue.isEmpty()) {
            Location location = queue.poll();
            int curIndex = location.index;
            int curTime = location.time;
            if(curIndex == end) {
                // 먼저 도착해도 최소 시간이 아닐 수 있기 때문에 바로 리턴하지 않고
                // 최소값을 갱신하는 방법을 사용한다
                min = Math.min(min, curTime);
            }

            if(isIn(curIndex + 1) && arr[curIndex + 1] > curTime + 1) {
                arr[curIndex + 1] = curTime + 1;
                queue.offer(new Location(curIndex + 1, curTime + 1));
            }

            if(isIn(curIndex - 1) && arr[curIndex - 1] > curTime + 1) {
                arr[curIndex - 1] = curTime + 1;
                queue.offer(new Location(curIndex - 1, curTime + 1));
            }

            if(isIn(curIndex * 2) && arr[curIndex * 2] > curTime) {
                arr[curIndex * 2] = curTime;
                queue.offer(new Location(curIndex * 2, curTime));
            }
        }

        return min;
    }

    private static boolean isIn(int index) {
        return index >= 0 && index < arr.length;
    }
}
