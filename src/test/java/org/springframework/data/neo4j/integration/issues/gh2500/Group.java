/*
 * Copyright 2011-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.neo4j.integration.issues.gh2500;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import org.springframework.lang.NonNull;

/**
 * @author Michael J. Simons
 */
@Node
@Getter
@Setter
public class Group {

	@Id
	@GeneratedValue(generatorClass = UUIDStringGenerator.class)
	private String id;

	@Version
	private Long version;

	@NonNull
	private String name;

	@Relationship(type = "BELONGS_TO", direction = Relationship.Direction.INCOMING)
	private Set<Device> devices = new LinkedHashSet<>();

	@Relationship(type = "GROUP_LINK")
	private Set<Group> groups = new LinkedHashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Group group = (Group) o;

		if (!id.equals(group.id)) {
			return false;
		}
		return name.equals(group.name);
	}

	@Override
	public int hashCode() {
		int result = 7;
		result = 31 * result + id.hashCode();
		result = 31 * result + name.hashCode();
		return result;
	}
}
