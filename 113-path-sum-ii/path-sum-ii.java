import java.util.*;

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode node, int sum, List<Integer> path,
                     List<List<Integer>> result) {
        if (node == null) return;

        path.add(node.val);
        sum -= node.val;

        if (node.left == null && node.right == null && sum == 0) {
            result.add(new ArrayList<>(path));
        } else {
            dfs(node.left, sum, path, result);
            dfs(node.right, sum, path, result);
        }

        path.remove(path.size() - 1);
    }
}