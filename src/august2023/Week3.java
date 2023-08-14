package august2023;

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


}
