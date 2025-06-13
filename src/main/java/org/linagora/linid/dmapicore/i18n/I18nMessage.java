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

import java.util.HashMap;
import java.util.Map;

/**
 * Immutable class representing an internationalized message with an associated key and an optional
 * context map for parameter substitution or additional information.
 *
 * <p>Instances of this class are typically used to identify messages in resource bundles or
 * translation files and optionally carry dynamic parameters for message formatting.
 */
public final class I18nMessage {

  /** The key identifying the message in the resource bundle or translation system. */
  private final String key;

  /**
   * A map containing context data for the message, such as parameters to be substituted into the
   * message template.
   */
  private final Map<String, Object> context;

  /**
   * Private constructor to enforce use of static factory methods.
   *
   * @param key the message key
   * @param context the context map (copied defensively)
   */
  private I18nMessage(final String key, final Map<String, Object> context) {
    this.key = key;
    this.context = context;
  }

  /**
   * Creates an {@code I18nMessage} instance with the given key and an empty context.
   *
   * @param key the message key
   * @return a new {@code I18nMessage} with an empty context
   */
  public static I18nMessage of(final String key) {
    return new I18nMessage(key, new HashMap<>());
  }

  /**
   * Creates an {@code I18nMessage} instance with the given key and context. A defensive copy of the
   * context map is made to preserve immutability.
   *
   * @param key the message key
   * @param context the context map with parameters for the message
   * @return a new {@code I18nMessage} containing the provided context
   */
  public static I18nMessage of(final String key, final Map<String, Object> context) {
    return new I18nMessage(key, new HashMap<>(context));
  }

  /**
   * Returns the key of this internationalized message.
   *
   * @return the message key
   */
  public String key() {
    return key;
  }

  /**
   * Returns the context map associated with this message. The map contains parameters or additional
   * information for the message.
   *
   * @return the context map (never null)
   */
  public Map<String, Object> context() {
    return context;
  }
}
