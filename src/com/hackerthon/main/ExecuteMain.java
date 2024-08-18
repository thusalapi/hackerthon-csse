package com.hackerthon.main;

import com.hackerthon.common.XSLTransformUtil;
import com.hackerthon.services.EmployeeService;
import com.hackerthon.services.EmployeeServiceImpl;

public class ExecuteMain {

    /**
     * @param args
     */
    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeServiceImpl();
        try {
            XSLTransformUtil.requestTransform();
            employeeService.manageEmployees();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}