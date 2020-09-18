package com.example.mybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

//@SpringBootTest
class MybatisPlusApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(getUUIDForStr());
    }

    /**
     * 获取一个随机不重复的UUID（字符串）
     *
     * @return
     */
    public static String getUUIDForStr() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }


}
