package com.ledboot.main.d01;

/**
 * @author: Fred
 * @date: 2024/2/1 17:29
 * @description: (类的描述)
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // Sort
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) { //  Traverse
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip the same

            int left = i + 1, right = nums.length - 1;
            while (left < right) { // Use two pointers
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result; // Return the list of triplets
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4}; // Example input
        List<List<Integer>> triplets = solution.threeSum(nums);

        for (List<Integer> triplet : triplets) {
            System.out.println(triplet);
        }
    }


        public ArrayList<ArrayList<Integer>> threeSum0 (int[] num) {
            Arrays.sort(num);

            ArrayList<ArrayList<Integer>> res  = new ArrayList<>();
            for (int i = 0; i < num.length - 2 ; i++) {

                int  l = i + 1, r = num.length - 1;

                while (l < r) {

                    int sum =  num[i] + num[l] + num[r];

                    if (sum == 0) {
                        res.add((ArrayList<Integer>) Arrays.asList(num[i], num[l], num[r] ));

                        l++;
                        r--;

                    } else if (sum < 0) {
                        l++;
                    } else {
                        r--;
                    }





                }


            }



            return res;
        }

}
