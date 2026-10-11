import java.util.*;

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<int[]> q = new LinkedList<>();
        Queue<TreeNode> nodes = new LinkedList<>();

        q.offer(new int[]{0, 0});
        nodes.offer(root);

        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            int[] pos = q.poll();
            int row = pos[0], col = pos[1];

            map.putIfAbsent(col, new TreeMap<>());
            map.get(col).putIfAbsent(row, new PriorityQueue<>());
            map.get(col).get(row).offer(node.val);

            if (node.left != null) {
                nodes.offer(node.left);
                q.offer(new int[]{row + 1, col - 1});
            }

            if (node.right != null) {
                nodes.offer(node.right);
                q.offer(new int[]{row + 1, col + 1});
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        for (TreeMap<Integer, PriorityQueue<Integer>> rows : map.values()) {
            List<Integer> column = new ArrayList<>();
            for (PriorityQueue<Integer> values : rows.values()) {
                while (!values.isEmpty()) {
                    column.add(values.poll());
                }
            }
            result.add(column);
        }

        return result;
    }
}