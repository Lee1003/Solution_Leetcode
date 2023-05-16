package may2023;

public class week3 {

    //2446. 判断两个事件是否存在冲突
    public static boolean haveConflict(String[] event1, String[] event2) {
        int e1Start = hourToMin(event1[0]);
        int e1End = hourToMin(event1[1]);
        int e2Start = hourToMin(event2[0]);
        int e2End = hourToMin(event2[1]);
        if (e2Start > e1End || e1Start > e2End) {
            return false;
        } else {
            return true;
        }
    }

    public static int hourToMin(String str) {
        return Integer.parseInt(str.substring(0,2)) * 60 + Integer.parseInt(str.substring(3,5));
    }


    public static void main(String[] args) {
        String str = "02:00";
        System.out.println(Integer.parseInt(str.substring(0,2)));
        System.out.println(Integer.parseInt(str.substring(3,5)));
        System.out.println(hourToMin(str));
    }
}
