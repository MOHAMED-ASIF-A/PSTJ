import java.util.*;

class Solution {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> max = new ArrayDeque<>();
        Deque<Integer> min = new ArrayDeque<>();

        int left = 0, result = 0;

        for (int right = 0; right < nums.length; right++) {
            while (!max.isEmpty() && max.peekLast() < nums[right])
                max.pollLast();
            max.offerLast(nums[right]);

            while (!min.isEmpty() && min.peekLast() > nums[right])
                min.pollLast();
            min.offerLast(nums[right]);

            while ((long) max.peekFirst() - min.peekFirst() > limit) {
                if (max.peekFirst() == nums[left])
                    max.pollFirst();
                if (min.peekFirst() == nums[left])
                    min.pollFirst();
                left++;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;
    }
}