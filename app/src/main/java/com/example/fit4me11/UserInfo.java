package com.example.fit4me11;


public class UserInfo {
    public String displayName;
    public int birthDate;
    public int birthMonth;
    public int birthYear;
    public String gender;
    public String email;

    public UserInfo() {
    }

    public UserInfo(String DisplayName, int BirthDate, int BirthMonth, int BirthYear, String Gender, String Email) {
        this.displayName = DisplayName;
        this.birthDate = BirthDate;
        this.birthMonth = BirthMonth;
        this.birthYear = BirthYear;
        this.gender = Gender;
        this.email = Email;
    }

    public UserInfo(String jsonStr) {
        JsonObjectParser jsonObjectParser = new JsonObjectParser(jsonStr);
        this.email = jsonObjectParser.getString("email");
        this.displayName = jsonObjectParser.getString("displayName");
        this.birthDate = jsonObjectParser.getInt("birthDate");
        this.birthMonth = jsonObjectParser.getInt("birthMonth");
        this.birthYear = jsonObjectParser.getInt("birthYear");
        this.gender = jsonObjectParser.getString("gender");
        this.email = jsonObjectParser.getString("email");
    }

    @Override
    public String toString() {
        return "{" +
                "displayName=" + displayName +
                ", birthDate=" + birthDate +
                ", birthMonth=" + birthMonth +
                ", birthYear=" + birthYear +
                ", gender=" + gender +
                ", email=" + email +
                '}';
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getBirthday()
    {
        String tmp = birthDate+"/"+birthMonth+"/"+birthYear;
        return tmp;
    }
}