package 불량사용자;

import java.util.HashSet;
import java.util.Set;

// Lv. 3 불량사용자
class Solution {

    Set<Set<String>> resultSet;
    String[] userIds, bannedIds;

    public int solution(String[] user_id, String[] banned_id) {
        resultSet = new HashSet<>();
        userIds = user_id;
        bannedIds = banned_id;

        dfs(new HashSet<>(), 0);

        return resultSet.size();
    }

    private void dfs(Set<String> set, int index) {
        if(index == bannedIds.length) {
            // Set의 HashCode는 원소들의 해시 값을 모두 더한 값이 되므로
            // 같은 문자열이 담긴 Set이 중복되어 추가되지 않는다.
            resultSet.add(set);
            return;
        }

        for(int i = 0; i < userIds.length; i++) {
            if(set.contains(userIds[i])) { // 이미 제재 후보군에 올라간 Id인 경우
                continue;
            }

            if(check(userIds[i], bannedIds[index])) { // Id가 제재 후보인지 판별
                set.add(userIds[i]); // 제재 후보이면 set에 넣는다.
                dfs(new HashSet<>(set), index + 1); // deep copy로 매개변수 전달
                set.remove(userIds[i]); // 다음 후보를 찾기 위해 set에서 제거
            }
        }
    }

    // 문자열 비교
    private boolean check(String userId, String bannedId) {
        if(userId.length() != bannedId.length()) {
            return false;
        }

        for(int i = 0; i < userId.length(); i++) {
            char c1 = userId.charAt(i);
            char c2 = bannedId.charAt(i);
            if(c1 != c2 && '*' != c2) {
                return false;
            }
        }

        return true;
    }
}
