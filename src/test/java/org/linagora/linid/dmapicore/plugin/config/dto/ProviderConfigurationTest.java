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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test class: ProviderConfiguration")
class ProviderConfigurationTest {

  @Test
  @DisplayName("Verify getName and setName methods")
  void testName() {
    ProviderConfiguration provider = new ProviderConfiguration();
    provider.setName("providerName");
    assertEquals(
        "providerName", provider.getName(), "The name should be set and retrieved correctly");
  }

  @Test
  @DisplayName("Verify getType and setType methods")
  void testType() {
    ProviderConfiguration provider = new ProviderConfiguration();
    provider.setType("ldap");
    assertEquals("ldap", provider.getType(), "The type should be set and retrieved correctly");
  }

  @Test
  @DisplayName("Verify addOption adds entries except 'name' and 'type' key which is ignored")
  void testAddOption() {
    ProviderConfiguration provider = new ProviderConfiguration();
    provider.addOption("option1", "value1");
    provider.addOption("name", "shouldBeIgnored");
    provider.addOption("type", "shouldBeIgnored");
    provider.addOption("option2", 123);

    Map<String, Object> options = provider.getOptions();

    assertEquals(2, options.size(), "Options map should contain exactly 2 entries");
    assertEquals(
        "value1", options.get("option1"), "Option 'option1' should have the correct value");
    assertEquals(123, options.get("option2"), "Option 'option2' should have the correct value");
    assertFalse(
        options.containsKey("name"), "Options map should not contain the reserved key 'name'");
    assertFalse(
        options.containsKey("type"), "Options map should not contain the reserved key 'type'");
  }

  @Test
  @DisplayName("Verify getOptions returns the current options map and reflects changes")
  void testGetOptions() {
    ProviderConfiguration provider = new ProviderConfiguration();
    Map<String, Object> options = provider.getOptions();

    assertNotNull(options, "Options map should not be null initially");
    assertTrue(options.isEmpty(), "Options map should be empty initially");

    provider.addOption("key", "value");
    assertEquals(
        "value",
        provider.getOptions().get("key"),
        "Options map should reflect added key-value pair");
  }
}
