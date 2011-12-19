package com.netcracker.lab3;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: mpogoda
 * Date: 19/12/11
 * Time: 20:17
 * <p/>
 * Managers are employees too.
 *
 * @author Michael Pogoda
 * @version 0.1.0
 */
public class Manager extends Employee {
    private final HashSet<Project> managedProjects;

    public Manager(String name, Department department) {
        super(name, department);
        managedProjects = new HashSet<>();
    }

    public HashSet<Project> getManagedProjects() {
        return managedProjects;
    }
}
