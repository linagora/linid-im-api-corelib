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

package io.github.linagora.linid.im.corelib.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.linagora.linid.im.corelib.i18n.I18nMessage;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test class: ApiException")
class ApiExceptionTest {
  @Test
  @DisplayName("Constructor with status code and error message")
  void testConstructor_withStatusAndError() {
    var msg = I18nMessage.of("error.key");
    ApiException ex = new ApiException(404, msg);

    assertEquals(404, ex.getStatusCode());
    assertEquals(msg, ex.getError());
    assertTrue(ex.isNeedToBeLogged());
    assertTrue(ex.getDetails().isEmpty());
    assertNull(ex.getCause());
    assertEquals("error.key", ex.getMessage());
  }

  @Test
  @DisplayName("Constructor with status code, error message and details")
  void testConstructor_withStatusErrorAndDetails() {
    var msg = I18nMessage.of("error.key");
    Map<String, Object> details = new HashMap<>();
    details.put("field", "value");
    ApiException ex = new ApiException(400, msg, details);

    assertEquals(400, ex.getStatusCode());
    assertEquals(msg, ex.getError());
    assertTrue(ex.isNeedToBeLogged());
    assertEquals("value", ex.getDetails().get("field"));
    assertNull(ex.getCause());
  }

  @Test
  @DisplayName("Constructor with status code, error message and needToBeLogged flag")
  void testConstructor_withStatusErrorAndNeedToBeLogged() {
    var msg = I18nMessage.of("error.key");
    ApiException ex = new ApiException(500, msg, false);

    assertEquals(500, ex.getStatusCode());
    assertEquals(msg, ex.getError());
    assertFalse(ex.isNeedToBeLogged());
    assertTrue(ex.getDetails().isEmpty());
    assertNull(ex.getCause());
  }

  @Test
  @DisplayName("Constructor with status code, error message, details and needToBeLogged flag")
  void testConstructor_withStatusErrorDetailsAndNeedToBeLogged() {
    var msg = I18nMessage.of("error.key");
    Map<String, Object> details = new HashMap<>();
    details.put("info", "detail");
    ApiException ex = new ApiException(403, msg, details, false);

    assertEquals(403, ex.getStatusCode());
    assertEquals(msg, ex.getError());
    assertFalse(ex.isNeedToBeLogged());
    assertEquals("detail", ex.getDetails().get("info"));
    assertNull(ex.getCause());
  }

  @Test
  @DisplayName("Constructor with status code, error message and cause")
  void testConstructor_withStatusErrorAndCause() {
    var msg = I18nMessage.of("error.key");
    Throwable cause = new RuntimeException("cause");
    ApiException ex = new ApiException(501, msg, cause);

    assertEquals(501, ex.getStatusCode());
    assertEquals(msg, ex.getError());
    assertTrue(ex.isNeedToBeLogged());
    assertTrue(ex.getDetails().isEmpty());
    assertEquals(cause, ex.getCause());
  }

  @Test
  @DisplayName("Constructor with status code, error message, details and cause")
  void testConstructor_withStatusErrorDetailsAndCause() {
    var msg = I18nMessage.of("error.key");
    Map<String, Object> details = new HashMap<>();
    details.put("key", "val");
    Throwable cause = new RuntimeException("cause");
    ApiException ex = new ApiException(502, msg, details, cause);

    assertEquals(502, ex.getStatusCode());
    assertEquals(msg, ex.getError());
    assertTrue(ex.isNeedToBeLogged());
    assertEquals("val", ex.getDetails().get("key"));
    assertEquals(cause, ex.getCause());
  }

  @Test
  @DisplayName("Constructor with status code, error message, cause and needToBeLogged flag")
  void testConstructor_withStatusErrorCauseAndNeedToBeLogged() {
    var msg = I18nMessage.of("error.key");
    Throwable cause = new RuntimeException("cause");
    ApiException ex = new ApiException(503, msg, cause, false);

    assertEquals(503, ex.getStatusCode());
    assertEquals(msg, ex.getError());
    assertFalse(ex.isNeedToBeLogged());
    assertTrue(ex.getDetails().isEmpty());
    assertEquals(cause, ex.getCause());
  }

  @Test
  @DisplayName("Constructor with all parameters")
  void testConstructor_allParams() {
    var msg = I18nMessage.of("error.key");
    Map<String, Object> details = new HashMap<>();
    details.put("data", "value");
    Throwable cause = new RuntimeException("cause");
    ApiException ex = new ApiException(504, msg, details, cause, false);

    assertEquals(504, ex.getStatusCode());
    assertEquals(msg, ex.getError());
    assertFalse(ex.isNeedToBeLogged());
    assertEquals("value", ex.getDetails().get("data"));
    assertEquals(cause, ex.getCause());
  }
}
