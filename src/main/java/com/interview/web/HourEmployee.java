package com.interview.web;


import com.interview.inteface.EmployeeSalaryStrategy;

public class HourEmployee extends AbstractEmployee implements EmployeeSalaryStrategy {

    public HourEmployee() {
    }

    public HourEmployee(double hoursSalay, double workHours) {
        this.hoursSalay = hoursSalay;
        this.workHours = workHours;
    }

    @Override
    public double calcSalary(AbstractEmployee employee) {
        double result = 0;
        if (employee.workHours > 160) {
            result = new Double(160 * employee.hoursSalay + (employee.workHours - 160) * employee.hoursSalay * 1.3);
        } else
            result = employee.workHours * employee.hoursSalay;
        return result;
    }

}
