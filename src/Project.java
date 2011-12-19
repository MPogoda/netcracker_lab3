package com.netcracker.lab3;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: mpogoda
 * Date: 17/12/11
 * Time: 17:54
 * <p/>
 * Class represents only ideal projects:
 * * project doesn't change its goals
 * * project doesn't change its manager
 * * project's manager works on it too
 */
public class Project {
    private final String description;
    private final HashSet<Employee> executors;
    private final Manager manager;
    private final Customer customer;

    public Project(String description, Manager manager, Customer customer) {
        this.description = description;
        this.manager = manager;
        executors = new HashSet<>();
        executors.add(manager);
        manager.getProjects().add(this);
        manager.getManagedProjects().add(this);
        this.customer = customer;
        customer.getProjects().add(this);
    }

    public Manager getManager() {
        return manager;
    }

    public HashSet<Employee> getExecutors() {
        return executors;
    }

    public String getDescription() {
        return description;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        return description.equals(project.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}