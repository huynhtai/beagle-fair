package ch.smartlinksa.intern.interfaces.constant;

public class PatternConstant {

    private PatternConstant(){}

    public static final String USER_NAME = "^[a-zA-Z]+([._]?[a-zA-Z0-9]+)*$";
    public static final String PASSWORD = "^.*(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$";
    public static final String PHONE_NUMBER = "^\\+[0-9]+$";
    public static final String NAME = "^[a-zA-Z ]+$";
}
