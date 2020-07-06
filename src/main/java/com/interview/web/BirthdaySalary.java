package com.interview.web;

import com.interview.inteface.EmployeeSalaryStrategy;

public class BirthdaySalary extends Salary {

    public BirthdaySalary(EmployeeSalaryStrategy employeeSalaryStrategy) {
        super(employeeSalaryStrategy);
    }

    public double calcBirthdaySalary(AbstractEmployee abstractEmployee, double birthSalay) {
        return super.calcSalay(abstractEmployee) + birthSalay;
    }
}
