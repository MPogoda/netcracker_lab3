package com.netcracker.lab3;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: mpogoda
 * Date: 17/12/11
 * Time: 17:45
 * <p/>
 * Class, representing employee.
 * Assume, that name is key |unique|
 *
 * @author Michael Pogoda
 * @version 0.0.1
 */
public class Employee {
    private String name;
    private Department department;
    private final HashSet<Project> projects;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department.getEmployees().remove(this);
        this.department = department;
        this.department.getEmployees().add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<Project> getProjects() {
        return projects;
    }

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
        this.projects = new HashSet<>();
        department.getEmployees().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (!department.equals(employee.department)) return false;
        return name.equals(employee.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + department.hashCode();
        return result;
    }

    public Manager makeManager() {
        Manager manager = new Manager(this.name, this.department);
        manager.getProjects().addAll(projects);
        return manager;
    }
}
