import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// [G3] 두 배열의 합
public class BOJ_2143 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arrA = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        getSumArr(arrA, N); // 누적합 구하기

        int M = Integer.parseInt(br.readLine());
        int[] arrB = new int[M + 1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= M; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        getSumArr(arrB, M); // 누적합 구하기

        // 누적합을 이용하여 부 배열에서 발생 가능한 모든 합 구하기
        ArrayList<Integer> listA = getWholeCase(arrA, N);
        ArrayList<Integer> listB = getWholeCase(arrB, M);

        Collections.sort(listB); // 이분탐색할 리스트 정렬
        long answer = 0;

        for(int i = 0; i < listA.size(); i++) {
            int value1 = listA.get(i);
            // 키 값의 개수를 구한다.
            long count = upperBound(listB, T - value1) - lowerBound(listB, T - value1);
            if(count <= 0) continue; // 키 값을 찾지 못한 경우

            answer += count;
        }

        System.out.println(answer);
    }

    private static void getSumArr(int[] arr, int N) {
        for(int i = 1; i <= N; i++) {
            arr[i] = arr[i] + arr[i - 1];
        }
    }
    private static ArrayList<Integer> getWholeCase(int[] arr, int length) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            for(int j = i + 1; j <= length; j++) {
                list.add(arr[j] - arr[i]);
            }
        }

        return list;
    }

    private static int lowerBound(ArrayList<Integer> listB, int value2) {
        int left = 0;
        int right = listB.size();
        boolean found = false;

        while(left < right) {
            int mid = (left + right) / 2;

            if(value2 > listB.get(mid)) {
                left = mid + 1;
            } else {
                if(value2 == listB.get(mid)) {
                    found = true;
                }
                right = mid;
            }
        }

        if(found) {
            return left;
        } else {
            return -1;
        }
    }

    private static int upperBound(ArrayList<Integer> listB, int value2) {
        int left = 0;
        int right = listB.size();
        boolean found = false;

        while(left < right) {
            int mid = (left + right) / 2;

            if(value2 >= listB.get(mid)) {
                if(value2 == listB.get(mid)) {
                    found = true;
                }
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if(found) {
            // Upper Bound는 키 값보다 커지는 첫 원소의 인덱스를 반환한다.
            // 만약 키 값이 연속되어 배열의 끝까지 다다른다면 left + 1을 하여 개수를 세어야 한다.
            if(left == listB.size() - 1 && listB.get(listB.size() - 2).equals(listB.get(listB.size() - 1))) {
                left++;
            }
            return left;

        } else {
            return -1;
        }
    }
}
