package com.example.myscriptdemo;

import com.example.myscriptdemo.utils.DateUtil;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void date_isCorrect(){
        assertEquals(DateUtil.getDate_yyyyMMdd(), "2019-07-19");
    }


}