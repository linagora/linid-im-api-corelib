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

package org.linagora.linid.dmapicore.plugin.entity;

import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

/**
 * Service interface responsible for handling CRUD operations on dynamic entities.
 *
 * <p>This interface abstracts operations over entities whose structure is defined dynamically at
 * runtime based on route and external configuration.
 */
public interface DynamicEntityService {

  /**
   * Handles the creation of a dynamic entity for the given route.
   *
   * @param route the route name identifying the entity type
   * @param body the entity data to create
   * @return the created {@link DynamicEntity}
   */
  DynamicEntity handleCreate(String route, Map<String, Object> body);

  /**
   * Handles the full update (replace) of an existing dynamic entity by ID.
   *
   * @param route the route name identifying the entity type
   * @param id the identifier of the entity to update
   * @param body the new data to replace the existing entity
   * @return the updated {@link DynamicEntity}
   */
  DynamicEntity handleUpdate(String route, String id, Map<String, Object> body);

  /**
   * Handles a partial update of an existing dynamic entity by ID.
   *
   * @param route the route name identifying the entity type
   * @param id the identifier of the entity to patch
   * @param body the partial data to apply
   * @return the patched {@link DynamicEntity}
   */
  DynamicEntity handlePatch(String route, String id, Map<String, Object> body);

  /**
   * Handles the deletion of an existing dynamic entity by ID.
   *
   * @param route the route name identifying the entity type
   * @param id the identifier of the entity to delete
   * @return {@code true} if the entity was successfully deleted, {@code false} otherwise
   */
  boolean handleDelete(String route, String id);

  /**
   * Retrieves a dynamic entity by its ID for the given route.
   *
   * @param route the route name identifying the entity type
   * @param id the identifier of the entity to retrieve
   * @return the found {@link DynamicEntity}, or {@code null} if not found
   */
  DynamicEntity handleFindById(String route, String id);

  /**
   * Retrieves a paginated list of dynamic entities for the given route, applying any filter and
   * pagination parameters provided.
   *
   * @param route the route name identifying the entity type
   * @param filters query parameters to filter results
   * @param pageable the pagination and sorting information
   * @return a {@link Page} of {@link DynamicEntity} instances
   */
  Page<DynamicEntity> handleFindAll(
      String route, MultiValueMap<String, String> filters, Pageable pageable);
}
