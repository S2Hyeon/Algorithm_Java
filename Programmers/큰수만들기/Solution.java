package 큰수만들기;

import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for(int i = 0; i < number.length(); i++) {
            char nextNumber = number.charAt(i);
            // 다음 숫자가 스택에 들어있는 숫자보다 작을 때 까지 pop
            while(!stack.isEmpty() && stack.peek() < nextNumber && count != k) {
                stack.pop();
                count++;
            }

            stack.push(nextNumber);
        }

        int length = number.length() - k; // number에서 k개의 숫자를 뺀 길이
        for(int i = 0; i < length; i++) {
            sb.append(stack.get(i));
        }

        return sb.toString();
    }
}