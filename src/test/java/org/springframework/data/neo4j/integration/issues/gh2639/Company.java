package org.springframework.data.neo4j.integration.issues.gh2639;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.StringJoiner;

@Node
public class Company {

	@Id
	@GeneratedValue
	private Long id;

	private final String name;
	@Relationship(type = "EMPLOYEE")
	private final List<Person> employees;


	public Company(String name, List<Person> employees) {
		this.name = name;
		this.employees = employees;
	}

	public void addEmployee(Person person){
		employees.add(person);
	}

	public List<Person> getEmployees() {
		return employees;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Company.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("name='" + name + "'")
				.add("employees=" + employees)
				.toString();
	}
}
