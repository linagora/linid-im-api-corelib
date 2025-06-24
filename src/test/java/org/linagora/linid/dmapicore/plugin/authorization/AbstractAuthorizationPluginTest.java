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

package org.linagora.linid.dmapicore.plugin.authorization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.linagora.linid.dmapicore.plugin.config.dto.AuthorizationConfiguration;
import org.linagora.linid.dmapicore.plugin.config.dto.RootConfiguration;
import org.linagora.linid.dmapicore.plugin.entity.DynamicEntity;
import org.linagora.linid.dmapicore.plugin.task.TaskExecutionContext;
import org.springframework.lang.NonNull;
import org.springframework.util.MultiValueMap;

@DisplayName("Test class: AbstractAuthorizationPlugin")
class AbstractAuthorizationPluginTest {
  @Test
  @DisplayName("Test setConfiguration and getConfiguration")
  void testSetAndGetConfiguration() {
    var plugin = new TestAuthorizationPlugin();
    var config = new AuthorizationConfiguration();
    config.setType("testAuthorization");

    plugin.setConfiguration(config);

    AuthorizationConfiguration retrievedConfig = plugin.getConfiguration();

    assertNotNull(retrievedConfig);
    assertEquals("testAuthorization", retrievedConfig.getType());
  }

  private static class TestAuthorizationPlugin extends AbstractAuthorizationPlugin {
    @Override
    public boolean supports(@NonNull String s) {
      return false;
    }

    @Override
    public void updateConfiguration(RootConfiguration configuration) {

    }

    @Override
    public void validateToken(HttpServletRequest request, TaskExecutionContext context) {

    }

    @Override
    public void isAuthorized(HttpServletRequest request, DynamicEntity entity, String action, TaskExecutionContext context) {

    }

    @Override
    public void isAuthorized(HttpServletRequest request, DynamicEntity entity, String id, String action,
                             TaskExecutionContext context) {

    }

    @Override
    public void isAuthorized(HttpServletRequest request, DynamicEntity entity, MultiValueMap<String, String> filters,
                             String action, TaskExecutionContext context) {

    }
  }
}
