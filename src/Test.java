import java.util.*;

public class Test {

    public static int minimumRecolors(String blocks, int k) {
        int l = 0, r = 0, cnt = 0;
        while(r < k){
            cnt += blocks.charAt(r) == 'W' ? 1 : 0;
            r++;
        }
        System.out.println(r);
        int res = cnt;
        while(r < blocks.length()){
            cnt += blocks.charAt(r) == 'W' ? 1 : 0;
            cnt -= blocks.charAt(l) == 'W' ? 1 : 0;
            res = Math.min(res, cnt);
            r++;
            l++;
        }

        return res;
    }

    public static int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int sumEnergy = 0;
        int hourNeededForEnergy = 0;
        for (int i = 0; i < energy.length; i++) {
            sumEnergy += energy[i];
        }
        if(sumEnergy < initialEnergy){
            hourNeededForEnergy = 0;
        }else{
            hourNeededForEnergy = sumEnergy - initialEnergy + 1;
        }

        int currentExperience = initialExperience;
        int hourNeededForExperience = 0;
        for (int i = 0; i < experience.length; i++) {
            if(currentExperience > experience[i]){
                currentExperience += experience[i];
                System.out.println(hourNeededForExperience);
                System.out.println(currentExperience);
            }else{
                hourNeededForExperience = hourNeededForExperience + experience[i] - currentExperience + 1;
                currentExperience = 2 * experience[i] + 1;
                System.out.println(hourNeededForExperience);
                System.out.println(currentExperience);
            }
        }
//        System.out.println(hourNeededForEnergy);
//        System.out.println(hourNeededForExperience);
        return hourNeededForEnergy + hourNeededForExperience;
    }

    public static int minMoves(int[] nums) {
        int minValue = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] < minValue){
                minValue = nums[i];
            }
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > minValue){
                count += nums[i] - minValue;
            }
        }
        return count;
    }

    public static int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;
        int[][] mat = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = Math.min(rowSum[i], colSum[j]);
                rowSum[i] -= mat[i][j];
                colSum[j] -= mat[i][j];
            }
        }
        return mat;
    }


    public int maximalNetworkRank(int n, int[][] roads) {
        boolean[][] connect = new boolean[n][n];
        int[] degree = new int[n];
        for(int[] road : roads){
            degree[road[0]]++;
            degree[road[1]]++;
            connect[road[0]][road[1]] = true;
            connect[road[1]][road[0]] = true;
        }
        int maxRank = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <n ; j++) {
                int rank = degree[i] + degree[j] - (connect[i][j] ? 1 : 0);
                maxRank = Math.max(maxRank,rank);
            }
        }
        return maxRank;
    }

    public static boolean checkPossibility(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            int x = nums[i];
            int y = nums[i+1];
            if(x > y){
                nums[i] = y;
                if(isSorted(nums)){
                    return true;
                }
                nums[i] = x; //复原num[i]原本的值
                nums[i+1] = x;
                if(isSorted(nums)){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    //判断一个数组是否为非递减数组
    public static boolean isSorted(int[] targetNums){
        for (int i = 1; i < targetNums.length; i++) {
            if(targetNums[i] < targetNums[i-1]){
                return false;
            }
        }
        return true;
    }

    public static int[] answerQueries(int[] nums, int[] queries) {
        //因为只需要输出子序列的长度，不要求顺序，先对nums进行排序
        Arrays.sort(nums);
        //用一个数组存放排序后nums数组的前缀和
        int[] preSum = preSum(nums);
        int[] res = new int[queries.length];
        //遍历queries数组
        for (int i = 0; i < queries.length; i++) {
            for (int j = 0; j < preSum.length-1; j++) {
                if(queries[i] >= preSum[j] && queries[i] < preSum[j+1]){
                    res[i] = j;
                }
            }
            if(queries[i] >= preSum[preSum.length-1]){
                res[i] = preSum.length-1;
            }
        }
        return res;
    }

    //计算一个数组的前缀和，作为一个数组返回
    public static int[] preSum(int[] nums){
        int[] preSum = new int[nums.length+1];
        preSum[0] = 0;
        for (int i = 1; i < preSum.length; i++) {
            int singlePreSum = 0;
            for (int j = 0; j < i; j++) {
                singlePreSum += nums[j];
            }
            preSum[i] = singlePreSum;
        }
        return preSum;
    }


    public static void moveZeroes(int[] nums) {
        int index = 0;
        for(int i : nums){
            if(i != 0){
                nums[index] = i;
                index++;
            }
        }
        //填充
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }

    }

    //189. 轮转数组
    public static void rotate(int[] nums, int k) {
        if(nums.length == 1){
            return;
        }
        int n = nums.length;
        int m = k %= n;
        rotateArr(nums, 0, n-1);
        rotateArr(nums, 0, m-1);
        rotateArr(nums, m, n-1);
    }
    //翻转对于明确左右坐标下的数组
    public static void rotateArr(int[] nums, int left, int right){
        while(left <= right){
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
            left++;
            right--;
        }
    }

    public static int maxRotateFunction(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            //翻转数组，如果是0就没有，往后每次翻转一次
            if(i == 0){
                rotate(nums, 0);
            }else{
                rotate(nums,1);
            }
            int sum = 0;
            //计算和
            for (int j = 0; j < nums.length; j++) {
                sum += j * nums[j];
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    public static double[] convertTemperature(double celsius) {
        double[] res = new double[2];
        double d1 = 273.15;
        double d2 = 1.8;
        double d3 = 32.00;
        double kelvin = celsius + d1;
        double fahrenheit = celsius * d2 + d3;
        res[0] = kelvin;
        res[1] = fahrenheit;
        return res;
    }


    public boolean checkPalindromeFormation(String a, String b) {
        return checkConcatenation(a, b) || checkConcatenation(b, a);
    }

    public static boolean checkConcatenation(String a, String b) {
        int n = a.length();
        int left = 0, right = n - 1;
        while (left < right && a.charAt(left) == b.charAt(right)) {
            left++;
            right--;
        }
        if (left >= right) {
            return true;
        }
        return checkSelfPalindrome(a, left, right) || checkSelfPalindrome(b, left, right);
    }

    public static boolean checkSelfPalindrome(String a, int left, int right){
        while(left < right && a.charAt(left) == a.charAt(right)){
            left++;
            right--;
        }
        if(left >= right){
            return true;
        }else{
            return false;
        }
    }

    public static int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set2.add(i+1);
        }
        int count = 0;
        for(int i : nums){
            set1.add(i);
            count++;
            if(set1.size() != count){
                res[0] = i;
                break;
            }
        }
        for(int i : nums){
            set1.add(i);
        }
        for(int i : set2){
            if(!set1.contains(i)){
                res[1] = i;
            }
        }
        return res;
    }

    public static List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int[] arr = new int[r[i] - l[i] + 1]; //创建一个新数组，把nums对应的数字放进去
            int index = l[i];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = nums[index];
                index++;
            }
            Arrays.sort(arr);
            list.add(isArithmetic(arr));
        }
        return list;
    }

    //判断一个数组是否为等差数列
    public static boolean isArithmetic(int[] nums){
        boolean b = false;
        int value = nums[1] - nums[0];
        for (int i = 0; i < nums.length-1; i++) {
            int j = nums[i+1] - nums[i];
            if(j == value){
                b = true;
            }else{
                b = false;
                break;
            }
        }
        return b;
    }

    public static int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            }else{
                map.put(nums[i], new int[]{1,i,i});
            }
        }
        //遍历HashMap
        int maxFreq = 0;
        int minLength = Integer.MAX_VALUE;
        for(Map.Entry<Integer, int[]> entry : map.entrySet()){
            int[] arr = entry.getValue();
            maxFreq = Math.max(maxFreq, arr[0]);
        }
        for(Map.Entry<Integer, int[]> entry : map.entrySet()){
            int[] arr = entry.getValue();
            if(arr[0] == maxFreq){
                minLength = Math.min(minLength, arr[2] - arr[1] + 1);
            }
        }
        return minLength;
    }

    public static int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != i){
                return i;
            }
        }
        return nums.length;
    }

    public static boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        String str = String.valueOf(x);
        int left = 0;
        int right = str.length()-1;
        while(left <= right){
            if(str.charAt(left) != str.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProf = 0;
        for (int i = 0; i < prices.length; i++) {
            if(minPrice > prices[i]){
                minPrice = prices[i];
            }else if(prices[i] - minPrice > maxProf){
                maxProf = prices[i] - minPrice;
            }
        }
        return maxProf;
    }





    public static void main(String[] args) {






    }
}
