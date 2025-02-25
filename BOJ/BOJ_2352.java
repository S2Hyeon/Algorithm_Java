import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// [G2] 반도체 설계
public class BOJ_2352 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] input = new int[N];
        ArrayList<Integer> lis = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        lis.add(input[0]);

        for(int i = 1; i < N; i++) {
            if(input[i] > lis.get(lis.size() - 1)) {
                lis.add(input[i]);
            } else {
                int index = Collections.binarySearch(lis, input[i]);
                if(index < 0) {
                    index = -index - 1;
                }

                lis.set(index, input[i]);
            }
        }

        System.out.println(lis.size());
    }
}
