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
import java.util.Optional;
import org.linagora.linid.dmapicore.plugin.config.dto.AttributeConfiguration;
import org.linagora.linid.dmapicore.plugin.config.dto.EntityConfiguration;

/**
 * Represents a dynamic entity with its attributes and associated configuration.
 *
 * <p>A dynamic entity is defined at runtime based on an {@link EntityConfiguration}, and its data
 * is held as a flexible map of key-value attribute pairs.
 */
public class DynamicEntity {

  /**
   * A map representing the actual values of the entity's attributes. The keys are attribute names,
   * and the values are their corresponding data.
   */
  private Map<String, Object> attributes;

  /** The configuration object defining the structure and metadata of the entity. */
  private EntityConfiguration configuration;

  /** Default constructor. */
  public DynamicEntity() {}

  /**
   * Returns the map of attribute values for this entity.
   *
   * @return a {@link Map} containing attribute names and their values
   */
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  /**
   * Sets the map of attribute values for this entity.
   *
   * @param attributes the map of attribute values to assign
   */
  public void setAttributes(final Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  /**
   * Returns the configuration object associated with this dynamic entity.
   *
   * @return the {@link EntityConfiguration} instance
   */
  public EntityConfiguration getConfiguration() {
    return configuration;
  }

  /**
   * Sets the configuration object associated with this dynamic entity.
   *
   * @param configuration the {@link EntityConfiguration} to assign
   */
  public void setConfiguration(final EntityConfiguration configuration) {
    this.configuration = configuration;
  }

  /**
   * Retrieves the attribute configuration for a given attribute name, if present.
   *
   * @param attributeName the name of the attribute to look for
   * @return an {@link Optional} containing the matching {@link AttributeConfiguration}, or empty if
   *     none matches
   */
  public Optional<AttributeConfiguration> getAttributeConfiguration(final String attributeName) {
    return this.getConfiguration().getAttributes().stream()
        .filter(config -> config.getName().equals(attributeName))
        .findAny();
  }
}
