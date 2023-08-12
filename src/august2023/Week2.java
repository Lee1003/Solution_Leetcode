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



    //1281. 整数的各位积和之差
    public static int subtractProductAndSum(int n) {
//        String str = String.valueOf(n);
//        int mul = 1;
//        int sum = 0;
//        for (char c : str.toCharArray()) {
//            mul *= c - '0';
//            sum += c - '0';
//        }
//        return mul - sum;

        int mul = 1;
        int sum = 0;
        while (n != 0) {
            int x = n % 10;
            mul *= x;
            sum += x;
            n /= 10;
        }
        return mul - sum;

    }


    //611. 有效三角形的个数
    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        int len = nums.length;
        for (int i = len - 1; i >= 2; i--) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    ans += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }


    //1289.下降路径最小和 II
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];
        //初始化
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        //第一行dp数值填充
        for (int i = 0; i < n; i++) {
            dp[0][i] = grid[0][i];
        }
        //dp二维数组赋值
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j == k) {
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + grid[i][j]); //当前dp[i][j]为前一行的每个元素所对应的最小值（k和j不能相等）加上当前的grid[i][j]值
                }
            }
        }
        //遍历dp数组最后一行取出最小值
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[n - 1][i]);
        }
        return res;
    }


    //11. 盛最多水的容器
    public static int maxArea(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        //双指针，木桶的宽度始终在减少，盛水的量取决于短的木头，比较两端木头的长度，哪一个短，就把对应的指针向内移动
        while (left < right) {
            res = Math.max(res, Math.min(height[left], height[right]) * (right - left));
            if (height[left] >= height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }


    //1572. 矩阵对角线元素的和
    public static int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || i + j == n - 1) {
                    sum += mat[i][j];
                }
            }
        }
        return sum;
    }


    //42. 接雨水
    public int trap(int[] height) {
        int n = height.length;
        int sum = 0;
        int[] preMax = new int[n];
        int[] sufMax = new int[n];
        preMax[0] = height[0];
        sufMax[n - 1] = height[n - 1];

        //计算前缀最大值
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], height[i]);
        }
        //计算后缀最大值
        for (int i = n - 2; i >= 0; i--) {
            sufMax[i] = Math.max(sufMax[i + 1], height[i]);
        }

        //对于当前height数组元素，对应位置的前缀最大值与后缀最大值的min，减去相应位置的height[i],即为该位置可以装雨水的量
        for (int i = 0; i < n; i++) {
            sum += Math.min(preMax[i], sufMax[i]) - height[i];
        }

        return sum;
    }


    //34. 在排序数组中查找元素的第一个和最后一个位置
    public static int[] searchRange(int[] nums, int target) {
        int start = lowerBound1(nums, target);
        if (start == nums.length || nums[start] != target) {
            return new int[]{-1, -1};
        }
        int end = lowerBound1(nums, target + 1) - 1;
        return new int[]{start, end};
    }

    public static int lowerBound1(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1; //闭区间
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) { //右指针左移
                right = mid - 1;
            } else { //左指针右移
                left = mid + 1;
            }
        }
        return left;
    }

    public static int lowerBound2(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n; //左闭右开区间
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) { //右指针左移
                right = mid - 1;
            } else { //左指针右移
                left = mid;
            }
        }
        return left;
    }

    public static int lowerBound3(int[] nums, int target) {
        int n = nums.length;
        int left = -1, right = n; //开区间
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) { //右指针左移
                right = mid;
            } else { //左指针右移
                left = mid;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{4, 2, 3, 4};
        System.out.println(triangleNumber(nums));
    }
}
