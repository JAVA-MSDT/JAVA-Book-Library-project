package com.javamsdt.library.util;

import org.junit.Assert;
import org.junit.Test;

import com.javamsdt.library.entity.enumeration.ReadingPlace;
import com.javamsdt.library.entity.enumeration.Role;
import com.javamsdt.library.util.EnumService;

public class EnumServiceTest {
    private final static String STRING_IN_ENUM = "admin";
    private final static String ROLE = "reader";
    private final static String READING_PLACE = "HOME";

    @Test
    public void getStringPass() {
        String actual = "ADMIN";
        String expected = EnumService.getString(Role.values(), STRING_IN_ENUM);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getRolePass() {
        Role actual = Role.READER;
        Role expected = EnumService.getRole(ROLE);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getReadingPlacePass() {
        ReadingPlace actual = ReadingPlace.HOME;
        ReadingPlace expected = EnumService.getReadingPlace(READING_PLACE);

        Assert.assertEquals(actual, expected);
    }
}
