import java.util.*;

public class Solution {
    class Pair {
        int val;
        int index;
        Pair(int v, int i) {
            val = v;
            index = i;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(nums[i], i);
        }

        Integer[] result = new Integer[n];
        Arrays.fill(result, 0);

        mergeSort(pairs, 0, n - 1, result);
        return Arrays.asList(result);
    }

    private void mergeSort(Pair[] pairs, int left, int right, Integer[] result) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(pairs, left, mid, result);
        mergeSort(pairs, mid + 1, right, result);
        merge(pairs, left, mid, right, result);
    }

    private void merge(Pair[] pairs, int left, int mid, int right, Integer[] result) {
        List<Pair> temp = new ArrayList<>();
        int i = left;
        int j = mid + 1;
        int countRightSmaller = 0;

        while (i <= mid && j <= right) {
            if (pairs[i].val > pairs[j].val) {
                countRightSmaller++;
                temp.add(pairs[j++]);
            } else {
                result[pairs[i].index] += countRightSmaller;
                temp.add(pairs[i++]);
            }
        }

        while (i <= mid) {
            result[pairs[i].index] += countRightSmaller;
            temp.add(pairs[i++]);
        }

        while (j <= right) {
            temp.add(pairs[j++]);
        }

        for (int k = left; k <= right; k++) {
            pairs[k] = temp.get(k - left);
        }
    }
}
