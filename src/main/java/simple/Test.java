package simple;

import java.util.HashMap;

public class Test {

    public static void main1(String[] args) {
        HashMap map = new HashMap(4);

        for(int i =0; i < 500; i++){
            map.put("key"+i, "value");
        }
    }

    public static void main(String[] args) {

    }
}
