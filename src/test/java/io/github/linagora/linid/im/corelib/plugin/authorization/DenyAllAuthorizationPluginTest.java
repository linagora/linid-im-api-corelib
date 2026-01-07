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

package io.github.linagora.linid.im.corelib.plugin.authorization;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.linagora.linid.im.corelib.exception.ApiException;
import io.github.linagora.linid.im.corelib.plugin.config.dto.RootConfiguration;
import io.github.linagora.linid.im.corelib.plugin.entity.DynamicEntity;
import io.github.linagora.linid.im.corelib.plugin.task.TaskExecutionContext;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@DisplayName("Test class:  AllowAllAuthorizationPlugin")
class DenyAllAuthorizationPluginTest {
  @Test
  void updateConfigurationShouldNotThrow() {
    assertDoesNotThrow(() -> new DenyAllAuthorizationPlugin().updateConfiguration(Mockito.mock(RootConfiguration.class)));
  }

  @Test
  void validateTokenShouldThrowApiException() {
    var plugin = new DenyAllAuthorizationPlugin();
    var request = Mockito.mock(HttpServletRequest.class);
    Mockito.when(request.getRequestURI()).thenReturn("/some/route");
    var context = new TaskExecutionContext();

    assertThrows(ApiException.class, () -> plugin.validateToken(request, context));
  }

  @Test
  void isAuthorizedEntityActionShouldThrowApiException() {
    var plugin = new DenyAllAuthorizationPlugin();
    var request = Mockito.mock(HttpServletRequest.class);
    Mockito.when(request.getRequestURI()).thenReturn("/some/route");
    var context = new TaskExecutionContext();
    var entity = new DynamicEntity();

    assertThrows(ApiException.class, () -> plugin.isAuthorized(request, entity, "read", context));
  }

  @Test
  void isAuthorizedEntityIdActionShouldThrowApiException() {
    var plugin = new DenyAllAuthorizationPlugin();
    var request = Mockito.mock(HttpServletRequest.class);
    Mockito.when(request.getRequestURI()).thenReturn("/some/route");
    var context = new TaskExecutionContext();
    var entity = new DynamicEntity();

    assertThrows(ApiException.class, () -> plugin.isAuthorized(request, entity, "123", "read", context));
  }

  @Test
  void isAuthorizedEntityFiltersActionShouldThrowApiException() {
    var plugin = new DenyAllAuthorizationPlugin();
    var request = Mockito.mock(HttpServletRequest.class);
    Mockito.when(request.getRequestURI()).thenReturn("/some/route");
    var context = new TaskExecutionContext();
    var entity = new DynamicEntity();
    MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();

    assertThrows(ApiException.class, () -> plugin.isAuthorized(request, entity, filters, "read", context));
  }

  @Test
  void supportsShouldReturnTrueForDenyAll() {
    var plugin = new DenyAllAuthorizationPlugin();
    assert plugin.supports("deny-all");
  }

  @Test
  void supportsShouldReturnFalseForOther() {
    var plugin = new DenyAllAuthorizationPlugin();
    assert !plugin.supports("allow-all");
  }
}
