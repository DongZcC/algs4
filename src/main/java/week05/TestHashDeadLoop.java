package week05;

import java.util.HashMap;

/**
 * 功能说明: 测试HashMap多线程死循环，结果在jdk1.8已经修复这个问题<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-14<br>
 */
public class TestHashDeadLoop {

    private static HashMap<Integer, String> map = new HashMap<Integer, String>(2, 0.75f);

    public static void main(String[] args) {
        map.put(5, "C");

        new Thread("Thread1") {
            public void run() {
                map.put(7, "B");
                System.out.println(map);
            }
        }.start();


        new Thread("Thread2") {
            public void run() {
                map.put(3, "A");
                System.out.println(map);
            }
        }.start();
        map.get(7);
    }
}
