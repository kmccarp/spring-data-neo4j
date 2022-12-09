package org.springframework.data.neo4j.integration.issues.gh2639;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CompanyRepository extends Neo4jRepository<Company, Long> {

	Company findByName(String name);

}
