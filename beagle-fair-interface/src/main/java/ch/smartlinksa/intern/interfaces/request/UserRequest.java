package ch.smartlinksa.intern.interfaces.request;

import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.constant.PatternConstant;
import ch.smartlinksa.intern.interfaces.validate.constraint.BirthDayValidate;
import ch.smartlinksa.intern.interfaces.validate.constraint.ExistedUser;
import ch.smartlinksa.intern.interfaces.validate.constraint.Gender;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {

    @ExistedUser
    @Size(message = MessageCodeConstant.ERROR_SIZE, min = 6, max = 30)
    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Pattern(message = MessageCodeConstant.ERROR_PATTERN_USERNAME, regexp = PatternConstant.USER_NAME)
    private String userName;

    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Size(message = MessageCodeConstant.ERROR_SIZE, min = 6, max = 1000)
    @Pattern(message = MessageCodeConstant.ERROR_PATTERN_PASSWORD,regexp = PatternConstant.PASSWORD)
    private String password;

    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Size(message = MessageCodeConstant.ERROR_SIZE, min = 1, max = 100)
    @Pattern(message = MessageCodeConstant.ERROR_PATTERN_NAME, regexp = PatternConstant.NAME)
    private String firstName;

    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Size(message = MessageCodeConstant.ERROR_SIZE, min = 1, max = 100)
    @Pattern(message = MessageCodeConstant.ERROR_PATTERN_NAME, regexp = PatternConstant.NAME)
    private String lastName;

    @NotNull(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @NotEmpty(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
//    @BirthDayValidate
    @Pattern(message = MessageCodeConstant.ERROR_INVALID_FORMAT_BIRTHDAY, regexp = PatternConstant.BIRTHDAY)
    private String birthday;

    @NotNull(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Gender
    private String gender;

    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Size(message = MessageCodeConstant.ERROR_SIZE, min = 4, max = 25)
    @Pattern(message = MessageCodeConstant.ERROR_PATTERN_PHONE_NUMBER, regexp = PatternConstant.PHONE_NUMBER)
    private String phoneNumber;

    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Size(message = MessageCodeConstant.ERROR_SIZE, min = 1, max = 100)
    private String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
