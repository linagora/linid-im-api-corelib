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

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Configuration class representing an entity in the plugin system.
 *
 * <p>An entity is defined by its name, the provider it belongs to, an optional route name, a list
 * of attribute configurations, a list of task configurations, and additional provider-specific
 * access configuration.
 */
public class EntityConfiguration {
  /** The unique name of the entity. */
  private String name;

  /** The name of the provider associated with this entity. */
  private String provider;

  /** The route name associated with this entity. If not set, it defaults to the entity's name. */
  private String route;

  /** The list of attribute configurations for this entity. */
  @JsonProperty("attributes")
  private List<AttributeConfiguration> attributes;

  /** The list of task configurations for this entity. */
  @JsonProperty("tasks")
  private List<TaskConfiguration> tasks;

  /**
   * Additional provider-specific configuration properties to access or handle this entity beyond
   * predefined fields.
   */
  private Map<String, Object> access = new HashMap<>();

  /** Default constructor. */
  public EntityConfiguration() {}

  /**
   * Returns the unique name of the entity.
   *
   * @return the entity name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the unique name of the entity.
   *
   * @param name the entity name to set
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Returns the provider name associated with this entity.
   *
   * @return the provider name
   */
  public String getProvider() {
    return provider;
  }

  /**
   * Sets the provider name associated with this entity.
   *
   * @param provider the provider name to set
   */
  public void setProvider(final String provider) {
    this.provider = provider;
  }

  /**
   * Returns the route name of the entity. If not set, returns the entity name as the default route.
   *
   * @return the route name or entity name if route is null
   */
  public String getRoute() {
    return Optional.ofNullable(route).orElse(this.name);
  }

  /**
   * Sets the route name of the entity.
   *
   * @param route the route name to set
   */
  public void setRoute(final String route) {
    this.route = route;
  }

  /**
   * Returns the list of attribute configurations.
   *
   * @return the attribute configurations list
   */
  public List<AttributeConfiguration> getAttributes() {
    return attributes;
  }

  /**
   * Sets the list of attribute configurations.
   *
   * @param attributes the attribute configurations to set
   */
  public void setAttributes(final List<AttributeConfiguration> attributes) {
    this.attributes = attributes;
  }

  /**
   * Returns the list of task configurations.
   *
   * @return the task configurations list
   */
  public List<TaskConfiguration> getTasks() {
    return tasks;
  }

  /**
   * Sets the list of task configurations.
   *
   * @param tasks the task configurations to set
   */
  public void setTasks(final List<TaskConfiguration> tasks) {
    this.tasks = tasks;
  }

  /**
   * Returns additional provider-specific access configuration properties.
   *
   * @return the map of access configuration properties
   */
  public Map<String, Object> getAccess() {
    return access;
  }

  /**
   * Sets additional provider-specific access configuration properties.
   *
   * @param access the map of access configuration properties to set
   */
  public void setAccess(final Map<String, Object> access) {
    this.access = access;
  }
}
