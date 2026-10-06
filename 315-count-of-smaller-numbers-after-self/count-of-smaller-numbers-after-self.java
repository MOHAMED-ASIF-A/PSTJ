import java.util.*;

class Solution {
    private int[] count;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        int[] indices = new int[n];
        
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        mergeSort(nums, indices, 0, n - 1);

        List<Integer> result = new ArrayList<>();
        for (int c : count) {
            result.add(c);
        }
        return result;
    }

    private void mergeSort(int[] nums, int[] indices, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(nums, indices, left, mid);
        mergeSort(nums, indices, mid + 1, right);

        merge(nums, indices, left, mid, right);
    }

    private void merge(int[] nums, int[] indices, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        int rightCount = 0;

        while (i <= mid && j <= right) {
            if (nums[indices[j]] < nums[indices[i]]) {
                rightCount++;
                temp[k++] = indices[j++];
            } else {
                count[indices[i]] += rightCount;
                temp[k++] = indices[i++];
            }
        }

        while (i <= mid) {
            count[indices[i]] += rightCount;
            temp[k++] = indices[i++];
        }

        while (j <= right) {
            temp[k++] = indices[j++];
        }

        for (int p = 0; p < temp.length; p++) {
            indices[left + p] = temp[p];
        }
    }
}