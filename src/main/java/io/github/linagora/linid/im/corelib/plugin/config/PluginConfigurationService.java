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

package io.github.linagora.linid.im.corelib.plugin.config;

import io.github.linagora.linid.im.corelib.plugin.config.dto.AuthorizationConfiguration;
import io.github.linagora.linid.im.corelib.plugin.config.dto.EntityConfiguration;
import io.github.linagora.linid.im.corelib.plugin.config.dto.ProviderConfiguration;
import io.github.linagora.linid.im.corelib.plugin.config.dto.RouteConfiguration;
import io.github.linagora.linid.im.corelib.plugin.config.dto.TaskConfiguration;
import io.github.linagora.linid.im.corelib.plugin.config.dto.ValidationConfiguration;
import io.github.linagora.linid.im.corelib.plugin.entity.EntityDescription;
import io.github.linagora.linid.im.corelib.plugin.route.RouteDescription;
import java.util.List;
import java.util.Optional;

/**
 * Service interface to access plugin-related configurations.
 *
 * <p>Provides methods to retrieve configurations for entities, providers, and routes by name.
 */
public interface PluginConfigurationService {
  /**
   * Retrieves the configuration for the entity identified by the given name.
   *
   * @param name the name of the entity configuration to retrieve
   * @return an {@link Optional} containing the {@link EntityConfiguration} if found, or {@link Optional#empty()} if no
   *     configuration exists for the given name
   */
  Optional<EntityConfiguration> getEntityConfiguration(String name);

  /**
   * Retrieves the configuration for the provider identified by the given name.
   *
   * @param name the name of the provider configuration to retrieve
   * @return an {@link Optional} containing the {@link ProviderConfiguration} if found, or {@link Optional#empty()} if no
   *     configuration exists for the given name
   */
  Optional<ProviderConfiguration> getProviderConfiguration(String name);

  /**
   * Retrieves the list of configured routes.
   *
   * @return a list of {@link RouteConfiguration} objects. The list may be empty if no routes are configured, but is never
   *     {@code null}.
   */
  List<RouteConfiguration> getRoutesConfiguration();

  /**
   * Retrieves the configuration for the task identified by the given name.
   *
   * @param name the name of the task configuration to retrieve
   * @return an {@link Optional} containing the {@link TaskConfiguration} if found, or {@link Optional#empty()} if no
   *     configuration exists for the given name
   */
  Optional<TaskConfiguration> getTaskConfiguration(String name);

  /**
   * Retrieves the configuration for the validation identified by the given name.
   *
   * @param name the name of the validation configuration to retrieve
   * @return an {@link Optional} containing the {@link ValidationConfiguration} if found, or {@link Optional#empty()} if no
   *     configuration exists for the given name
   */
  Optional<ValidationConfiguration> getValidationConfiguration(String name);

  /**
   * Retrieves all route descriptions available in the application.
   *
   * @return a list of {@link RouteDescription} representing all HTTP routes.
   */
  List<RouteDescription> getRouteDescriptions();

  /**
   * Retrieves all entity descriptions configured in the application.
   *
   * @return a list of {@link EntityDescription} representing all entities.
   */
  List<EntityDescription> getEntityDescriptions();

  /**
   * Retrieves the description of a single entity by its name.
   *
   * @param name the name of the entity to retrieve
   * @return an {@link Optional} containing the entity description if found, otherwise empty
   */
  Optional<EntityDescription> getEntityDescription(String name);

  /**
   * Retrieves the global authorization configuration used for access control across entities or attributes.
   *
   * <p>This configuration may define global access policies or plugin-specific authorization logic.
   *
   * @return an {@link Optional} containing the {@link AuthorizationConfiguration} if defined, otherwise empty
   */
  Optional<AuthorizationConfiguration> getAuthorizationConfiguration();
}
