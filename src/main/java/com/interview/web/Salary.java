package com.interview.web;

import com.interview.inteface.EmployeeSalaryStrategy;

public class Salary {

    private EmployeeSalaryStrategy employeeSalaryStrategy;

    public Salary(EmployeeSalaryStrategy employeeSalaryStrategy) {
        this.employeeSalaryStrategy = employeeSalaryStrategy;
    }

    public double calcSalay(AbstractEmployee employee) {
        return this.employeeSalaryStrategy.calcSalary(employee);
    }
}
