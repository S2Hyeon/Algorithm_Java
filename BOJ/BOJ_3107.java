import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ_3107 IPv6
public class BOJ_3107 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int MAX_LENGTH = 8; // IPv6의 바이트 갯수
        String[] byteArr =  input.split(":");
        int count = MAX_LENGTH - byteArr.length + 1; // 생략된 바이트 갯수
        StringBuilder sb = new StringBuilder();
        boolean flag = false; // :: 사용 여부

        for(int i = 0; i < byteArr.length; i++) { // 바이트별로 처리
            if("".equals(byteArr[i]) && !flag) { // 맨 앞 혹은 중간에 ::가 있다면
                for(int j = 0; j < count; j++) {
                    sb.append("0000:");
                }
                count = 0;
                flag = true;
            }
            else {
                for(int j = 0; j < 4 - byteArr[i].length(); j++) { // 축약된 부분 있으면 앞자리부터 0으로 채우기
                    sb.append(0);
                }
                for(int j = 0; j < byteArr[i].length(); j++) { // 나머지 값 채우기
                    sb.append(byteArr[i].charAt(j));
                }
                sb.append(":");
            }
        }

        if(count > 0) { // ::로 끝나면
            for(int i = 0; i < count - 1; i++) {
                sb.append("0000:");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}