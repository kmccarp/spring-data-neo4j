package org.springframework.data.neo4j.integration.issues.gh2639;

import org.springframework.data.neo4j.core.schema.Node;

import java.util.StringJoiner;

@Node
public class Sales extends Person {

	private final String name;

	public Sales(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Sales.class.getSimpleName() + "[", "]")
				.add("name='" + name + "'")
				.toString();
	}
}
