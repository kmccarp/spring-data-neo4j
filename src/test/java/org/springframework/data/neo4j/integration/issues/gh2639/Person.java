package org.springframework.data.neo4j.integration.issues.gh2639;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public abstract class Person {

	@Id
	@GeneratedValue
	private Long id;

}
