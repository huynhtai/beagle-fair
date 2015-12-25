package ch.smartlinksa.intern.interfaces.request;

import ch.smartlinksa.intern.dao.constant.Gender;
import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.validate.constraint.BirthDayValidate;
import ch.smartlinksa.intern.interfaces.validate.constraint.GenderValidate;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

public class UserRequest implements Serializable{

    @NotNull(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    @NotNull(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @NotEmpty(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Past(message = MessageCodeConstant.ERROR_IVALID_BIRTHDAY)
//    @Future(message = MessageCodeConstant.ERROR_IVALID)
//    @BirthDayValidate
    private String birthday;

//    @GenderValidate
    private Gender gender;

    private String phoneNumber;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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
