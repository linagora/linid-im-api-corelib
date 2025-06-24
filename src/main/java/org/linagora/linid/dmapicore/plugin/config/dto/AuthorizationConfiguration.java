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

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the configuration of an authorization provider plugin.
 *
 * <p>This class supports dynamic and extensible configuration by allowing any additional
 * provider-specific options to be added via JSON/YAML deserialization. The configuration includes a required {@code type} field
 * identifying the plugin, and a map of arbitrary key-value pairs for plugin-specific settings.
 *
 * <p>The dynamic properties are collected into the {@code options} map using Jackson's
 * {@link JsonAnySetter} annotation.
 */
public class AuthorizationConfiguration implements PluginConfiguration {

  /**
   * A map of arbitrary provider-specific configuration options.
   *
   * <p>This allows for plugin extensions without modifying the static structure of this class.
   * These entries are populated at deserialization time via {@link #addOption(String, Object)}, except for the reserved
   * {@code type} field.
   */
  private final Map<String, Object> options = new HashMap<>();
  /**
   * The type of the authorization provider plugin.
   *
   * <p>This value is used to resolve the appropriate plugin implementation (e.g., "ldap", "api").
   */
  private String type;

  /**
   * Default constructor.
   */
  public AuthorizationConfiguration() {
  }

  /**
   * Dynamically adds a plugin-specific configuration entry during deserialization.
   *
   * <p>This method ignores the reserved key {@code "type"}.
   *
   * @param key the name of the configuration option
   * @param value the value of the configuration option
   */
  @Override
  @JsonAnySetter
  public void addOption(final String key, final Object value) {
    if (!"type".equals(key)) {
      this.options.put(key, value);
    }
  }

  /**
   * Returns the type of provider plugin this configuration targets.
   *
   * @return the provider type identifier
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type of provider plugin this configuration targets.
   *
   * @param type the provider type identifier to set
   */
  public void setType(final String type) {
    this.type = type;
  }

  /**
   * Returns the map of dynamically configured plugin-specific options.
   *
   * @return the configuration options map
   */
  @Override
  public Map<String, Object> getOptions() {
    return options;
  }
}
