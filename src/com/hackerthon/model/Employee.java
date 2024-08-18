package com.hackerthon.model;

/**
 * Class representing an Employee.
 */
public class Employee {

    private String employeeId;
    private String fullName;
    private String address;
    private String facultyName;
    private String department;
    private String designation;

    // Getter and Setter for employeeId
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    // Getter and Setter for fullName
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Getter and Setter for address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter and Setter for facultyName
    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    // Getter and Setter for department
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Getter and Setter for designation
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Employee ID = " + employeeId + "\n" + "Full Name = " + fullName + "\n" + "Address = " + address + "\n"
                + "Faculty Name = " + facultyName + "\n" + "Department = " + department + "\n" + "Designation = "
                + designation;
    }
}