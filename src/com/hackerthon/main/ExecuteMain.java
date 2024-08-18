package com.hackerthon.main;

import com.hackerthon.common.XmlTransformer;

public class ExecuteMain {

    /**
     * @param args
     */
    public static void main(String[] args) {

        EmployeeServiceTemplate employeeService = new EmployeeServiceTemplate();
        try {
            XmlTransformer.requestTransform();
            employeeService.manageEmployees();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}