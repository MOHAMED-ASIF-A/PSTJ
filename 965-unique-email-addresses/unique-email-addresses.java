import java.util.*;

class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> unique = new HashSet<>();

        for (String email : emails) {
            String[] parts = email.split("@");
            String local = parts[0];
            String domain = parts[1];

            StringBuilder sb = new StringBuilder();

            for (char ch : local.toCharArray()) {
                if (ch == '+') break;
                if (ch != '.') sb.append(ch);
            }

            unique.add(sb.toString() + "@" + domain);
        }

        return unique.size();
    }
}