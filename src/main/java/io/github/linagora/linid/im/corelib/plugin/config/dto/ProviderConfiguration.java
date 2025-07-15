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

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a configuration instance of a provider plugin.
 *
 * <p>The <code>type</code> field indicates the kind of provider plugin (e.g., "LDAPProvider") which
 * determines the implementation or the support handled by the plugin.
 *
 * <p>The <code>name</code> field uniquely identifies this specific configuration instance of the
 * given provider type. This enables defining multiple configurations for the same provider type,
 * allowing, for example, connections to different servers or data sources using the same underlying
 * provider plugin implementation.
 *
 * <p>For example, with a provider plugin "LDAPProvider", you could have two configurations named
 * "ldap1" and "ldap2", each targeting different LDAP directories. The <code>name</code> is used in
 * entity configurations to reference which provider configuration to use.
 *
 * <p>The <code>options</code> map holds additional arbitrary configuration properties that are not
 * explicitly declared as fields. These properties can be added dynamically via JSON deserialization
 * using the {@link JsonAnySetter} annotated method {@link #addOption(String, Object)}.
 */
public class ProviderConfiguration implements PluginConfiguration {

  /**
   * A map holding additional provider-specific configuration options that are not explicitly
   * declared as fields. Populated dynamically during JSON deserialization via {@link
   * #addOption(String, Object)}.
   */
  private final Map<String, Object> options = new HashMap<>();

  /**
   * The name identifier of this provider configuration instance. This allows multiple
   * configurations of the same provider type to coexist.
   */
  private String name;

  /**
   * The type of provider plugin this configuration targets. Typically corresponds to the plugin
   * implementation identifier, such as "LDAPProvider".
   */
  private String type;

  /** Default constructor. */
  public ProviderConfiguration() {}

  @Override
  @JsonAnySetter
  public void addOption(final String key, final Object value) {
    if (!"name".equals(key) &&  !"type".equals(key)) {
      this.options.put(key, value);
    }
  }

  /**
   * Returns the name of this provider configuration instance.
   *
   * @return the configuration instance name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of this provider configuration instance.
   *
   * @param name the name to set
   */
  public void setName(final String name) {
    this.name = name;
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
   * Returns the map of additional provider-specific options.
   *
   * @return the options map
   */
  @Override
  public Map<String, Object> getOptions() {
    return options;
  }
}
