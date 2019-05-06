package com.epam.library.util.validate;

public class DataRegex {

    public final static String E_MAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

    public final static String LOGIN = "[a-zA-z0-9]+";

    public final static String WORDS_ANY_LANGUAGE = "[a-zA-zа-яА-Яأ-ى ]*"; // English, Russian, Arabic

    public final static String NUMBER = "[0-9]+";

    public final static String PASSWORD = "^([a-zA-Z0-9@*#]{4,10})$";

    public final static String NAME = "[a-zA-Z ]*";


    private DataRegex() {

    }
}
