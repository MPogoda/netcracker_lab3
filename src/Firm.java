package com.netcracker.lab3;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: mpogoda
 * Date: 17/12/11
 * Time: 18:07
 * <p/>
 * !!!
 * To all methods, that have in parameters objects of classes, defined in that package,
 * you can pass real object or dummy object, where only key is specified.
 * For example, if method expects to find Manager instance, you can pass
 * * real Manager object with non-empty managedProjects
 * * new Manager(managerName, departmentName)
 *
 * @author Michael Pogoda
 * @version 0.1.0
 */
public class Firm {
    private final HashSet<Employee> employees;
    private final HashSet<Customer> customers;
    private final HashSet<Project> projects;
    private final HashSet<Department> departments;
    private final HashSet<Manager> managers;
    @SuppressWarnings("FieldCanBeLocal")
    private final String name;

    public Firm(String name) {
        this.name = name;
        employees = new HashSet<>();
        customers = new HashSet<>();
        projects = new HashSet<>();
        departments = new HashSet<>();
        managers = new HashSet<>();
    }

    /**
     * Hire new worker
     *
     * @param name        name of new worker
     * @param _department destination department of worker
     * @throws NoSuchDepartmentException if such department doesn't exist
     */
    public void hire(String name, Department _department) throws NoSuchDepartmentException {
        for (Department department : departments) {
            if (_department.equals(department)) {
                employees.add(new Employee(name, department));
            }
        }

        throw new NoSuchDepartmentException();
    }

    public void addCustomer(String name) {
        customers.add(new Customer(name));
    }

    public void addDepartment(String name) {
        departments.add(new Department(name));
    }

    public void addProject(String description, Customer _customer, Employee _manager)
            throws NoSuchCustomerException, NoSuchEmployeeException {
        for (Customer customer : customers) {
            if (_customer.equals(customer)) {

                for (Employee employee : employees) {
                    if (employee.equals(_manager)) {
                        Manager manager;
                        if (!(employee instanceof Manager)) {
                            manager = employee.makeManager();
                            employees.remove(employee);
                            employees.add(manager);
                            managers.add(manager);
                        } else {
                            manager = (Manager) employee;
                        }

                        projects.add(new Project(description, manager, customer));
                        return;
                    }
                }
                throw new NoSuchEmployeeException();
            }
        }

        throw new NoSuchCustomerException();
    }

    public void addExecutorToProject(Project _project, Employee _executor)
            throws NoSuchEmployeeException, NoSuchProjectException {
        for (Project project : projects) {
            if (_project.equals(project)) {
                for (Employee employee : employees) {
                    if (employee.equals(_executor)) {
                        project.getExecutors().add(employee);
                        return;
                    }
                }

                throw new NoSuchEmployeeException();
            }
        }

        throw new NoSuchProjectException();
    }

    /**
     * Return all executors of project
     *
     * @param _project
     * @return
     * @throws NoSuchProjectException
     */
    public HashSet<Employee> getProjectExecutors(Project _project) throws NoSuchProjectException {
        for (Project project : projects) {
            if (project.equals(_project)) {
                return new HashSet<>(project.getExecutors());
            }
        }

        throw new NoSuchProjectException();
    }

    /**
     * Get all projects in which employee is participated
     *
     * @param _employee
     * @return
     * @throws NoSuchEmployeeException
     */
    public HashSet<Project> getEmployeeSProjects(Employee _employee) throws NoSuchEmployeeException {
        for (Employee employee : employees) {
            if (employee.equals(_employee)) {
                return new HashSet<>(employee.getProjects());
            }
        }

        throw new NoSuchEmployeeException();
    }

    /**
     * Get all employees in department, that do not participate in any project
     *
     * @param _department
     * @return
     * @throws NoSuchDepartmentException
     */
    public HashSet<Employee> getFreeEmployeesInDepartment(Department _department) throws NoSuchDepartmentException {
        for (Department department : departments) {
            if (_department.equals(department)) {
                HashSet<Employee> result = new HashSet<>();

                for (Employee employee : department.getEmployees()) {
                    if (employee.getProjects().isEmpty()) {
                        result.add(employee);
                    }
                }

                return result;
            }
        }

        throw new NoSuchDepartmentException();
    }

    /**
     * Get all employees in firm that do not participate in any project
     *
     * @return
     */
    public HashSet<Employee> getFreeEmployees() {
        HashSet<Employee> result = new HashSet<>();

        for (Employee employee : employees) {
            if (employee.getProjects().isEmpty()) {
                result.add(employee);
            }
        }

        return result;
    }

    /**
     * Get all workers participated in any project managed by _manager
     *
     * @param _manager
     * @return
     * @throws NoSuchEmployeeException
     */
    public HashSet<Employee> getSubordinates(Manager _manager) throws NoSuchEmployeeException {
        for (Manager manager : managers) {
            if (_manager.equals(manager)) {
                HashSet<Employee> result = new HashSet<>();

                for (Project project : manager.getManagedProjects()) {
                    result.addAll(project.getExecutors());
                }

                result.remove(manager);
                return result;
            }
        }

        throw new NoSuchEmployeeException();
    }

    /**
     * Get all managers managing any project that _employee participated in
     *
     * @param _employee
     * @return
     * @throws NoSuchEmployeeException
     */
    public HashSet<Manager> getManagers(Employee _employee) throws NoSuchEmployeeException {
        for (Employee employee : employees) {
            if (employee.equals(_employee)) {
                HashSet<Manager> result = new HashSet<>();

                for (Project project : employee.getProjects()) {
                    result.add(project.getManager());
                }

                return result;
            }
        }

        throw new NoSuchEmployeeException();
    }

    /**
     * Find all employees participated at least in one project, _employee participated in
     *
     * @param _employee
     * @return
     * @throws NoSuchEmployeeException
     */
    public HashSet<Employee> getColleagues(Employee _employee) throws NoSuchEmployeeException {
        for (Employee employee : employees) {
            if (employee.equals(_employee)) {
                HashSet<Employee> result = new HashSet<>();

                for (Project project : employee.getProjects()) {
                    result.addAll(project.getExecutors());
                }

                result.remove(employee);
                return result;
            }
        }

        throw new NoSuchEmployeeException();
    }

    /**
     * Find all projects ordered by _customer
     *
     * @param _customer
     * @return
     * @throws NoSuchCustomerException
     */
    public HashSet<Project> getProjects(Customer _customer) throws NoSuchCustomerException {
        for (Customer customer : customers) {
            if (customer.equals(_customer)) {
                return new HashSet<>(customer.getProjects());
            }
        }

        throw new NoSuchCustomerException();
    }

    /**
     * Find all employees participated in any project, ordered by _customer
     *
     * @param _customer
     * @return
     * @throws NoSuchCustomerException
     */
    public HashSet<Employee> getCustomersEmployees(Customer _customer) throws NoSuchCustomerException {
        for (Customer customer : customers) {
            if (_customer.equals(customer)) {
                HashSet<Employee> result = new HashSet<>();

                for (Project project : customer.getProjects()) {
                    result.addAll(project.getExecutors());
                }

                return result;
            }
        }

        throw new NoSuchCustomerException();
    }
}
