package com.javamsdt.library.util;


import com.javamsdt.library.entity.enumeration.ReadingPlace;
import com.javamsdt.library.entity.enumeration.Role;
import com.javamsdt.library.util.validate.ArgumentValidator;

public class EnumService {

    /**
     * @param values from enum class as array
     * @param string that we need to compare with the values of the enum
     * @return the enum value if it is equal to the string or null if it is not in the list.
     */
    public static String getString(Enum[] values, String string) {
        ArgumentValidator.checkForNull(values, "Not allow for null enum values in EnumService");
        ArgumentValidator.checkForNullOrEmptyString(string, "Not allow for null or empty string in EnumService");

        String findingValue = null;
        for (Enum value : values) {
            if (string.toUpperCase().equalsIgnoreCase(value.name())) {
                findingValue = value.name();
            }
        }
        return findingValue;
    }

    /**
     * @param role from the database at this project
     * @return enum element for the role to setRole() in User class
     */
    public static Role getRole(String role) {
        ArgumentValidator.checkForNullOrEmptyString(role, "Not allow for null or empty string in EnumService");

        Role findingValue = null;
        for (Role value : Role.values()) {
            if (role.trim().equalsIgnoreCase(value.name())) {
                findingValue = value;
            }
        }
        return findingValue;
    }

    /**
     * @param readingPlace from the database at this project
     * @return enum element for the role to setReadingPlace() in Order class
     */
    public static ReadingPlace getReadingPlace(String readingPlace) {
        ArgumentValidator.checkForNullOrEmptyString(readingPlace, "Not allow for null or empty string in EnumService");
        ReadingPlace findingValue = null;
        for (ReadingPlace value : ReadingPlace.values()) {
            if (readingPlace.trim().equalsIgnoreCase(value.name())) {
                findingValue = value;
            }
        }
        return findingValue;
    }
}
