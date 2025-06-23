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

package org.linagora.linid.dmapicore.i18n;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test class: I18nMessage")
class I18nMessageTest {

  @Test
  @DisplayName("Create I18nMessage with key only")
  void testOf_withKeyOnly() {
    String key = "message.key";
    I18nMessage msg = I18nMessage.of(key);

    assertEquals(key, msg.key());
    assertNotNull(msg.context());
    assertTrue(msg.context().isEmpty());
  }

  @Test
  @DisplayName("Create I18nMessage with key and context")
  void testOf_withKeyAndContext() {
    String key = "message.key";
    Map<String, Object> context = new HashMap<>();
    context.put("param1", "value1");
    context.put("param2", 123);

    I18nMessage msg = I18nMessage.of(key, context);

    assertEquals(key, msg.key());
    assertNotNull(msg.context());
    assertEquals(2, msg.context().size());
    assertEquals("value1", msg.context().get("param1"));
    assertEquals(123, msg.context().get("param2"));

    // Verify that the context map inside I18nMessage is a defensive copy
    context.put("param3", "value3");
    assertFalse(msg.context().containsKey("param3"));
  }
}
