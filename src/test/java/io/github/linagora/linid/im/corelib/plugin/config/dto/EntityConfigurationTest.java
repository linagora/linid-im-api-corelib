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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test class: EntityConfiguration")
class EntityConfigurationTest {
  @Test
  @DisplayName("Test name getter and setter")
  void testName() {
    EntityConfiguration entity = new EntityConfiguration();
    entity.setName("entityName");
    assertEquals("entityName", entity.getName());
  }

  @Test
  @DisplayName("Test provider getter and setter")
  void testProvider() {
    EntityConfiguration entity = new EntityConfiguration();
    entity.setProvider("providerName");
    assertEquals("providerName", entity.getProvider());
  }

  @Test
  @DisplayName("Test disabledRoutes getter and setter")
  void testDisabledRoutes() {
    EntityConfiguration entity = new EntityConfiguration();
    assertNotNull(entity.getDisabledRoutes());
    entity.setDisabledRoutes(List.of("test"));
    assertEquals(List.of("test"), entity.getDisabledRoutes());
  }

  @Test
  @DisplayName("Test route getter and setter with explicit route")
  void testRouteWithExplicitRoute() {
    EntityConfiguration entity = new EntityConfiguration();
    entity.setName("entityName");
    entity.setRoute("customRoute");
    assertEquals("customRoute", entity.getRoute());
  }

  @Test
  @DisplayName("Test route getter returns name when route is null")
  void testRouteReturnsNameWhenNull() {
    EntityConfiguration entity = new EntityConfiguration();
    entity.setName("entityName");
    entity.setRoute(null);
    assertEquals("entityName", entity.getRoute());
  }

  @Test
  @DisplayName("Test attributes getter and setter")
  void testAttributes() {
    EntityConfiguration entity = new EntityConfiguration();
    AttributeConfiguration attr1 = new AttributeConfiguration();
    AttributeConfiguration attr2 = new AttributeConfiguration();

    entity.setAttributes(List.of(attr1, attr2));
    List<AttributeConfiguration> attrs = entity.getAttributes();

    assertNotNull(attrs);
    assertEquals(2, attrs.size());
    assertTrue(attrs.contains(attr1));
    assertTrue(attrs.contains(attr2));
  }

  @Test
  @DisplayName("Test tasks getter and setter")
  void testTasks() {
    EntityConfiguration entity = new EntityConfiguration();
    TaskConfiguration task1 = new TaskConfiguration();
    TaskConfiguration task2 = new TaskConfiguration();

    entity.setTasks(List.of(task1, task2));
    List<TaskConfiguration> tasks = entity.getTasks();

    assertNotNull(tasks);
    assertEquals(2, tasks.size());
    assertTrue(tasks.contains(task1));
    assertTrue(tasks.contains(task2));
  }

  @Test
  @DisplayName("Test access getter and setter")
  void testAccess() {
    EntityConfiguration entity = new EntityConfiguration();
    Map<String, Object> accessMap = new HashMap<>();
    accessMap.put("key1", "value1");
    accessMap.put("key2", 42);

    entity.setAccess(accessMap);
    Map<String, Object> result = entity.getAccess();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("value1", result.get("key1"));
    assertEquals(42, result.get("key2"));
  }
}
