package com.interview.web;

import com.interview.inteface.EmployeeSalaryStrategy;


public class SalesEmployee extends AbstractEmployee implements EmployeeSalaryStrategy {

    public SalesEmployee() {
    }

    public SalesEmployee(double baseSalesSalay, double advanceLowPercent, double advanceMiddlePercent) {
        this.baseSalesSalay = baseSalesSalay;
        this.advanceLowPercent = advanceLowPercent;
        this.advanceMiddlePercent = advanceMiddlePercent;
    }

    @Override
    public double calcSalary(AbstractEmployee employee) {
        double result = 0;
        if (employee.baseSalesSalay <= 20000)
            result = 2000;
        else if (employee.baseSalesSalay > 20000 && employee.baseSalesSalay <= 30000) {
            result = 2000 + (employee.baseSalesSalay - 20000) * employee.advanceLowPercent;
        } else if (employee.baseSalesSalay > 30000) {
            result = 2000 + (employee.baseSalesSalay - 20000) * employee.advanceMiddlePercent;
        } else
            result = 0;
        return result;
    }
}
