import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [S1] 트리 순회
public class BOJ_1991 {

    static class Node {
        char value;
        Node left;
        Node right;

        Node(char value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    static int N;
    static Node[] nodes;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];
        sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char value = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if(nodes[value - 'A'] == null) {
                nodes[value - 'A'] = new Node(value);
            }

            if('.' != left) {
                Node leftNode = new Node(left);
                nodes[left - 'A'] = leftNode;
                nodes[value - 'A'].left = leftNode;
            }

            if('.' != right) {
                Node rightNode = new Node(right);
                nodes[right - 'A'] = rightNode;
                nodes[value - 'A'].right = rightNode;
            }
        }

        preOrder(nodes[0]);
        sb.append("\n");
        inOrder(nodes[0]);
        sb.append("\n");
        postOrder(nodes[0]);

        System.out.println(sb);

    }

    private static void preOrder(Node node) {
        if(node == null) {
            return;
        }

        sb.append(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    private static void inOrder(Node node) {
        if(node == null) {
            return;
        }

        inOrder(node.left);
        sb.append(node.value);
        inOrder(node.right);
    }

    private static void postOrder(Node node) {
        if(node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.value);
    }


}
