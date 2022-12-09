package org.springframework.data.neo4j.integration.issues.gh2639;

import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.transaction.Neo4jBookmarkManager;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.test.BookmarkCapture;
import org.springframework.data.neo4j.test.Neo4jExtension;
import org.springframework.data.neo4j.test.Neo4jImperativeTestConfiguration;
import org.springframework.data.neo4j.test.Neo4jIntegrationTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Gerrit Meier
 */
@Neo4jIntegrationTest
public class Gh2639IT {

	protected static Neo4jExtension.Neo4jConnectionSupport neo4jConnectionSupport;

	@Test
	void relationshipsOfGenericRelationshipsGetResolvedCorrectly(@Autowired CompanyRepository companyRepository) {
		Person greg = new Sales("Greg");
		Person roy = new Sales("Roy");
		Person craig = new Sales("Craig");

		// devs
		Language java = new Language("java", "1.5");
		Language perl = new Language("perl", "6.0");
		List<Language> languages = new java.util.ArrayList<>();
		languages.add(java);
		languages.add(perl);
		Developer harry = new Developer("Harry", languages);

		// setup and save the company
		List<Person> team = Arrays.asList(
					greg,
					roy,
					craig,
				harry);
		Company acme = new Company("ACME", team);
		companyRepository.save(acme);


		// read company from db
		Company loadedAcme = companyRepository.findByName("ACME");

		// find Harry...
		Developer loadedHarry = loadedAcme.getEmployees().stream()
				.filter(e -> e instanceof Developer)
				.map(e -> (Developer) e)
				.filter(developer -> developer.getName().equals("Harry"))
				.findFirst().get();

		// ... and check for harry's programming skills
		assertThat(loadedHarry.getProgrammingLanguages()).isNotEmpty();
	}

	interface CompanyRepository extends Neo4jRepository<Company, Long> {
		Company findByName(String name);
	}

	@Configuration
	@EnableTransactionManagement
	@EnableNeo4jRepositories(considerNestedRepositories = true)
	static class Config extends Neo4jImperativeTestConfiguration {

		@Bean
		public BookmarkCapture bookmarkCapture() {
			return new BookmarkCapture();
		}

		@Override
		public PlatformTransactionManager transactionManager(
				Driver driver, DatabaseSelectionProvider databaseNameProvider) {

			BookmarkCapture bookmarkCapture = bookmarkCapture();
			return new Neo4jTransactionManager(driver, databaseNameProvider,
					Neo4jBookmarkManager.create(bookmarkCapture));
		}

		@Override
		protected Collection<String> getMappingBasePackages() {
			return Collections.singleton(Company.class.getPackage().getName());
		}

		@Bean
		public Driver driver() {

			return neo4jConnectionSupport.getDriver();
		}

		@Override
		public boolean isCypher5Compatible() {
			return neo4jConnectionSupport.isCypher5SyntaxCompatible();
		}
	}
}
