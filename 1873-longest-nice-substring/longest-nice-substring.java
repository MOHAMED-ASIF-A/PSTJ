class Solution {
    public String longestNiceSubstring(String s) {
        for (int len = s.length(); len > 0; len--) {
            for (int i = 0; i + len <= s.length(); i++) {
                String sub = s.substring(i, i + len);

                if (isNice(sub)) {
                    return sub;
                }
            }
        }
        return "";
    }

    private boolean isNice(String s) {
        for (char c : s.toCharArray()) {
            if (s.indexOf(Character.toLowerCase(c)) == -1 ||
                s.indexOf(Character.toUpperCase(c)) == -1) {
                return false;
            }
        }
        return true;
    }
}