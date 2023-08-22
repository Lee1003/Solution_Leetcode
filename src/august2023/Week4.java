package august2023;

public class Week4 {

    //2337. 移动片段得到字符串
    public static boolean canChange(String start, String target) {
        int n = start.length();
        int i = 0, j = 0;
        while (true) {
            //指针i，j前进到有字符的位置
            while (i < n && start.charAt(i) == '_') {
                ++i;
            }
            while (j < n && target.charAt(j) == '_') {
                ++j;
            }
            //i，j遍历完成，说明可以移动成target
            if (i == n && j == n) {
                return true;
            }
            //如果当前i，j对应的字符不是一样的（一个L，一个R）
            if (i == n || j == n || start.charAt(i) != target.charAt(j)) {
                return false;
            }
            //当前字符是L的时候，i必须大于j，才能把该L移动到target的位置
            //当前字符是R的时候，i必须小于j，才能把该R移动到target的位置
            if (start.charAt(i) == 'L' && i < j || start.charAt(i) == 'R' && i > j) {
                return false;
            }
            ++i;
            ++j;
        }
    }

    //849. 到最近的人的最大距离
    public static int maxDistToClosest(int[] seats) {
        int index = -1;
        int distance = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1 && index == -1) { //第一次遇到1，与第一个座位相减
                distance = Math.max(distance, i);
                index = i;
            }
            if (seats[i] == 1 && index >= 0) { //之后遇到1
                distance = Math.max(distance, (i - index) / 2);
                index = i;
            }
            if (i == seats.length - 1) {  //遍历结束
                distance = Math.max(distance, i - index);
            }
        }

        return distance;
    }


    public static void main(String[] args) {
        int[] seats = {1,0,0,0,1,0,1};
        System.out.println(maxDistToClosest(seats));
    }

}
