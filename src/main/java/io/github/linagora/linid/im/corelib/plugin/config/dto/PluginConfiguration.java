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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Optional;

/**
 * Represents a configuration holder for a plugin.
 *
 * <p>This interface allows storing and retrieving plugin-specific configuration options using a
 * key-value mechanism. It provides type-safe access to individual options.
 */
public interface PluginConfiguration {
  /**
   * Adds a configuration option to the plugin.
   *
   * @param key the name of the option (must not be null)
   * @param value the value of the option (can be any object)
   */
  void addOption(String key, Object value);

  /**
   * Returns all configuration options as a map.
   *
   * @return a map containing all the options added to this configuration
   */
  Map<String, Object> getOptions();

  /**
   * Retrieves the value of an option by its key and attempts to cast it to a {@link String}.
   *
   * <p>This method is a convenience shortcut for accessing simple string-based options from the
   * plugin configuration.
   *
   * @param key the name of the configuration option to retrieve
   * @return an {@link Optional} containing the value cast to {@link String} if present and
   *     non-null, or {@link Optional#empty()} if the key is absent or the value is {@code null}
   * @throws ClassCastException if the value is present but not of type {@link String}
   */
  default Optional<String> getOption(String key) {
    Object value = this.getOptions().get(key);

    if (value == null) {
      return Optional.empty();
    }

    return Optional.of((String) value);
  }

  /**
   * Retrieves an option by key and attempts to cast it to the specified type.
   *
   * @param key the name of the option
   * @param type the expected type of the option's value
   * @param <T> the generic type parameter
   * @return an {@link Optional} containing the value if it exists and is of the correct type;
   *     otherwise, an empty {@link Optional}
   */
  default <T> Optional<T> getOption(String key, Class<T> type) {
    Object value = this.getOptions().get(key);

    if (value == null) {
      return Optional.empty();
    }

    if (type.isInstance(value)) {
      return Optional.of((T) value);
    }

    return Optional.empty();
  }

  /**
   * Retrieves an option by key and attempts to deserialize it using a {@link TypeReference}.
   *
   * @param key the name of the option
   * @param typeRef the Jackson type reference
   * @param <T> the expected type
   * @return an {@link Optional} containing the value cast/deserialized to the given type
   */
  default <T> Optional<T> getOption(String key, TypeReference<T> typeRef) {
    Object value = this.getOptions().get(key);

    if (value == null) {
      return Optional.empty();
    }

    ObjectMapper mapper = new ObjectMapper();
    try {
      T converted = mapper.convertValue(value, typeRef);
      return Optional.ofNullable(converted);
    } catch (IllegalArgumentException e) {
      return Optional.empty();
    }
  }
}
