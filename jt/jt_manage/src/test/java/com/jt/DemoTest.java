package com.jt;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

public class DemoTest {
    @Test
    public void testREGEX(){
        String regex = "^.+\\.(jpg|png|gif)$";
        String name = "a.exe.jpg";
        System.out.println(name.matches(regex));
    }
}
