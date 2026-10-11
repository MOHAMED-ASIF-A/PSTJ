import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0] + nums2[a[1]],
                                      b[0] + nums2[b[1]])
        );

        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            pq.offer(new int[]{nums1[i], 0});
        }

        while (k-- > 0 && !pq.isEmpty()) {
            int[] pair = pq.poll();
            int u = pair[0], j = pair[1];

            result.add(Arrays.asList(u, nums2[j]));

            if (j + 1 < nums2.length) {
                pq.offer(new int[]{u, j + 1});
            }
        }

        return result;
    }
}