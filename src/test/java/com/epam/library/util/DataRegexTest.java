package com.epam.library.util;

import com.epam.library.util.validate.DataMatcher;
import com.epam.library.util.validate.DataRegex;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;

/**
 * To check our regex syntax
 */
public class DataRegexTest {

    @Test
    public void e_mailRegex(){
        String email = "serenitydiver@hotmail.com";
        Matcher matcher = DataMatcher.matches(DataRegex.E_MAIL, email);

        Assert.assertTrue(matcher.matches());

    }
    @Test
    public void wordRegex(){
        String word = "Ahmed";
        Matcher matcher = DataMatcher.matches(DataRegex.WORD, word);
        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void numberRegex(){
        String number = "4400";
        Matcher matcher = DataMatcher.matches(DataRegex.NUMBER, number);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void passwordRegex(){
        String password = "4400@jCkmf";
        Matcher matcher = DataMatcher.matches(DataRegex.PASSWORD, password);

        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void wordAnyLanguageRegex(){
        String word = "سامى";
        Matcher matcher = DataMatcher.matches(DataRegex.WORDS_ANY_LANGUAGE, word);

        Assert.assertTrue(matcher.matches());
    }
}
