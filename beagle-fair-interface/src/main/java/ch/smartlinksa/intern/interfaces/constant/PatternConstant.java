package ch.smartlinksa.intern.interfaces.constant;

public class PatternConstant {

    private PatternConstant(){}

    public static final String USER_NAME = "^[a-zA-Z]+([._]?[a-zA-Z0-9]+)*$";
    public static final String PASSWORD = "^.*(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$";
    public static final String PHONE_NUMBER = "^\\+[0-9]+$";
    public static final String NAME = "^[a-zA-Z ]+$";
    public static final String BIRTHDAY = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    public static final String PRODUCT_CODE = "^[a-zA-Z0-9]{12}$";

}
