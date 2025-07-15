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

package io.github.linagora.linid.im.corelib.plugin.entity;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

/**
 * Service interface for performing CRUD operations on dynamically defined entities.
 *
 * <p>Entities are defined at runtime based on route-specific configuration. This interface abstracts
 * the logic required to create, update, delete, and query such entities in a generic way, allowing the system to operate on
 * flexible schemas without requiring static definitions.
 */
public interface DynamicEntityService {

  /**
   * Creates a new dynamic entity for the specified route.
   *
   * @param request the HTTP request context
   * @param entity the name of the dynamic entity type (defined by route)
   * @param body the entity attributes to persist
   * @return the newly created {@link DynamicEntity}
   */
  DynamicEntity handleCreate(HttpServletRequest request, String entity, Map<String, Object> body);

  /**
   * Fully replaces an existing dynamic entity identified by its ID.
   *
   * @param request the HTTP request context
   * @param entity the name of the dynamic entity type
   * @param id the unique identifier of the entity to update
   * @param body the complete set of attributes to replace the entity with
   * @return the updated {@link DynamicEntity}
   */
  DynamicEntity handleUpdate(HttpServletRequest request, String entity, String id, Map<String, Object> body);

  /**
   * Partially updates an existing dynamic entity identified by its ID.
   *
   * @param request the HTTP request context
   * @param entity the name of the dynamic entity type
   * @param id the unique identifier of the entity to patch
   * @param body a map of attributes to update
   * @return the patched {@link DynamicEntity}
   */
  DynamicEntity handlePatch(HttpServletRequest request, String entity, String id, Map<String, Object> body);

  /**
   * Deletes an existing dynamic entity by its ID.
   *
   * @param request the HTTP request context
   * @param entity the name of the dynamic entity type
   * @param id the unique identifier of the entity to delete
   * @return {@code true} if the entity was deleted, {@code false} if it was not found or could not be deleted
   */
  boolean handleDelete(HttpServletRequest request, String entity, String id);

  /**
   * Retrieves a dynamic entity by its ID.
   *
   * @param request the HTTP request context
   * @param entity the name of the dynamic entity type
   * @param id the unique identifier of the entity
   * @return the matching {@link DynamicEntity}, or {@code null} if not found
   */
  DynamicEntity handleFindById(HttpServletRequest request, String entity, String id);

  /**
   * Retrieves a paginated list of dynamic entities matching the provided filters.
   *
   * @param request the HTTP request context
   * @param entity the name of the dynamic entity type
   * @param filters a map of query parameters used to filter the results
   * @param pageable pagination and sorting options
   * @return a {@link Page} of matching {@link DynamicEntity} instances
   */
  Page<DynamicEntity> handleFindAll(HttpServletRequest request,
                                    String entity,
                                    MultiValueMap<String, String> filters,
                                    Pageable pageable);
}
