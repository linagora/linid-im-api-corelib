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

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration class representing an attribute with its name, type, validations, and additional
 * arbitrary access properties.
 *
 * <p>The {@code access} map collects any extra properties not explicitly mapped to fields, allowing
 * flexible extension of attribute configuration.
 */
public class AttributeConfiguration {

  /**
   * Map holding additional configuration properties used by providers to access or handle this
   * attribute, beyond the predefined fields.
   *
   * <p>These properties are populated via {@link JsonAnySetter} during JSON deserialization.
   */
  private final Map<String, Object> access = new HashMap<>();

  /** The name of the attribute. */
  private String name;

  /** The type of the attribute (e.g., "string", "integer"). */
  private String type;

  /** List of validation configurations associated with this attribute. */
  private List<ValidationConfiguration> validations;

  /** Default constructor. */
  public AttributeConfiguration() {}

  /**
   * Adds a property to the {@code access} map if the property key is not one of the predefined
   * fields ("name", "type", "validations").
   *
   * @param key the property name
   * @param value the property value
   */
  @JsonAnySetter
  public void addAccess(final String key, final Object value) {
    if (!"name".equals(key) && !"type".equals(key) && !"validations".equals(key)) {
      this.access.put(key, value);
    }
  }

  /**
   * Returns the name of this attribute.
   *
   * @return the attribute name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of this attribute.
   *
   * @param name the attribute name to set
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Returns the type of this attribute.
   *
   * @return the attribute type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type of this attribute.
   *
   * @param type the attribute type to set
   */
  public void setType(final String type) {
    this.type = type;
  }

  /**
   * Returns the list of validation configurations for this attribute.
   *
   * @return the list of {@link ValidationConfiguration} instances, or null if none
   */
  public List<ValidationConfiguration> getValidations() {
    return validations;
  }

  /**
   * Sets the list of validation configurations for this attribute.
   *
   * @param validations the list of validations to set
   */
  public void setValidations(final List<ValidationConfiguration> validations) {
    this.validations = validations;
  }

  /**
   * Returns the map of additional configuration properties used by providers to access or process
   * this attribute, beyond the predefined fields.
   *
   * <p>This map contains supplementary provider-specific settings deserialized from JSON and stored
   * in the {@code access} field.
   *
   * @return an unmodifiable view of the additional access configuration properties
   */
  public Map<String, Object> getAccess() {
    return access;
  }
}
