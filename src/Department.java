package com.netcracker.lab3;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: mpogoda
 * Date: 17/12/11
 * Time: 17:48
 * <p/>
 * Class, representing department.
 * Assume, that department name is unique
 *
 * @author Michael Pogoda
 * @version 0.1
 */
public class Department {
    private final HashSet<Employee> employees;
    private final String name;

    public Department(String name) {
        this.name = name;
        employees = new HashSet<>();
    }

    public HashSet<Employee> getEmployees() {
        return employees;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;

        Department that = (Department) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
