package com.inheritance;

import com.employee.Employee;

public class Manager extends Employee {
    private double bonus;

    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        bonus = 0;
    }

    public double getSalary() {
        return bonus + super.getSalary();
    }

    public void setBonus(double b) {
        bonus = b;
    }
}
