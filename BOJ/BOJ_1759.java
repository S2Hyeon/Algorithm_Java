import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [G5] 암호 만들기
public class BOJ_1759 {
    static char[] selected, inputs;
    static StringBuilder sb;
    static int L, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        selected = new char[L];
        inputs = new char[C];
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < C; i++) {
            inputs[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(inputs);
        combination(0, 0);

        System.out.println(sb);
    }

    private static void combination(int inputIndex, int selectIndex) {
        if(selectIndex == L) {
            if(check()) {
                for(char c : selected) {
                    sb.append(c);
                }
                sb.append("\n");
            }

            return;
        }

        if(inputIndex >= C) {
            return;
        }

        selected[selectIndex] = inputs[inputIndex];
        combination(inputIndex + 1, selectIndex + 1);
        combination(inputIndex + 1, selectIndex);
    }

    private static boolean check() {
        int vowelCount = 0;
        int consonantCount = 0;

        for(char c : selected) {
            switch(c) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    vowelCount++;
                    break;
                default:
                    consonantCount++;
            }
        }

        return vowelCount >= 1 && consonantCount >= 2;
    }
}
