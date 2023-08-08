package august2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Week2 {

    public static void reverseString(char[] s) {
        int start = 0;
        int end = s.length - 1;
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }


    //1749. 任意子数组和的绝对值的最大值
    public int maxAbsoluteSum(int[] nums) {
        //dp[i]为以i结尾的子数组的最大值，那么dp[i] = max(dp[i-1]+nums[i],nums[i])
        //第一种情况是dp[i-1]都是正数，那么直接加上，第二种情况是dp[i-1]是负数，那么只需要单独取nums[i]
        //子数组和的绝对值的最大值就是最大子数组和最小子数组取反，取最大值
        int ans = 0, max = 0, min = 0;
        for (int i : nums) {
            max = Math.max(max, 0) + i;
            min = Math.min(min, 0) + i;
            ans = Math.max(ans, Math.max(max, -min));
        }
        return ans;
    }


    //53. 最大子数组和
    public int maxSubArray(int[] nums) {
//        int[] dp = new int[nums.length];
//        dp[0] = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
//        }
//
//        return Arrays.stream(dp).max().getAsInt();
        int max = 0;
        int ans = Integer.MIN_VALUE;
        for (int i : nums) {
            max = Math.max(max, 0) + i;
            ans = Math.max(ans, max);
        }
        return ans;
    }


    //167. 两数之和 II - 输入有序数组
    public int[] twoSum(int[] numbers, int target) {
        //双指针，由于数组有序，left+right>target 向左移动右指针，left+right<target，向右移动做指针
        int[] ans = new int[2];
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                ans[0] = left + 1;
                ans[1] = right + 1;
                return ans;
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        return null;
    }


    //15. 三数之和
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //优化
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }
            if (nums[i] + nums[len - 1] + nums[len - 2] < 0) {
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                if (nums[left] + nums[right] == -nums[i]) {
                    List<Integer> subAns = new ArrayList<>();
                    subAns.add(nums[i]);
                    subAns.add(nums[left]);
                    subAns.add(nums[right]);
                    ans.add(subAns);
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                } else if (nums[left] + nums[right] > -nums[i]) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }



    //16. 最接近的三数之和
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        int ans = 0;
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            //优化
            if (nums[i] + nums[i + 1] + nums[i + 2] > target) {
                if (nums[i] + nums[i + 1] + nums[i + 2] - target < minDiff) {
                    ans = nums[i] + nums[i + 1] + nums[i + 2];
                }
                break;
            }

            if (nums[i] + nums[len - 2] + nums[len - 1] < target) {
                if (target - nums[i] + nums[len - 2] + nums[len - 1] < minDiff) {
                    minDiff = target - nums[i] + nums[len - 2] + nums[len - 1];
                    ans = nums[i] + nums[len - 2] + nums[len - 1];
                }
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return sum;
                } else if (sum > target) {
                    if (sum - target < minDiff) {
                        minDiff = sum - target;
                        ans = sum;
                    }
                    right--;
                } else {
                    if (target - sum < minDiff) {
                        minDiff = target - sum;
                        ans = sum;
                    }
                    left++;
                }
            }
        }
        return ans;
    }

    //18. 四数之和
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;

        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < len - 2; j++) {
                System.out.println(i);
                System.out.println(j);
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                //int溢出，转换成long计算
                long l1 = (long)nums[i] + nums[j] + nums[j + 1] + nums[j + 2];
                if (l1 > target) {
                    break;
                }
                long l2 = (long)nums[i] + nums[j] + nums[len - 1] + nums[len - 2];
                if (l2 < target) {
                    continue;
                }
                int left = j + 1;
                int right = len - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        List<Integer> subAns = new ArrayList<>();
                        subAns.add(nums[i]);
                        subAns.add(nums[j]);
                        subAns.add(nums[left]);
                        subAns.add(nums[right]);
                        System.out.println(subAns);
                        ans.add(subAns);
                        left++;
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        right--;
                        while (left < right && nums[right] == nums[right + 1]){
                            right--;
                        }
                    } else if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{0,0,0,-1000000000,-1000000000,-1000000000,-1000000000}, -1000000000));
    }
}
