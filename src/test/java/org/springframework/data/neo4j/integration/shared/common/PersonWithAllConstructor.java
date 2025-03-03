/*
 * Copyright 2011-2023 the original author or authors.
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
package org.springframework.data.neo4j.integration.shared.common;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.neo4j.driver.types.Point;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author Gerrit Meier
 * @author Michael J. Simons
 */
@Getter
@Setter
@Node
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class PersonWithAllConstructor {

	@Id @GeneratedValue @With private final Long id;

	private final String name;

	@Property("first_name") private String firstName;

	private final String sameValue;

	private final Boolean cool;

	private final Long personNumber;

	private final LocalDate bornOn;

	private String nullable;

	private List<String> things;

	private final Point place;

	private final Instant createdAt;
}
