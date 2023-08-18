package august2023;

import java.util.Arrays;

public class Week3 {

    //162. 寻找峰值
    public static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 2;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;

    }

    //153. 寻找旋转排序数组中的最小值
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 2;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[nums.length - 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return nums[left];
    }


    //2682. 找出转圈游戏输家
    public static int[] circularGameLosers(int n, int k) {
        int[] count = new int[n];
        count[0] = 1;
        int index = 0;
        for (int i = 1; ;i++) {
            count[(index + i * k) % n]++;
            if (count[(index + i * k) % n] == 2) {
                break;
            }
            index = (index + i * k) % n;
        }

        int loserCount = 0;
        for (int i : count) {
            if (i == 0) {
                loserCount++;
            }
        }

        int[] ans = new int[loserCount];
        int index1 = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] == 0) {
                ans[index1] = i + 1;
                index1++;
            }
        }

        return ans;
    }

    //833. 字符串中的查找与替换
    public static String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length();
        String[] replaceStr = new String[n];
        int[] replaceLen = new int[n];
        Arrays.fill(replaceLen, 1);

        for (int i = 0; i < indices.length; i++) {
            int idx = indices[i];
            if (s.startsWith(sources[i], idx)) {
                replaceStr[idx] = targets[i];
                replaceLen[idx] = sources[i].length();
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i += replaceLen[i]) {
            if (replaceStr[i] == null) {
                ans.append(s.charAt(i));
            } else {
                ans.append(replaceStr[i]);
            }
        }

        return ans.toString();
    }




}
