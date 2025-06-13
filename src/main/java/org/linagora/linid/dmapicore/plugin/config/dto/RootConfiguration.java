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
import java.util.List;

/**
 * Represents the root configuration for the plugin system.
 *
 * <p>This configuration aggregates the main components of the system including entities, providers,
 * routes, and tasks. Each list corresponds to a specific type of configuration used by the plugin
 * framework.
 */
public class RootConfiguration {

  /**
   * List of entity configurations. Each {@link EntityConfiguration} defines the structure and
   * behavior of an entity.
   */
  @JsonProperty("entities")
  private List<EntityConfiguration> entities;

  /**
   * List of provider configurations. Each {@link ProviderConfiguration} defines the settings for a
   * data provider.
   */
  @JsonProperty("providers")
  private List<ProviderConfiguration> providers;

  /**
   * List of route configurations. Each {@link RouteConfiguration} defines routing information for
   * entities or services.
   */
  @JsonProperty("routes")
  private List<RouteConfiguration> routes;

  /**
   * List of task configurations. Each {@link TaskConfiguration} defines background or scheduled
   * tasks within the system.
   */
  @JsonProperty("tasks")
  private List<TaskConfiguration> tasks;

  /**
   * List of validation configurations. Each {@link ValidationConfiguration} defines the settings for a
   * validator.
   */
  @JsonProperty("validations")
  private List<ValidationConfiguration> validations;

  /** Default constructor. */
  public RootConfiguration() {}

  /**
   * Returns the list of entity configurations.
   *
   * @return the entities list
   */
  public List<EntityConfiguration> getEntities() {
    return entities;
  }

  /**
   * Sets the list of entity configurations.
   *
   * @param entities the list of entities to set
   */
  public void setEntities(final List<EntityConfiguration> entities) {
    this.entities = entities;
  }

  /**
   * Returns the list of provider configurations.
   *
   * @return the providers list
   */
  public List<ProviderConfiguration> getProviders() {
    return providers;
  }

  /**
   * Sets the list of provider configurations.
   *
   * @param providers the list of providers to set
   */
  public void setProviders(final List<ProviderConfiguration> providers) {
    this.providers = providers;
  }

  /**
   * Returns the list of route configurations.
   *
   * @return the routes list
   */
  public List<RouteConfiguration> getRoutes() {
    return routes;
  }

  /**
   * Sets the list of route configurations.
   *
   * @param routes the list of routes to set
   */
  public void setRoutes(final List<RouteConfiguration> routes) {
    this.routes = routes;
  }

  /**
   * Returns the list of task configurations.
   *
   * @return the tasks list
   */
  public List<TaskConfiguration> getTasks() {
    return tasks;
  }

  /**
   * Sets the list of task configurations.
   *
   * @param tasks the list of tasks to set
   */
  public void setTasks(final List<TaskConfiguration> tasks) {
    this.tasks = tasks;
  }

  /**
   * Returns the list of validation configurations.
   *
   * @return the validations list
   */
  public List<ValidationConfiguration> getValidations() {
    return validations;
  }

  /**
   * Sets the list of validation configurations.
   *
   * @param validations the list of validations to set
   */
  public void setValidations(final List<ValidationConfiguration> validations) {
    this.validations = validations;
  }

}
