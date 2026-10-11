class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            groupGraph.add(new ArrayList<>());
        }

        int[] itemDegree = new int[n];
        int[] groupDegree = new int[m];

        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                itemGraph.get(prev).add(i);
                itemDegree[i]++;

                if (group[prev] != group[i]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupDegree[group[i]]++;
                }
            }
        }

        List<Integer> items = topoSort(itemGraph, itemDegree);
        List<Integer> groups = topoSort(groupGraph, groupDegree);

        if (items.size() != n || groups.size() != m) {
            return new int[0];
        }

        List<List<Integer>> groupedItems = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            groupedItems.add(new ArrayList<>());
        }

        for (int item : items) {
            groupedItems.get(group[item]).add(item);
        }

        int[] result = new int[n];
        int index = 0;

        for (int g : groups) {
            for (int item : groupedItems.get(g)) {
                result[index++] = item;
            }
        }

        return result;
    }

    private List<Integer> topoSort(List<List<Integer>> graph, int[] degree) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);

            for (int next : graph.get(node)) {
                if (--degree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return order;
    }
}