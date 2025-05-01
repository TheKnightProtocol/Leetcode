import java.util.*;

public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int offset = 10000;  // offset negative to non-negative
        int size = 2 * 10000 + 2; // total possible values range (-10^4 to 10^4)
        int[] tree = new int[size];
        List<Integer> result = new ArrayList<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i] + offset;
            result.add(query(tree, num - 1));
            update(tree, num);
        }

        Collections.reverse(result);
        return result;
    }

    private void update(int[] tree, int i) {
        while (i < tree.length) {
            tree[i] += 1;
            i += i & -i;
        }
    }

    private int query(int[] tree, int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & -i;
        }
        return sum;
    }
}
