/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xenum;

import com.opencsv.bean.CsvBindByName;

/**
 *
 * @author ITSUKA KOTORI
 */
public enum ErrorDetails  implements AbstractEnum{
    E000(0, "Unknow Error", "The error is not define, unknow error found."),
    E001(1, "Not admin user error", "Please login as admin before using this following page nor function before login, please login as a admin. Thank you"), 
    E002(2, "Login account and password not match error", "Please check back your account id or password nither one is not valid, please try to login agian. Thank you"), 
    E003(3, "Not Customer User Error", "You are not able to view this page cause you are not login as a customer, please login as a customer nor register as a customer. Thank you"), 
    E004(4, "Not driver User Error", "The following function is only support for a driver, please login as a driver or register as a driver before using the following function. Thank you"), 
    E005(5, "Servlet Eception error", "Java servlet exception error"),
    E006(6, "IO Exception error", "Java IO Exception error found"), 
    E007(7, "Update Record error", "Please try agian to update and ensure all the entered field is correct"), 
    E008(8, "Date format error", "The date format is not match to the format of local-datetime dd/mm/yy hh:mm AA"),
    E009(9, "Adding Record Error", "The record you added is not complete please input again and try agian, andensure all the required field is fully fill in"),
    E010(10, "Unable to back up", "Please try agian you are unable to back up yours data now. Please try agian later."), 
    E011(11, "Unable to restore the data in database", "You database record is not modified cause of many problem. Please try agian"), 
    E012(12, "Adding profile picture error", "The profile picture is not added to your account please try agian later."),
    E013(13, "User id not correct error", "The user id you enter is not found in our database and format is not correct, please enter the correct user id"),
    E014(14, "Email sending error", "The email had not sucessful to send to the reciver. please apply agian to get back your password"),
    E015(15, "Driver has no register car", "The selected driver has not yet register a car, please try agian for another driver thank you."),
    E016(16, "Date format error", "The date format you enter is not correct please try agian !! pleas echeck back the date format"),
    E017(17, "Your id is not found in database", "Please try agian later, please ensure that you had logined to your account before use this function"),
    E018(18, "The username is registered", "The username is currentlly using by other user"),
    E019(19, "The password not match", "Password not match error, please reenter agian"),
    E020(20, "The email is registed", "The email account hasbean registed by other user"),
    E021(21, "", ""),
    E022(22, "", ""),
    E023(23, "", "");
    
    /**
     * Error Saving ID
     */
    @CsvBindByName(column = "error_code")
    private final int code;
    
    /**
     * Error Name
     */
    private final String name;
    
    /**
     * Error Description
     */
    private final String decription;
    ErrorDetails(int code, String name, String des){
        this.code = code;
        this.name = name;
        this.decription = des;
    }
    public int getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public String getDecription() {
        return decription;
    }
    public static ErrorDetails getValue(String code){
        return getValue(Integer.valueOf(code));
    }
    public static ErrorDetails getValue(int code){
        return ErrorDetails.valueOf(String.format("E%03d", code));
    }
    public String getUrl() {
        return "code=" +code;
    }
    @Override
    public String getStringCode() {
        return this.getCode() + "";
    }
}