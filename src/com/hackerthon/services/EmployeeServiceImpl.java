package com.hackerthon.services;

import com.hackerthon.common.CommonConstants;
import com.hackerthon.common.XmlTransformer;

import com.hackerthon.common.ConfigurationLoader;
import com.hackerthon.common.QueryLoader;
import com.hackerthon.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;


public class EmployeeServiceImpl extends EmployeeService {

    private final ArrayList<Employee> employees = new ArrayList<>();
    private static Connection connection;
    private static Statement statement;
    private PreparedStatement preparedStatement;

    public EmployeeServiceImpl() {
        connection = DBConnectUtil.getInstance().getConnection();
    }

    @Override
       public void loadEmployeesFromXml() {
        try {
            for (Map<String, String> data : XmlTransformer.extractXmlData()) {
                Employee employee = new Employee();
                employee.setEmployeeId(data.get(CommonConstants.XPATH_EMPLOYEE_ID_KEY));
                employee.setFullName(data.get(CommonConstants.XPATH_EMPLOYEE_NAME_KEY));
                employee.setAddress(data.get(CommonConstants.XPATH_EMPLOYEE_ADDRESS_KEY));
                employee.setFacultyName(data.get(CommonConstants.XPATH_FACULTY_NAME_KEY));
                employee.setDepartment(data.get(CommonConstants.XPATH_DEPARTMENT_KEY));
                employee.setDesignation(data.get(CommonConstants.XPATH_DESIGNATION_KEY));
                employees.add(employee);
                System.out.println(employee.toString() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createEmployeeTable() {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(QueryLoader.getQueryById("q2"));
            statement.executeUpdate(QueryLoader.getQueryById("q1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveEmployees() {
        try {
            preparedStatement = connection.prepareStatement(QueryLoader.getQueryById("q3"));
            connection.setAutoCommit(false);
            for (Employee employee : employees) {
                preparedStatement.setString(1, employee.getEmployeeId());
                preparedStatement.setString(2, employee.getFullName());
                preparedStatement.setString(3, employee.getAddress());
                preparedStatement.setString(4, employee.getFacultyName());
                preparedStatement.setString(5, employee.getDepartment());
                preparedStatement.setString(6, employee.getDesignation());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getEmployeeById(String employeeId) {
        Employee employee = new Employee();
        try {
            preparedStatement = connection.prepareStatement(QueryLoader.getQueryById("q4"));
            preparedStatement.setString(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee.setEmployeeId(resultSet.getString(1));
                employee.setFullName(resultSet.getString(2));
                employee.setAddress(resultSet.getString(3));
                employee.setFacultyName(resultSet.getString(4));
                employee.setDepartment(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
            }
            ArrayList<Employee> employeeList = new ArrayList<>();
            employeeList.add(employee);
            displayEmployees(employeeList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(String employeeId) {
        try {
            preparedStatement = connection.prepareStatement(QueryLoader.getQueryById("q6"));
            preparedStatement.setString(1, employeeId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayAllEmployees() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(QueryLoader.getQueryById("q5"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getString(1));
                employee.setFullName(resultSet.getString(2));
                employee.setAddress(resultSet.getString(3));
                employee.setFacultyName(resultSet.getString(4));
                employee.setDepartment(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
                employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        displayEmployees(employeeList);
    }

    @Override
    public void displayEmployees(ArrayList<Employee> employeeList) {
        System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
                + "Department" + "\t\t" + "Designation" + "\n");
        System.out.println(
                "================================================================================================================");
        for (Employee employee : employeeList) {
            System.out.println(employee.getEmployeeId() + "\t" + employee.getFullName() + "\t\t" + employee.getAddress()
                    + "\t" + employee.getFacultyName() + "\t" + employee.getDepartment() + "\t"
                    + employee.getDesignation() + "\n");
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------");
        }
    }
}