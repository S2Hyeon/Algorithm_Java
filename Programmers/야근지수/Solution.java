package 야근지수;

import java.util.Collections;
import java.util.PriorityQueue;

// Lv. 3 야근지수
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < works.length; i++) {
            pq.offer(works[i]);
        }

        // 남은 작업량의 제곱을 모두 더해야 하므로
        // 가장 큰 수부터 1씩 빼준다
        while(!pq.isEmpty() && n > 0) {
            int work = pq.poll();
            work--;
            n--;
            if(work > 0) { // 작업량이 남아있다면 다시 큐에 넣어준다
                pq.offer(work);
            }
        }

        while(!pq.isEmpty()) {
            answer += (int) Math.pow(pq.poll(), 2);
        }

        return answer;
    }
}
