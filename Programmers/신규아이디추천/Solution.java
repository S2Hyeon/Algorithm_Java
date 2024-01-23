package 신규아이디추천;

class Solution {
    public String solution(String new_id) {
        String answer = step7(step6(step5(step4(step3(step2(step1(new_id)))))));
        return answer;
    }

    public String step1(String str) { // 1단계
        return str.toLowerCase();
    }

    public String step2(String str) { // 2단계
        return str.replaceAll("[^a-z0-9._-]", "");
    }

    public String step3(String str) { // 3단계
        return str.replaceAll("[.]{2,}", ".");
    }

    public String step4(String str) { // 4단계
        return str.replaceAll("^\\.|\\.$", "");
    }

    public String step5(String str) { // 5단계
        if (str.isEmpty()) {
            str = "a";
        }

        return str;
    }

    public String step6(String str) { // 6단계
        if (str.length() >= 16) {
            str = str.substring(0, 15);
            str = str.replaceAll("\\.$", "");
        }

        return str;
    }

    public String step7(String str) { // 7단계
        StringBuilder sb = new StringBuilder(str);
        char last = str.charAt(str.length() - 1);
        while (sb.length() <= 2) {
            sb.append(last);
        }

        return sb.toString();
    }

}
