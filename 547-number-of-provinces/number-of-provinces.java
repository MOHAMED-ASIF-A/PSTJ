class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                provinces++;
                dfs(isConnected, visited, i);
            }
        }

        return provinces;
    }

    private void dfs(int[][] graph, boolean[] visited, int city) {
        visited[city] = true;

        for (int i = 0; i < graph.length; i++) {
            if (graph[city][i] == 1 && !visited[i]) {
                dfs(graph, visited, i);
            }
        }
    }
}