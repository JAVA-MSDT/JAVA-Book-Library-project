package com.javamsdt.library.util;

import org.junit.Assert;
import org.junit.Test;

import com.javamsdt.library.util.MD5Encrypt;

public class MD5EncryptTest {

    private final static String TEXT = "test";

    @Test
    public void encryptPass() {
        String actual = MD5Encrypt.encrypt(TEXT);
        String expected = MD5Encrypt.encrypt(TEXT);
        Assert.assertEquals(actual, expected);
    }
}
