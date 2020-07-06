package com.interview.web;

import com.interview.inteface.EmployeeSalaryStrategy;
import org.jdom.*;
import org.jdom.input.SAXBuilder;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Test {

    public static void main(String[] args) {

        //员工信息存储
        Map<Integer, List<Employee>> map = new HashMap<Integer, List<Employee>>();

        //创建SAXBuilder对象
        SAXBuilder saxBuilder = new SAXBuilder();
        //创建输入流
        InputStream is = null;
        try {
            is = new FileInputStream(new File("src/main/resources/demo.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //将输入流加载到build中
        Document document = null;
        try {
            document = saxBuilder.build(is);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取根节点
        Element rootElement = document.getRootElement();
        //获取子节点
        List<Element> children = rootElement.getChildren();
        for (Element child : children) {
            List<Attribute> attributes = child.getAttributes();
            //打印属性
            Integer key = 0;
            for (Attribute attr : attributes) {
                try {
                    key = attr.getIntValue();
                } catch (DataConversionException e) {
                    e.printStackTrace();
                }
            }
            List<Employee> employees = new ArrayList<Employee>();
            List<Element> childrenList = child.getChildren();
            for (Element o : childrenList) {
                List<Attribute> oAttributes = o.getAttributes();
                Employee employee = new Employee();
                for (Attribute attr : oAttributes) {
                    try {
                        switch (attr.getName()) {
                            case "name":
                                employee.setName(attr.getValue());
                                break;
                            case "type":
                                employee.setType(attr.getValue());
                                break;
                            case "birthday":
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    employee.setBirthday(sdf.parse(attr.getValue()));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "workingHours":
                                employee.setWorkingHours(attr.getDoubleValue());
                                break;
                            case "amount":
                                employee.setAmount(attr.getDoubleValue());
                                break;
                        }
                    } catch (DataConversionException ex) {
                        ex.printStackTrace();
                    }
                }
                employees.add(employee);
            }
            map.put(key, employees);
        }
        try {
            if (Objects.nonNull(is)) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //总薪资
        double sumSalary = 0;
        for (Map.Entry<Integer, List<Employee>> entry : map.entrySet()) {
            if (9 == entry.getKey()) {
                sumSalary = getSumSalary(sumSalary, entry.getValue(), 9);
            } else if (10 == entry.getKey()) {
                sumSalary = getSumSalary(sumSalary, entry.getValue(), 10);
            }
        }

        System.out.println("公司应支付员工工资总额为：" + new java.text.DecimalFormat("#.00").format(sumSalary));
    }

    /**
     * 计算公司员工总薪资
     *
     * @param sumSalary
     * @param employees
     * @param month
     * @return
     */
    private static double getSumSalary(double sumSalary, List<Employee> employees, int month) {
        for (Employee employee : employees) {
            switch (employee.getType()) {
                case "salary":
                    EmployeeSalaryStrategy employeeSalaryStrategy = new CommonEmployee();
                    if (employee.getBirthday().getMonth() == month) {
                        //普通员工过生日薪资
                        BirthdaySalary birthdaySalary = new BirthdaySalary(employeeSalaryStrategy);
                        sumSalary = sumSalary + birthdaySalary.calcBirthdaySalary(new CommonEmployee(2000), 100);
                    } else {
                        //普通员工
                        Salary salary = new Salary(employeeSalaryStrategy);
                        sumSalary += salary.calcSalay(new CommonEmployee(2000));
                    }
                    break;
                case "hour":
                    EmployeeSalaryStrategy hourEmployee = new HourEmployee();
                    if (employee.getBirthday().getMonth() == 9) {
                        //小时工生日薪资
                        BirthdaySalary birthdayHourSalary = new BirthdaySalary(hourEmployee);
                        sumSalary += birthdayHourSalary.calcBirthdaySalary(new HourEmployee(40, employee.getWorkingHours()), 100);
                    } else {
                        //小时工薪资
                        Salary hourSalary = new Salary(hourEmployee);
                        sumSalary += hourSalary.calcSalay(new HourEmployee(40, employee.getWorkingHours()));
                    }
                    break;
                case "sale":
                    EmployeeSalaryStrategy salesEmployee = new SalesEmployee();
                    if (employee.getBirthday().getTime() == 9) {
                        //销售过生日薪资
                        BirthdaySalary birthdaySalesSalary = new BirthdaySalary(salesEmployee);
                        sumSalary += birthdaySalesSalary.calcBirthdaySalary(new SalesEmployee(employee.getAmount(), 0.05, 0.08), 100);
                        ;
                    } else {
                        //销售薪资
                        Salary salarySales = new Salary(salesEmployee);
                        sumSalary += salarySales.calcSalay(new SalesEmployee(employee.getAmount(), 0.05, 0.08));

                    }
                    break;
            }
        }
        return sumSalary;
    }
}
