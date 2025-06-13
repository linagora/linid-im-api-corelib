/*
 * Copyright (C) 2020-2025 Linagora
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version, provided you comply with the Additional Terms applicable for LinID Directory Manager software by
 * LINAGORA pursuant to Section 7 of the GNU Affero General Public License, subsections (b), (c), and (e), pursuant to
 * which these Appropriate Legal Notices must notably (i) retain the display of the "LinID™" trademark/logo at the top
 * of the interface window, the display of the “You are using the Open Source and free version of LinID™, powered by
 * Linagora © 2009–2013. Contribute to LinID R&D by subscribing to an Enterprise offer!” infobox and in the e-mails
 * sent with the Program, notice appended to any type of outbound messages (e.g. e-mail and meeting requests) as well
 * as in the LinID Directory Manager user interface, (ii) retain all hypertext links between LinID Directory Manager
 * and https://linid.org/, as well as between LINAGORA and LINAGORA.com, and (iii) refrain from infringing LINAGORA
 * intellectual property rights over its trademarks and commercial brands. Other Additional Terms apply, see
 * <http://www.linagora.com/licenses/> for more details.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License and its applicable Additional Terms for
 * LinID Directory Manager along with this program. If not, see <http://www.gnu.org/licenses/> for the GNU Affero
 * General Public License version 3 and <http://www.linagora.com/licenses/> for the Additional Terms applicable to the
 * LinID Directory Manager software.
 */

package org.linagora.linid.dmapicore.plugin.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.linagora.linid.dmapicore.plugin.config.dto.AttributeConfiguration;
import org.linagora.linid.dmapicore.plugin.config.dto.EntityConfiguration;

@DisplayName("Test class: DynamicEntity")
class DynamicEntityTest {

  @Test
  @DisplayName("Test get and set attributes")
  void testAttributes() {
    DynamicEntity entity = new DynamicEntity();
    Map<String, Object> attributes = new HashMap<>();
    attributes.put("username", "jdoe");
    attributes.put("age", 42);

    entity.setAttributes(attributes);

    assertEquals(attributes, entity.getAttributes());
    assertEquals("jdoe", entity.getAttributes().get("username"));
    assertEquals(42, entity.getAttributes().get("age"));
  }

  @Test
  @DisplayName("Test get and set configuration")
  void testConfiguration() {
    DynamicEntity entity = new DynamicEntity();
    EntityConfiguration config = new EntityConfiguration();
    config.setName("userEntity");

    entity.setConfiguration(config);

    assertEquals(config, entity.getConfiguration());
    assertEquals("userEntity", entity.getConfiguration().getName());
  }

  @Test
  @DisplayName("Test getAttributeConfiguration returns expected attribute")
  void testGetAttributeConfigurationFound() {
    DynamicEntity entity = new DynamicEntity();

    AttributeConfiguration attr1 = new AttributeConfiguration();
    attr1.setName("email");

    AttributeConfiguration attr2 = new AttributeConfiguration();
    attr2.setName("login");

    EntityConfiguration config = new EntityConfiguration();
    config.setAttributes(List.of(attr1, attr2));

    entity.setConfiguration(config);

    Optional<AttributeConfiguration> result = entity.getAttributeConfiguration("login");

    assertTrue(result.isPresent());
    assertEquals("login", result.get().getName());
  }

  @Test
  @DisplayName("Test getAttributeConfiguration returns empty when not found")
  void testGetAttributeConfigurationNotFound() {
    DynamicEntity entity = new DynamicEntity();

    AttributeConfiguration attr1 = new AttributeConfiguration();
    attr1.setName("email");

    EntityConfiguration config = new EntityConfiguration();
    config.setAttributes(List.of(attr1));

    entity.setConfiguration(config);

    Optional<AttributeConfiguration> result = entity.getAttributeConfiguration("unknown");

    assertTrue(result.isEmpty());
  }
}
