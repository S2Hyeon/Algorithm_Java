import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// [S2] 가장 긴 증가하는 부분 수열
public class BOJ_11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        ArrayList<Integer> lis = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis.add(arr[0]);

        for(int i = 1; i < N; i++) {

            // lis 배열의 가장 큰 수보다 큰 수이면 lis 배열에 추가
            if(arr[i] > lis.get(lis.size() - 1)) {
                lis.add(arr[i]);
            } else { // 작은 수 이면 lis[index] 원소와 교체
                // lis 배열에서 현재 원소가 들어갈 위치
                // binarySearch의 반환값은 (-(insertion point) - 1)이다.
                int index = Math.abs(Collections.binarySearch(lis, arr[i])) - 1;

                // 이미 있는 값이라면 교체할 필요 없음
                if(!lis.contains(arr[i])) {
                    lis.set(index, arr[i]);
                }
            }
        }

        System.out.println(lis.size());
    }
}
