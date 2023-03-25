package model;

public class Asset {

    private String employeeID;

    private String name;

    private String birthday;

    private String graduateSchool;

    private String jobTitle;

    private String department;

    private float salary;

    private String cardNumber;

    private String record;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Asset() {
    }

    public Asset(String employeeID, String name, String birthday,
                 String graduateSchool, String jobTitle, String department,
                 float salary, String cardNumber, String record) {
        this.employeeID = employeeID;
        this.name = name;
        this.birthday = birthday;
        this.graduateSchool = graduateSchool;
        this.jobTitle = jobTitle;
        this.department = department;
        this.salary = salary;
        this.cardNumber = cardNumber;
        this.record = record;
    }

    @Override
    public String toString() {
        return "Employee Information:<br/>" +
                "Employee ID:'" + employeeID + "<br/>" +
                "Name: " + name + "<br/>" +
                "Birthday:" + birthday + "<br/>" +
                "Graduation School: " + graduateSchool + "<br/>" +
                "Job Title: " + jobTitle + "<br/>" +
                "Department: " + department + ",<br/>" +
                "Salary: " + salary + "<br/>" +
                "Card Number (Salary): " + cardNumber + "<br/>" +
                "Record: " + record + "<br/>";
    }
}
