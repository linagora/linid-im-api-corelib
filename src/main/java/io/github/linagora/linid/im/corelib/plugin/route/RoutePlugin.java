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

package io.github.linagora.linid.im.corelib.plugin.route;

import io.github.linagora.linid.im.corelib.plugin.config.dto.EntityConfiguration;
import io.github.linagora.linid.im.corelib.plugin.config.dto.RouteConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.plugin.core.Plugin;

/**
 * Interface defining a plugin for routing HTTP requests.
 *
 * <p>Implementations decide if they match a given URL and HTTP method, and execute the
 * corresponding logic for the matched request.
 */
public interface RoutePlugin extends Plugin<String> {

  /**
   * Returns the current configuration of the route.
   *
   * @return the route configuration
   */
  RouteConfiguration getConfiguration();

  /**
   * Sets the configuration of the route.
   *
   * @param configuration the route configuration to set
   */
  void setConfiguration(RouteConfiguration configuration);

  /**
   * Returns the list of route descriptions defined in the application, potentially derived from the provided list of entity
   * configurations.
   *
   * @param entities the list of entity configurations used to generate routes
   * @return list of all route descriptions (HTTP method, path, variables)
   */
  List<RouteDescription> getRoutes(List<EntityConfiguration> entities);

  /**
   * Determines if this plugin matches the given URL and HTTP method.
   *
   * @param url the URL of the incoming request
   * @param method the HTTP method (GET, POST, etc.) of the incoming request
   * @return {@code true} if the plugin matches the request, {@code false} otherwise
   */
  boolean match(String url, String method);

  /**
   * Executes the logic associated with the given HTTP request.
   *
   * @param request the HTTP servlet request to be processed
   * @return a {@link ResponseEntity} representing the response of the execution
   */
  ResponseEntity<?> execute(HttpServletRequest request);
}
