import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// [G2] 가장 긴 증가하는 부분 수열 2
public class BOJ_12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        ArrayList<Integer> lis = new ArrayList<>();

        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis.add(arr[0]);

        for(int i = 1; i < arr.length; i++) {
            // lis 배열의 가장 큰 수보다 큰 수이면 lis 배열에 추가
            if(lis.get(lis.size() - 1) < arr[i]) {
                lis.add(arr[i]);
            } else { // 작은 수 이면 lis[index] 원소와 교체
                int index = Collections.binarySearch(lis, arr[i]); // lis 배열에서 현재 원소가 들어갈 위치
                if(index < 0) {
                    // binarySearch는 키 값을 찾지 못했을 때 -(insertion point) - 1을 반환한다.
                    // 따라서 들어가야 할 위치를 양수로 조정해준다.
                    index = -(index + 1);
                }

                lis.set(index, arr[i]); // lis[index] 원소와 교체
            }
        }

        System.out.println(lis.size());
    }
}
