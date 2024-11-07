import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// [G4] 휴게소 세우기
public class BOJ_1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        ArrayList<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        list.add(0);
        list.add(L);
        Collections.sort(list);
        int left = 1; // 구간의 길이는 1부터 시작
        int right = L - 1; // 도로의 끝에는 휴게소를 세울 수 없다

        while(left < right) {
            int mid = (left + right) / 2; // 구간의 길이
            int count = 0;

            for(int i = 1; i < list.size(); i++) {
                // 구간마다 현재 길이(mid)간격으로 설치할 수 있는 휴게소 개수를 구한다
                count += (list.get(i) - list.get(i - 1) - 1) / mid;
            }

            if(count > M) { // 설치할 수 있는 휴게소의 개수가 많다면
                left = mid + 1; // 구간의 길이를 늘린다
            } else {
                right = mid; // 개수가 일치해도 더 짧은 길이로 조건을 만족할 수 있는지 탐색한다
            }
        }

        System.out.println(left);
    }
}