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

package io.github.linagora.linid.im.corelib.plugin.authorization;

import io.github.linagora.linid.im.corelib.plugin.config.dto.AuthorizationConfiguration;
import io.github.linagora.linid.im.corelib.plugin.config.dto.RootConfiguration;
import io.github.linagora.linid.im.corelib.plugin.entity.DynamicEntity;
import io.github.linagora.linid.im.corelib.plugin.task.TaskExecutionContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.plugin.core.Plugin;
import org.springframework.util.MultiValueMap;

/**
 * Defines the contract for an authorization plugin within the plugin-based configuration system.
 *
 * <p>Authorization plugins control access to entities and operations by evaluating the request context,
 * user credentials, and configured access rules. They also contribute to the global configuration by declaring
 * authorization-specific entities or validations.
 *
 * <p>Each implementation can define its own strategy for token validation, per-entity authorization,
 * and policy enforcement based on filters or record identifiers.
 */
public interface AuthorizationPlugin extends Plugin<String> {

  /**
   * Returns the current authorization configuration applied to this plugin.
   *
   * @return the {@link AuthorizationConfiguration} used by this plugin
   */
  AuthorizationConfiguration getConfiguration();

  /**
   * Applies the provided authorization configuration to this plugin.
   *
   * @param configuration the {@link AuthorizationConfiguration} to apply
   */
  void setConfiguration(AuthorizationConfiguration configuration);

  /**
   * Allows this plugin to contribute additional elements to the system-wide configuration.
   *
   * <p>Typically used to declare authorization-specific entities, attributes, or validation steps
   * required for managing permissions and access control.
   *
   * @param configuration the {@link RootConfiguration} to extend or modify
   */
  void updateConfiguration(RootConfiguration configuration);

  /**
   * Validates the authentication token present in the incoming HTTP request.
   *
   * <p>Should throw an exception if the token is missing, invalid, or expired.
   *
   * @param request the HTTP request containing the token
   * @param context the current execution context
   */
  void validateToken(HttpServletRequest request, TaskExecutionContext context);

  /**
   * Checks whether the request is authorized to perform the specified action on the given entity.
   *
   * @param request the HTTP request to evaluate
   * @param entity the entity on which the action is requested
   * @param action the action to authorize (e.g., "read", "update", "delete")
   * @param context the current execution context
   */
  void isAuthorized(HttpServletRequest request, DynamicEntity entity, String action, TaskExecutionContext context);

  /**
   * Checks whether the request is authorized to perform the specified action on a specific record of an entity.
   *
   * @param request the HTTP request to evaluate
   * @param entity the target entity
   * @param id the ID of the specific record
   * @param action the action to authorize
   * @param context the current execution context
   */
  void isAuthorized(HttpServletRequest request, DynamicEntity entity, String id, String action, TaskExecutionContext context);

  /**
   * Checks whether the request is authorized to perform the specified action on a filtered subset of entity data.
   *
   * @param request the HTTP request to evaluate
   * @param entity the target entity
   * @param filters the filter criteria applied to the dataset
   * @param action the action to authorize
   * @param context the current execution context
   */
  void isAuthorized(HttpServletRequest request, DynamicEntity entity, MultiValueMap<String, String> filters,
                    String action, TaskExecutionContext context);
}
