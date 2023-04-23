package march2023;

import java.util.*;

public class Week5 {

    //2023.03.27
    public static int countSubstrings(String s, String t) {
        int res = 0;
        int n = s.length();
        int m = t.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int diff = 0;
                for (int k = 0; i + k < n && j + k < m; k++) {
                    diff += s.charAt(i + k) == t.charAt(j + k) ? 0 : 1;
                    if (diff > 1) {
                        break;
                    } else if (diff == 1) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    public static boolean findSubarrays(int[] nums) {
//        int l = nums.length;
//        for (int i = 0; i < l-1; i++) {
//            for (int j = i + 1; j < l-1; j++) {
//                int ans1 = nums[i] + nums[i+1];
//                int ans2 = nums[j] + nums[j+1];
//                if(ans1 == ans2){
//                    return true;
//                }
//            }
//        }
//        return false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            int sum = nums[i] + nums[i + 1];
            if (set.contains(sum)) {
                return true;
            } else {
                set.add(sum);
            }
        }
        return false;
    }

    public static List<Integer> findDuplicates(int[] nums) {
//        List<Integer> list = new ArrayList<>();
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
//        }
//        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
//            int num = entry.getKey();
//            int count = entry.getValue();
//            if(count == 2){
//                list.add(num);
//            }
//        }
//        return list;
        List<Integer> list = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
                for (int k : nums) {
                    System.out.print(k + " ");
                }
                System.out.println();
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] - 1 != i) {
                list.add(nums[i]);
            }
        }
        return list;
    }

    public static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {

        }
        return list;
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int maxLen = 0;
        int currLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                currLen++;
                maxLen = Math.max(maxLen, currLen);
            } else {
                currLen = 0;
            }
        }
        maxLen = Math.max(maxLen, currLen);
        return maxLen;
    }

    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0;
        int expired = 0;
        for (int i = 0; i < timeSeries.length; i++) {
            if (timeSeries[i] >= expired) {
                ans += duration;
            } else {
                ans += timeSeries[i] + duration - expired;
            }
            expired = timeSeries[i] + duration;
        }
        return ans;
    }

    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            if (!list.contains(i)) {
                list.add(i);
            }
        }
        if (list.size() < 3) {
            return list.get(1);
        } else {
            return list.get(list.size() - 3);
        }
    }

    public static boolean isPalindrome(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                stringBuffer.append(Character.toLowerCase(c));
            }
        }
        int left = 0;
        int right = stringBuffer.length() - 1;
        while (left < right) {
            if (stringBuffer.charAt(left) != stringBuffer.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean detectCapitalUse(String word) {
        char[] cs = word.toCharArray();
        if (cs.length == 1) { //长度为1，只有1个字母，不管大小写都是true
            return true;
        }
        if (cs.length == 2) { //长度为2，只有前小后大是false，其他都是true
            //只有前小后大是false，其他都是true
            return !Character.isLowerCase(cs[0]) || !Character.isUpperCase(cs[1]);
        }
        //长度大于2
        if (Character.isUpperCase(cs[0])) {  //第一个字母是大写，分两种情况
            if (Character.isUpperCase(cs[1])) { //第二个字母也是大写，后面的必须全是大写才是true
                for (int i = 2; i < cs.length; i++) {
                    if (Character.isLowerCase(cs[i])) {
                        return false;
                    }
                }
            } else { //第二个字母是小写，后面的字母必须全是小写才是true
                for (int i = 2; i < cs.length; i++) {
                    if (Character.isUpperCase(cs[i])) {
                        return false;
                    }
                }
            }
        } else { //第一个字母是小写，后面的必须全是小写才是true，出现一个大写就是false
            for (char c : cs) {
                if (Character.isUpperCase(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }

    //2023.03.28
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                return true;
            } else {
                set.add(i);
            }
        }
        return false;
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            set2.add(i);
        }
        List<Integer> list = new ArrayList<>();
        for (int i : set1) {
            if (set2.contains(i)) {
                list.add(i);
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        char[] cs1 = s.toCharArray();
        char[] cs2 = t.toCharArray();
        for (char c : cs1) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for (char c : cs2) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        if (map1.size() != map2.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : map2.entrySet()) {
            char c = entry.getKey();
            int i = entry.getValue();
            if (!map1.containsKey(c)) { //map2中没有key，直接false
                return false;
            } else { //map2中有key，分两种情况，value值不同-false value值相同-true
                if (i != map1.get(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int sumArr(int[] nums, int left, int right) {
        if (nums.length == 1) {
            return nums[0];
        }
        int sum = 0;
        for (int i = left; i < right + 1; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left <= right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    //345.反转字符串中的元音字母
    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        char[] cs = s.toCharArray();
        int left = 0;
        int right = cs.length - 1;
        while (left <= right) {
            if (set.contains(cs[left]) && set.contains(cs[right])) { //两边都符合，交换，左右指针更新
                char temp = cs[right];
                cs[right] = cs[left];
                cs[left] = temp;
                left++;
                right--;
            } else if (!set.contains(cs[left]) && set.contains(cs[right])) { //只有右边符合，左指针更新
                left++;
            } else if (set.contains(cs[left]) && !set.contains(cs[right])) { //只有左边符合，右指针更新
                right--;
            } else { //左右都不符合，左右都更新
                left++;
                right--;
            }
        }
        return new String(cs);
    }

    //2023.03.29
    //11.盛水最多的容器
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        int maxRes = 0;
        while (left < right) {
            res = Math.min(height[left], height[right]) * (right - left);
            maxRes = Math.max(maxRes, res);
            if (height[left] >= height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxRes;
    }

    //15.三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        //枚举a
        for (int first = 0; first < n; first++) {
            //需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            //c对应的指针出事指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            //枚举b
            for (int second = first + 1; second < n; second++) {
                //需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                //需要保证b的指针在c的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    third--;
                }
                //如果指针重合，随后b后续的增加，a+b+c一定大于0，可以退出循环
                if (second == third) {
                    break;
                }
                //判断三数相加是否等于0
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    //1641. 统计字典序元音字符串的数目 (没看懂题解）
    public static int countVowelStrings(int n) {
        int[] dp = new int[5];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < 5; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return Arrays.stream(dp).sum();
    }

    //118. 杨辉三角
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> outcome = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> perRow = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if(j == 0 || j == i){
                    perRow.add(1);
                }else{
                    int sum = outcome.get(i - 1).get(j - 1) + outcome.get(i - 1).get(j);
                    perRow.add(sum);
                }
            }
            outcome.add(perRow);
        }
        return outcome;
    }

    //119. 杨辉三角 II
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < rowIndex + 1; i++) {
            List<Integer> perRow = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if(j == 0 || j == i){
                    perRow.add(1);
                }else{
                    int sum = triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j);
                    perRow.add(sum);
                }
            }
            triangle.add(perRow);
        }
        return triangle.get(rowIndex);
    }

    //661. 图片平滑器
    public static int[][] imageSmoother(int[][] img) {
        int m = img.length;
        int n = img[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = 0;
                int sum = 0;
                for(int x = i - 1; x <= i + 1; x++){
                    for(int y = j - 1; y <= j + 1; y++){
                        if(x >= 0 && x < m && y >= 0 && y < n){
                            num++;
                            sum += img[x][y];
                        }
                    }
                }
                ans[i][j] = sum / num;
            }
        }
        return ans;
    }

    //598. 范围求和 II
    public static int maxCount(int m, int n, int[][] ops) {
        for (int[] op : ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }

    //2023.03.30
    //1637. 两点之间不包含任何点的最宽垂直区域
    public static int maxWidthOfVerticalArea(int[][] points) {
        int[] arr = new int[points.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = points[i][0];
        }
        Arrays.sort(arr);
        int maxWidth = 0;
        for (int i = 1; i < arr.length; i++) {
            maxWidth = Math.max(maxWidth, arr[i] - arr[i - 1]);
        }
        return maxWidth;
    }

    //419. 甲板上的战舰
    public int countBattleships(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int ans = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j] == 'X'){
                    board[i][j] = '.';
                    for(int k = j + 1; k < col && board[i][k] == 'X'; k++){
                        board[i][k] = '.';
                    }
                    for(int k = i + 1; k < row && board[k][j] == 'X'; k++){
                        board[k][j] = '.';
                    }
                    ans++;
                }
            }
        }
        return ans;
    }

    //54. 螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
//        List<Integer> order = new ArrayList<>();
//        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
//            return order;
//        }
//        int rows = matrix.length;
//        int columns = matrix[0].length;
//        boolean[][] visited = new boolean[rows][columns]; //表示是否已经走过这个位置
//        int total = rows * columns;
//        int row = 0;
//        int column = 0;
//        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //表示前进方向，向右，向下，向左，向上
//        int directionIndex = 0;
//        for (int i = 0; i < total; i++) {
//            order.add(matrix[row][column]);
//            visited[row][column] = true;
//            //nextRow, nextColumn用于计算下一个格子的位置
//            int nextRow = row + directions[directionIndex][0];
//            int nextColumn = column + directions[directionIndex][1];
//            //如果下一个格子超过边界，或者已经被访问过了，则更新directionIndex，directionIndex在0,1,2,3,中来回变换，对应directions[][]表示方向
//            if(nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >=columns || visited[nextRow][nextColumn]){
//                directionIndex = (directionIndex + 1) % 4;
//            }
//            //用当前的row，column加上二维数组directions，即可更新到下一个位置
//            row += directions[directionIndex][0];
//            column += directions[directionIndex][1];
//        }
//        return order;
        List<Integer> order = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return order;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        //坐标
        int left = 0;
        int right = columns - 1;
        int top = 0;
        int bottom = rows - 1;
        while(left <= right && top <= bottom){
            for(int column = left; column <= right; column++){
                order.add(matrix[top][column]);
            }
            for(int row = top + 1; row <= bottom; row++){
                order.add(matrix[row][right]);
            }
            if(left < right && top < bottom){
                for(int column = right - 1; column >= left + 1; column--){
                    order.add(matrix[bottom][column]);
                }
                for(int row = bottom; row >= top + 1; row--){
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

    //59. 螺旋矩阵 II
    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int num = 1;
        while(left <= right && top <= bottom){
            for(int column = left; column <= right; column++){
                matrix[top][column] = num;
                num++;
            }
            for(int row = top + 1; row <= bottom; row++){
                matrix[row][right] = num;
                num++;
            }
            if(left < right && top < bottom){
                for(int column = right - 1; column > left; column--){
                    matrix[bottom][column] = num;
                    num++;
                }
                for(int row = bottom; row > top; row--){
                    matrix[row][left] = num;
                    num++;
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return matrix;
    }

    //498. 对角线遍历
    public static int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] ans = new int[m * n];
        int pos = 0;
        for (int i = 0; i < m + n - 1; i++) {
            if(i % 2 == 0){ //i是偶数，从下往上遍历
                int x = i < m ? i : m - 1;
                int y = i < m ? 0 : i - m + 1;
                while(x >= 0 && y < n){
                    ans[pos] = mat[x][y];
                    pos++;
                    x--;
                    y++;
                }
            }else{ //i是基数，从上往下遍历
                int x = i < n ? 0 : i - n + 1;
                int y = i < n ? i : n - 1;
                while(x < m && y >= 0){
                    ans[pos] = mat[x][y];
                    pos++;
                    x++;
                    y--;
                }
            }
        }
        return ans;
    }


    //2023.03.31
    //2367. 算术三元组的数目
    public static int arithmeticTriplets(int[] nums, int diff) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int k = n - 1;
            for (int j = i + 1; j < n; j++) {
                if(nums[j] - nums[i] == diff){
                    while(j < k && nums[k] - nums[j] > diff){
                        k--;
                    }
                    if(j == k){
                        break;
                    }
                    if(nums[k] - nums[j] == diff){
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    //566. 重塑矩阵
    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if(m * n != r * c){
            return mat;
        }
        int[][] ans = new int[r][c];
        int[] arr = new int[r * c];
        int pos = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[pos] = mat[i][j];
                pos++;
            }
        }
        pos = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ans[i][j] = arr[pos];
                pos++;
            }
        }
        return ans;
    }

    //48. 旋转图像
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        //上下翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
            }
        }
        //对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    //73. 矩阵置零
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == 0){
                    int[] arr = new int[2];
                    arr[0] = i;
                    arr[1] = j;
                    list.add(arr);
                }
            }
        }
        for (int[] ints : list) {
            int a = ints[0];
            int b = ints[1];
            for (int i = 0; i < m; i++) {
                matrix[i][b] = 0;
            }
            for (int i = 0; i < n; i++) {
                matrix[a][i] = 0;
            }
        }
    }

    //289. 生命游戏
    public static void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] check = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 0){ //如果是死细胞，计算周围八个格子内的活细胞数
                    int liveCellCount1 = 0;
                    for(int x = i - 1; x <= i + 1; x++){
                        for(int y = j - 1; y <= j + 1; y++){
                            if(x >= 0 && x < m && y >= 0 && y < n){ //如果周围的格子在原来的数组内
                                if(board[x][y] == 1){
                                    liveCellCount1++;
                                }
                            }
                        }
                    }
                    check[i][j] = liveCellCount1 == 3;
                }else{ //如果是活细胞，计算周围八个格子内的活细胞数
                    int liveCellCount2 = 0;
                    for(int x = i - 1; x <= i + 1; x++){
                        for(int y = j - 1; y <= j + 1; y++){
                            if(x >= 0 && x < m && y >= 0 && y < n){ //如果周围的格子在原来的数组内
                                if(board[x][y] == 1){
                                    liveCellCount2++;
                                }
                            }
                        }
                    }
                    liveCellCount2--; //最后计算的活细胞数要减一，因为中心的就是活细胞不算在内
                    check[i][j] = liveCellCount2 == 3 || liveCellCount2 == 2;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = check[i][j] ? 1 : 0;
            }
        }
    }

    //831. 隐藏个人信息
    public static String maskPII(String s) {
        StringBuilder stringBuffer = new StringBuilder();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if(cs[i] == '@'){ //是地址
                stringBuffer.append(Character.toLowerCase(cs[0]));
                stringBuffer.append("*****");
                stringBuffer.append(Character.toLowerCase(cs[i - 1]));
                stringBuffer.append(Character.toLowerCase(cs[i]));
                String str = s.substring(i + 1);
                stringBuffer.append(str.toLowerCase());
                return stringBuffer.toString();
            }
        }
        //是电话号码
        Set<Character> set = new HashSet<>();
        set.add('+');
        set.add('-');
        set.add('(');
        set.add(')');
        set.add(' ');
        for(char c : cs){
            if(!set.contains(c)){
                stringBuffer.append(c);
            }
        }
        String str = stringBuffer.toString();
        int len = str.length();
        if(len == 10){
            stringBuffer.setLength(0);
            stringBuffer.append("***-***-");
            stringBuffer.append(str.substring(len - 4));
        }else if(len == 11){
            stringBuffer.setLength(0);
            stringBuffer.append("+*-***-***-");
            stringBuffer.append(str.substring(len - 4));
        }else if(len == 12){
            stringBuffer.setLength(0);
            stringBuffer.append("+**-***-***-");
            stringBuffer.append(str.substring(len - 4));
        }else{
            stringBuffer.setLength(0);
            stringBuffer.append("+***-***-***-");
            stringBuffer.append(str.substring(len - 4));
        }
        return stringBuffer.toString();
    }

    //383. 赎金信
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        char[] cs1 = ransomNote.toCharArray();
        char[] cs2 = magazine.toCharArray();
        for(char c : cs1){
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for(char c : cs2){
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        for(Map.Entry<Character, Integer> entry : map1.entrySet()){
            char c = entry.getKey();
            int i = entry.getValue();
            if(!map2.containsKey(c)){
                return false;
            }else{
                if(map2.get(c) < i){
                    return false;
                }
            }

        }
        return true;
    }

    //128. 最长连续序列
    public static int longestConsecutive(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int maxLen = 0;
        Set<Integer> set = new HashSet<>();
        for(int i : nums){
            set.add(i);
        }
        //考虑每次增加
        for(int i : set){
            //如果不含有i-1，说明可以从这个i开始计算序列长度
            if(!set.contains(i - 1)){
                int currNum = i;
                int currLen = 1;

                while(set.contains(currNum + 1)){
                    currLen += 1;
                    currNum += 1;
                }
                maxLen = Math.max(maxLen, currLen);
            }
            //如果set中已经包含了i-1，我们可以跳过这个，因为从i-1开始的序列长度肯定比从i开始的长
        }
        return maxLen;
    }

    //290. 单词规律
    public boolean wordPattern(String pattern, String s) {
        String[] strArr = s.split(" ");
        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();

        if(pattern.length() != strArr.length){
            return false;
        }

        for (int i = 0; i < strArr.length; i++) {
            char x = pattern.charAt(i);
            String y = strArr[i];
            if(map1.containsKey(x) && !map1.get(x).equals(y) || map2.containsKey(y) && !map2.get(y).equals(x)){
                return false;
            }
            map1.put(x, y);
            map2.put(y, x);
        }
        return true;
    }

    //532. 数组中的 k-diff 数对
    public static int findPairs(int[] nums, int k) {
        int count = 0;
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
            set.add(i);
            if(map.get(i) == 2){
                count++;
            }
        }

        if(k == 0){
            //此时count表示重复数字的次数
            return count;
        }

        count = 0;
        for(int num : set){
            if(set.contains(num + k)){
                count++;
            }
        }
        return count;
    }






    public static void main(String[] args) {

    }
}

