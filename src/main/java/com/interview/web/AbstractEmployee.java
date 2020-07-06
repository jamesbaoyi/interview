package com.interview.web;

import com.interview.inteface.Employee;


/**
 * 员工抽象类
 */
public abstract class AbstractEmployee implements Employee {

    //小时工工作时长
    double workHours = 0;

    //普通员工基础薪资
    double baseSalay = 0;

    //小时工基础薪资
    double hoursSalay = 0;

    //销售超出20000提成
    double advanceLowPercent = 0;

    //销售超出30000提成
    double advanceMiddlePercent = 0;

    //销售基础销售额
    double baseSalesSalay = 0;
}
