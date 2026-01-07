/*
 * Copyright (C) 2020-2026 Linagora
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version, provided you comply with the Additional Terms applicable for LinID Identity Manager software by
 * LINAGORA pursuant to Section 7 of the GNU Affero General Public License, subsections (b), (c), and (e), pursuant to
 * which these Appropriate Legal Notices must notably (i) retain the display of the "LinID™" trademark/logo at the top
 * of the interface window, the display of the “You are using the Open Source and free version of LinID™, powered by
 * Linagora © 2009–2013. Contribute to LinID R&D by subscribing to an Enterprise offer!” infobox and in the e-mails
 * sent with the Program, notice appended to any type of outbound messages (e.g. e-mail and meeting requests) as well
 * as in the LinID Identity Manager user interface, (ii) retain all hypertext links between LinID Identity Manager
 * and https://linid.org/, as well as between LINAGORA and LINAGORA.com, and (iii) refrain from infringing LINAGORA
 * intellectual property rights over its trademarks and commercial brands. Other Additional Terms apply, see
 * <http://www.linagora.com/licenses/> for more details.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License and its applicable Additional Terms for
 * LinID Identity Manager along with this program. If not, see <http://www.gnu.org/licenses/> for the GNU Affero
 * General Public License version 3 and <http://www.linagora.com/licenses/> for the Additional Terms applicable to the
 * LinID Identity Manager software.
 */

package io.github.linagora.linid.im.corelib.plugin.config.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test class: ValidationConfiguration")
class ValidationConfigurationTest {

  @Test
  @DisplayName("Test getType and setType")
  void testType() {
    ValidationConfiguration config = new ValidationConfiguration();
    config.setType("regex");
    assertEquals("regex", config.getType());
  }

  @Test
  @DisplayName("Test getName and setName")
  void testName() {
    ValidationConfiguration config = new ValidationConfiguration();
    config.setName("name");
    assertEquals("name", config.getName());
  }

  @Test
  @DisplayName("Test getPhases and setPhases")
  void testPhases() {
    ValidationConfiguration config = new ValidationConfiguration();
    List<String> phases = List.of("create", "update");
    config.setPhases(phases);

    assertEquals(phases, config.getPhases());
    assertTrue(config.getPhases().contains("create"));
    assertTrue(config.getPhases().contains("update"));
  }

  @Test
  @DisplayName("Test addOption adds valid keys and skips reserved ones")
  void testAddOption() {
    ValidationConfiguration config = new ValidationConfiguration();
    config.addOption("minLength", 5);
    config.addOption("name", "shouldBeIgnored");
    config.addOption("type", "shouldBeIgnored");
    config.addOption("phases", "shouldBeIgnoredToo");

    Map<String, Object> options = config.getOptions();
    assertEquals(1, options.size());
    assertEquals(5, options.get("minLength"));
    assertFalse(options.containsKey("name"));
    assertFalse(options.containsKey("type"));
    assertFalse(options.containsKey("phases"));
  }

  @Test
  @DisplayName("Test getOptions returns current option map")
  void testGetOptions() {
    ValidationConfiguration config = new ValidationConfiguration();
    assertNotNull(config.getOptions());
    assertTrue(config.getOptions().isEmpty());

    config.addOption("custom", true);
    assertEquals(true, config.getOptions().get("custom"));
  }
}
