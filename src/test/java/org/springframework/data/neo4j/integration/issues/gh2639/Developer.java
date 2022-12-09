package org.springframework.data.neo4j.integration.issues.gh2639;


import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;
import java.util.StringJoiner;

@Node
public class Developer extends Person {

	private final List<Language> programmingLanguages;
	private final String name;

	public Developer(String name, List<Language> programmingLanguages) {
		this.name = name;
		this.programmingLanguages = programmingLanguages;
	}

	public List<Language> getProgrammingLanguages() {
		return programmingLanguages;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Developer.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
				.add("programmingLanguages=" + programmingLanguages)
				.toString();
	}
}
