import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// [S4] 카드
public class BOJ_11652 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Long, Integer> map = new HashMap<>();
        long maxNum = 0;
        int maxCount = 0;

        for(int i = 0; i < N; i++) {
            long key = Long.parseLong(br.readLine());
            int value = map.getOrDefault(key, 0);
            map.put(key, value + 1);
        }

        for(long n : map.keySet()) {
            int count = map.get(n);

            if(count == maxCount) {
                if(n < maxNum) {
                    maxNum = n;
                }
            } else if(count > maxCount) {
                maxNum = n;
                maxCount = count;
            }
        }

        System.out.println(maxNum);
    }
}
