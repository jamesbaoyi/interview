package com.interview.web;

import com.interview.inteface.EmployeeSalaryStrategy;


public class CommonEmployee extends AbstractEmployee implements EmployeeSalaryStrategy {

    public CommonEmployee() {

    }

    public AbstractEmployee abstractEmployee;

    public CommonEmployee(double baseSalay) {
        this.baseSalay = baseSalay;
    }

    @Override
    public double calcSalary(AbstractEmployee employee) {
        return employee.baseSalay;
    }

}
