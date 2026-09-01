package composite;

import java.util.ArrayList;
import java.util.List;

interface OrganizationComponent {
    String getName();
    double getTotalSalary();
    void add(OrganizationComponent component);
    void remove(OrganizationComponent component);
    String toXml(int indentLevel);
}

class Employee implements OrganizationComponent {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getTotalSalary() {
        return salary;
    }

    @Override
    public void add(OrganizationComponent component) {
        // No implementation for leaf
        System.out.println("Cannot add to an Employee.");
    }

    @Override
    public void remove(OrganizationComponent component) {
        System.out.println("Cannot remove from an Employee.");
    }

    @Override
    public String toXml(int indentLevel) {
        String indent = "  ".repeat(indentLevel);
        return indent + "<employee name=\"" + name + "\" salary=\"" + salary + "\" />";
    }
}

class Department implements OrganizationComponent {
    private String name;
    private List<OrganizationComponent> components = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getTotalSalary() {
        double total = 0;
        for (OrganizationComponent component : components) {
            total += component.getTotalSalary();
        }
        return total;
    }

    @Override
    public void add(OrganizationComponent component) {
        components.add(component);
    }

    @Override
    public void remove(OrganizationComponent component) {
        components.remove(component);
    }

    @Override
    public String toXml(int indentLevel) {
        String indent = "  ".repeat(indentLevel);
        StringBuilder xml = new StringBuilder();
        xml.append(indent).append("<department name=\"").append(name).append("\">\n");

        for (OrganizationComponent component : components) {
            xml.append(component.toXml(indentLevel + 1)).append("\n");
        }

        xml.append(indent).append("</department>");
        return xml.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Employee alice = new Employee("Alice", 5000);
        Employee bob = new Employee("Bob", 6000);
        Employee charlie = new Employee("Charlie", 5500);
        Employee diana = new Employee("Diana", 7000);

        Department engineering = new Department("Engineering");
        Department qa = new Department("Quality Assurance");
        Department hr = new Department("Human Resources");
        Department company = new Department("Company");

        engineering.add(alice);
        engineering.add(bob);

        qa.add(charlie);

        hr.add(diana);

        company.add(engineering);
        company.add(qa);
        company.add(hr);

        System.out.println("Total Salary of Organization: " + company.getTotalSalary() + "\n");

        System.out.println("--- Adding a new employee 'Eve' to Engineering ---");
        Employee eve = new Employee("Eve", 6500);
        engineering.add(eve);
        System.out.println("Total Salary after adding Eve: " + company.getTotalSalary() + "\n");

        System.out.println("--- Removing 'Bob' from Engineering ---");
        engineering.remove(bob);
        System.out.println("Total Salary after removing Bob: " + company.getTotalSalary() + "\n");

        System.out.println("--- Organizational Structure (XML) ---");
        System.out.println(company.toXml(0));
    }
}