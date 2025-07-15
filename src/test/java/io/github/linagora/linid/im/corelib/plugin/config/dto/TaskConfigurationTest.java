/*
 * Copyright (C) 2020-2025 Linagora
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test class: TaskConfiguration")
class TaskConfigurationTest {

  @Test
  @DisplayName("Test getName and setName")
  void testName() {
    TaskConfiguration task = new TaskConfiguration();
    task.setName("syncTask");
    assertEquals("syncTask", task.getName());
  }

  @Test
  @DisplayName("Test getType and setType")
  void testType() {
    TaskConfiguration task = new TaskConfiguration();
    task.setType("syncTask");
    assertEquals("syncTask", task.getType());
  }

  @Test
  @DisplayName("Test getPhases and setPhases")
  void testPhases() {
    TaskConfiguration task = new TaskConfiguration();
    List<String> phases = Arrays.asList("pre", "post");
    task.setPhases(phases);
    assertEquals(phases, task.getPhases());
    assertEquals(2, task.getPhases().size());
  }

  @Test
  @DisplayName("Test getOptions initially empty")
  void testGetOptionsInitiallyEmpty() {
    TaskConfiguration task = new TaskConfiguration();
    assertNotNull(task.getOptions());
    assertTrue(task.getOptions().isEmpty());
  }

  @Test
  @DisplayName("Test addOption adds options except 'name'n 'type' and 'phases'")
  void testAddOption() {
    TaskConfiguration task = new TaskConfiguration();
    task.addOption("timeout", 3000);
    task.addOption("name", "ignored");
    task.addOption("type", "ignored");
    task.addOption("phases", "ignored");

    Map<String, Object> options = task.getOptions();

    assertEquals(1, options.size());
    assertEquals(3000, options.get("timeout"));
    assertFalse(options.containsKey("name"));
    assertFalse(options.containsKey("type"));
    assertFalse(options.containsKey("phases"));
  }

  @Test
  @DisplayName("Test getOptions after adding multiple entries")
  void testGetOptions() {
    TaskConfiguration task = new TaskConfiguration();
    task.addOption("retry", true);
    task.addOption("maxAttempts", 5);

    Map<String, Object> options = task.getOptions();
    assertEquals(2, options.size());
    assertTrue((Boolean) options.get("retry"));
    assertEquals(5, options.get("maxAttempts"));
  }
}
