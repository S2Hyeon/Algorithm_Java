import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ_2141 우체국
class BOJ_2141 {
    static class Town implements Comparable<Town> {
        int location, people;

        Town(int location, int people) {
            this.location = location;
            this.people = people;
        }

        @Override
        public int compareTo(Town o) {
            return this.location - o.location;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        ArrayList<Town> list = new ArrayList<>();
        long min = Long.MAX_VALUE;
        int result = -1;
        long wholePeople = 0; // 전체 인원 수
        long left = 0; // 현재 마을 기준 왼쪽에 있는 인원 수 합
        long right; // 현재 마을 기준 오른쪽에 있는 인원 수 합

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int location = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            list.add(new Town(location, people));
            wholePeople += people;
        }

        Collections.sort(list); // 마을 위치순으로 오름차순
        right = wholePeople;

        for (int i = 0; i < N; i++) {
            int curLocationPeople = list.get(i).people;
            right -= curLocationPeople;
            long temp = Math.abs(left - right); // 왼쪽과 오른쪽의 인원수 차이
            if (temp < min) { // 인원수 차이가 가장 적은 마을의 위치가 정답
                min = temp;
                result = list.get(i).location;
            }

            left += curLocationPeople;
        }

        System.out.println(result);
    }
}