import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [S3] 영단어 암기는 괴로워
public class BOJ_20920 {

    static class Word implements Comparable<Word> {
        String word;
        int count;

        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public int compareTo(Word w) {
            if(this.count != w.count) {
                return w.count - this.count;
            }

            if(this.word.length() != w.word.length()) {
                return w.word.length() - this.word.length();
            }

            return this.word.compareTo(w.word); // 사전 순 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();
        PriorityQueue<Word> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            String word = br.readLine();
            if(word.length() < M) {
                continue;
            }

            int count = 0;
            if(map.containsKey(word)) {
                count = map.get(word) + 1;
            }

            map.put(word, count);
        }

        for(String word : map.keySet()) {
            int count = map.get(word);
            pq.offer(new Word(word, count));
        }

        while(!pq.isEmpty()) {
            Word w = pq.poll();
            sb.append(w.word).append("\n");
        }

        System.out.println(sb);
    }
}
