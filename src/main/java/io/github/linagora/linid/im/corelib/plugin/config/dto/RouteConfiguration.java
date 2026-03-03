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

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the configuration of a route, identified by a {@code type} and supporting additional
 * arbitrary options.
 *
 * <p>The {@code type} field acts as a discriminator to associate this route configuration with the
 * appropriate {@code RoutePlugin} implementation.
 *
 * <p>The {@code options} map allows storing route-specific configuration parameters that are not
 * explicitly defined as class fields. This enables flexible extension of route behavior depending
 * on the plugin or system context.
 *
 * <p>The {@link #addOption(String, Object)} method is used to populate this map during JSON
 * deserialization, ignoring reserved keywords such as {@code "type"}.
 */
public class RouteConfiguration implements PluginConfiguration {

  /**
   * A map of additional configuration options specific to the route. Populated dynamically via
   * {@link JsonAnySetter} during deserialization.
   */
  private final Map<String, Object> options = new HashMap<>();

  /**
   * The plugin type discriminator for the route. Used to match this configuration with the
   * appropriate {@code RoutePlugin} implementation.
   */
  private String type;

  /** Default constructor. */
  public RouteConfiguration() {}

  @Override
  @JsonAnySetter
  public void addOption(final String key, final Object value) {
    if (!"type".equals(key)) {
      this.options.put(key, value);
    }
  }

  /**
   * Returns the plugin type discriminator for the route.
   *
   * @return the route's type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the plugin type discriminator for the route.
   *
   * @param type the route type to set
   */
  public void setType(final String type) {
    this.type = type;
  }

  /**
   * Returns the map of additional options for the route.
   *
   * @return a map containing all additional configuration options
   */
  @Override
  public Map<String, Object> getOptions() {
    return options;
  }
}
