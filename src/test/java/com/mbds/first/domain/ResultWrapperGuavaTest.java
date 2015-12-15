package com.mbds.first.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by marwen on 15/12/15.
 */
public class ResultWrapperGuavaTest {

    @Test
    public void testEquals() {
        ResultWrapperGuava resultWrapperGuava = new ResultWrapperGuava("Foo Bar");
        ResultWrapperGuava resultWrapperGuavaSecond = new ResultWrapperGuava("Foo Bar");
        Assert.assertTrue(resultWrapperGuava.equals(resultWrapperGuavaSecond) && resultWrapperGuavaSecond.equals(resultWrapperGuava));
    }

    @Test
    public void testHashCode() throws Exception {
        ResultWrapperGuava resultWrapperGuava = new ResultWrapperGuava("Foo Bar");
        ResultWrapperGuava resultWrapperGuavaSecond = new ResultWrapperGuava("Foo Bar");
        Assert.assertTrue(resultWrapperGuava.hashCode() == resultWrapperGuavaSecond.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        String resultGuava = "trueResult";
        ResultWrapperGuava resultWrapperGuava = new ResultWrapperGuava(resultGuava);
        String excepted = "ResultWrapperGuava{result=[trueResult]}";
        Assert.assertEquals(excepted,resultWrapperGuava.toString());
    }
}