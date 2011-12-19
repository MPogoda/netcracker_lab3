package com.netcracker.lab3;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: mpogoda
 * Date: 17/12/11
 * Time: 17:59
 * <p/>
 * Class represents customer.
 * Assume that name is unique.
 *
 * @author Michael Pogoda
 * @version 0.1.0
 */
public class Customer {
    private final String name;
    private final HashSet<Project> projects;

    /**
     * Creates new customer with name specified by its parameter.
     *
     * @param name name of customer
     */
    public Customer(String name) {
        this.name = name;
        projects = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public HashSet<Project> getProjects() {
        return projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        return name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

