package Util;

public class Validator {

    public static final String POST_CODE_REGEX = "^\\d{2}-\\d{3}$";

    public static final String ADDRESS_REGEX = "^[a-zA-Z]{2,}([a-zA-Z]{0,2})?(/\\d{1,4}([a-zA-Z]{0,2})?){0,2}$";

    public static final String PHONE_NUMBER_REGEX = "^(\\d{3}( |-)){2}\\d{3}$";

    public static final String TEXT_REGEX = "^[[:alpha:]]+$";

    public static final String QUANTITY_NUMBER_REGEX = "^[1-9][0-9]?$";

}
