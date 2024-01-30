import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ_16434 드래곤 앤 던전
public class BOJ_16434 {

    static class Dungeon {
        int t, a, h;

        public Dungeon(int t, int a, int h) {
            this.t = t; // 방의 타입 : 1 -> 몬스터방, 2 -> 포션방
            this.a = a; // 몹의 공격력 or 공격력 포션
            this.h = h; // 몹의 체력 or HP 포션
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long initATK = Long.parseLong(st.nextToken());
        Dungeon[] dungeons = new Dungeon[N];
        long left = 0;
        long right = Long.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            dungeons[i] = new Dungeon(t, a, h);
        }

        while (left < right) {
            long curMaxHP = (left + right) / 2; // 찾고자 하는 최대 HP 값
            long curHP = curMaxHP; // 던전을 지나면서 변하게 될 현재 HP 값
            long curATK = initATK; // 던전을 지나면서 변할 수 있는 현재 공격력
            boolean gameOver = false;
            for (int i = 0; i < N; i++) {
                Dungeon dungeon = dungeons[i];
                if (dungeon.t == 1) {
                    int mobATK = dungeon.a;
                    int mobHP = dungeon.h;
                    double mobTurn = Math.ceil((double) curHP / mobATK); // 몹이 플레이어를 죽이게 되는 턴 수
                    double playerTurn = Math.ceil((double) mobHP / curATK); // 플레이어가 몹을 죽이게 되는 턴 수
                    if (mobTurn >= playerTurn) { // 플레이어 승리 시
                        // 플레이어가 몹을 죽이기 바로 직전까지 몹이 공격하므로 해당 수치만큼 플레이어 HP 감소
                        curHP -= (long) (mobATK * (playerTurn - 1));
                    } else { // 몹 승리 시 최대 HP를 늘려야 하므로 게임 종료
                        left = curMaxHP + 1;
                        gameOver = true;
                        break;
                    }

                } else if (dungeon.t == 2) {
                    curATK += dungeon.a;
                    curHP = Math.min(curHP + dungeon.h, curMaxHP); // 최대 HP를 초과할 수 없다.
                }
            }

            if (!gameOver) {
                right = curMaxHP;
            }
        }

        System.out.println(left);

    }
}
