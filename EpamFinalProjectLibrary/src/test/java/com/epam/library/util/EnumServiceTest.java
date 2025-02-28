package com.epam.library.util;

import com.epam.library.entity.enumeration.ReadingPlace;
import com.epam.library.entity.enumeration.Role;
import org.junit.Assert;
import org.junit.Test;

public class EnumServiceTest {
    private final static String STRING_IN_ENUM = "admin";
    private final static String ROLE = "user";
    private final static String READING_PLACE = "home";

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
