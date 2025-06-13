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

package org.linagora.linid.dmapicore.plugin.config.dto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test class: AttributeConfiguration")
class AttributeConfigurationTest {
  @Test
  @DisplayName("Test getters and setters for name and type")
  void testNameAndType() {
    AttributeConfiguration attr = new AttributeConfiguration();
    attr.setName("username");
    attr.setType("string");

    assertEquals("username", attr.getName());
    assertEquals("string", attr.getType());
  }

  @Test
  @DisplayName("Test validations getter and setter")
  void testValidations() {
    AttributeConfiguration attr = new AttributeConfiguration();
    ValidationConfiguration val1 = new ValidationConfiguration();
    ValidationConfiguration val2 = new ValidationConfiguration();

    attr.setValidations(List.of(val1, val2));

    List<ValidationConfiguration> validations = attr.getValidations();
    assertNotNull(validations);
    assertEquals(2, validations.size());
    assertTrue(validations.contains(val1));
    assertTrue(validations.contains(val2));
  }

  @Test
  @DisplayName("Test addAccess ignores reserved keys")
  void testAddAccessIgnoresReservedKeys() {
    AttributeConfiguration attr = new AttributeConfiguration();

    attr.addAccess("name", "shouldBeIgnored");
    attr.addAccess("type", "shouldBeIgnored");
    attr.addAccess("validations", List.of());

    assertTrue(attr.getAccess().isEmpty());
  }

  @Test
  @DisplayName("Test addAccess adds non-reserved keys")
  void testAddAccessAddsKeys() {
    AttributeConfiguration attr = new AttributeConfiguration();

    attr.addAccess("customKey1", "value1");
    attr.addAccess("customKey2", 42);

    Map<String, Object> access = attr.getAccess();
    assertEquals(2, access.size());
    assertEquals("value1", access.get("customKey1"));
    assertEquals(42, access.get("customKey2"));
  }

  @Test
  @DisplayName("Test getAccess returns modifiable map")
  void testGetAccessModifiable() {
    AttributeConfiguration attr = new AttributeConfiguration();

    attr.addAccess("key", "value");
    Map<String, Object> access = attr.getAccess();

    assertDoesNotThrow(() -> access.put("newKey", "newValue"));
    assertEquals("newValue", attr.getAccess().get("newKey"));
  }
}
