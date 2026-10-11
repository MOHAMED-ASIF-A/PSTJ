import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] red = new ArrayList[n];
        List<Integer>[] blue = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            red[i] = new ArrayList<>();
            blue[i] = new ArrayList<>();
        }

        for (int[] e : redEdges) red[e[0]].add(e[1]);
        for (int[] e : blueEdges) blue[e[0]].add(e[1]);

        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        boolean[][] visited = new boolean[n][2];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0, 0});
        q.offer(new int[]{0, 1});
        visited[0][0] = visited[0][1] = true;
        ans[0] = 0;

        int distance = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            distance++;

            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int node = cur[0], color = cur[1];

                List<Integer> next = color == 0 ? blue[node] : red[node];

                for (int neighbor : next) {
                    int nextColor = 1 - color;

                    if (!visited[neighbor][nextColor]) {
                        visited[neighbor][nextColor] = true;
                        q.offer(new int[]{neighbor, nextColor});

                        if (ans[neighbor] == -1) {
                            ans[neighbor] = distance;
                        }
                    }
                }
            }
        }

        return ans;
    }
}