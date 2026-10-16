import java.util.HashMap;
import java.util.Map;

class Solution {
public boolean checkSubarraySum(int[] nums, int k) {
Map<Integer, Integer> map = new HashMap<>();
map.put(0, -1);


    long prefixSum = 0;
    long divisor = Math.abs((long) k);

    for (int i = 0; i < nums.length; i++) {
        prefixSum += nums[i];

        long remainder = (k == 0)
                ? prefixSum
                : prefixSum % divisor;

        int rem = (int) remainder;

        if (map.containsKey(rem)) {
            if (i - map.get(rem) >= 2) {
                return true;
            }
        } else {
            map.put(rem, i);
        }
    }

    return false;
}


}
