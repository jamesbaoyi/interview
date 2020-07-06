package com.interview.inteface;


import com.interview.web.AbstractEmployee;

/**
 * 计算雇工的薪水
 */
public interface EmployeeSalaryStrategy {

    /**
     * 普通薪资计算
     *
     * @param employee
     * @return
     */
    double calcSalary(AbstractEmployee employee);

}
