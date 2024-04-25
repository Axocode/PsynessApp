package com.example.psynessapp.variables;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONObject;

@Entity(tableName = "InterUsers")
public class InterUsers {
    @PrimaryKey( autoGenerate = true)
    private Integer IUserNum;

    private String IUser;
    private int IAge;
    private String IEmail;
    private String IPassword;
    private String IImgNum;
    private String IRol;
    private Integer IUserSeguidores;
    private Integer IUserSeguidos;
    private String IUserDescription;
    private String IUserDate;
    private String IUserHour;
    @ColumnInfo(name = "IUserActive", defaultValue = "false")
    private boolean IUserActive;

    public InterUsers() {
    }

    public InterUsers(Integer IUserNum, String IUser, int IAge, String IEmail, String IPassword, String IImgNum, String IRol, Integer IUserSeguidores, Integer IUserSeguidos, String IUserDescription, String IUserDate, String IUserHour, boolean IUserActive) {
        this.IUserNum = IUserNum;
        this.IUser = IUser;
        this.IAge = IAge;
        this.IEmail = IEmail;
        this.IPassword = IPassword;
        this.IImgNum = IImgNum;
        this.IRol = IRol;
        this.IUserSeguidores = IUserSeguidores;
        this.IUserSeguidos = IUserSeguidos;
        this.IUserDescription = IUserDescription;
        this.IUserDate = IUserDate;
        this.IUserHour = IUserHour;
        this.IUserActive = IUserActive;
    }

    public Integer getIUserNum() {
        return IUserNum;
    }

    public void setIUserNum(Integer IUserNum) {
        this.IUserNum = IUserNum;
    }

    public String getIUser() {
        return IUser;
    }

    public void setIUser(String IUser) {
        this.IUser = IUser;
    }

    public int getIAge() {
        return IAge;
    }

    public void setIAge(int IAge) {
        this.IAge = IAge;
    }

    public String getIEmail() {
        return IEmail;
    }

    public void setIEmail(String IEmail) {
        this.IEmail = IEmail;
    }

    public String getIPassword() {
        return IPassword;
    }

    public void setIPassword(String IPassword) {
        this.IPassword = IPassword;
    }

    public String getIImgNum() {
        return IImgNum;
    }

    public void setIImgNum(String IImgNum) {
        this.IImgNum = IImgNum;
    }

    public String getIRol() {
        return IRol;
    }

    public void setIRol(String IRol) {
        this.IRol = IRol;
    }

    public Integer getIUserSeguidores() {
        return IUserSeguidores;
    }

    public void setIUserSeguidores(Integer IUserSeguidores) {
        this.IUserSeguidores = IUserSeguidores;
    }

    public Integer getIUserSeguidos() {
        return IUserSeguidos;
    }

    public void setIUserSeguidos(Integer IUserSeguidos) {
        this.IUserSeguidos = IUserSeguidos;
    }

    public String getIUserDescription() {
        return IUserDescription;
    }

    public void setIUserDescription(String IUserDescription) {
        this.IUserDescription = IUserDescription;
    }

    public String getIUserDate() {
        return IUserDate;
    }

    public void setIUserDate(String IUserDate) {
        this.IUserDate = IUserDate;
    }

    public String getIUserHour() {
        return IUserHour;
    }

    public void setIUserHour(String IUserHour) {
        this.IUserHour = IUserHour;
    }

    public boolean getIUserActive() {
        return IUserActive;
    }

    public void setIUserActive(boolean IUserActive) {
        this.IUserActive = IUserActive;
    }

    public void populateFromJson(JSONObject jsonObject) {
    }
}
