package 이중우선순위큐;

import java.util.Collections;
import java.util.LinkedList;

// Lv. 3 이중우선순위큐
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        LinkedList<Integer> deque = new LinkedList<>();

        for(int i = 0; i < operations.length; i++) {
            String[] operation = operations[i].split(" ");
            String command = operation[0];
            int data = Integer.parseInt(operation[1]);

            if("I".equals(command)) {
                deque.offerLast(data);
                Collections.sort(deque);
            } else if(!deque.isEmpty()) {
                if(data == -1) {
                    deque.pollFirst();
                } else {
                    deque.pollLast();
                }
            }
        }

        if(!deque.isEmpty()) {
            answer[0] = deque.peekLast();
            answer[1] = deque.peekFirst();
        }

        return answer;
    }
}