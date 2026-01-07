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

package io.github.linagora.linid.im.corelib.plugin.config.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration class representing an attribute with its name, type, validations, and additional arbitrary access
 * properties.
 *
 * <p>The {@code access} map collects any extra properties not explicitly mapped to fields, allowing
 * flexible extension of attribute configuration.
 */
public class AttributeConfiguration {

  /**
   * Map holding additional configuration properties used by providers to access or handle this attribute, beyond the
   * predefined fields.
   */
  private Map<String, Object> access = new HashMap<>();

  /**
   * The name of the attribute.
   */
  private String name;

  /**
   * The type of the attribute (e.g., "string", "integer").
   */
  private String type;

  /**
   * If set to {@code true}, empty strings will be treated as {@code null}. This can be useful to distinguish between
   * empty and absent values during processing.
   */
  private boolean nullIfEmpty;

  /**
   * List of validation configurations associated with this attribute.
   */
  private List<ValidationConfiguration> validations;

  /**
   * Indicates whether the attribute is required.
   */
  private boolean required;

  /**
   * Specifies the type of front-end input to use for this attribute.
   *
   * <p>This value guides the front-end on how to render the attribute.
   */
  private String input;

  /**
   * Additional settings to configure the behavior and appearance of the input component.
   *
   * <p>This map allows specifying arbitrary key-value pairs to fine-tune input rendering.
   */
  private Map<String, Object> inputSettings;

  /**
   * Default constructor.
   */
  public AttributeConfiguration() {
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
   * Returns the map of additional configuration properties used by providers to access or process this attribute,
   * beyond the predefined fields.
   *
   * <p>This map contains supplementary provider-specific settings deserialized from JSON and stored
   * in the {@code access} field.
   *
   * @return an unmodifiable view of the additional access configuration properties
   */
  public Map<String, Object> getAccess() {
    return access;
  }

  /**
   * Sets the map of additional provider-specific configuration properties used to access or handle this attribute.
   *
   * <p>This map contains arbitrary key-value pairs defined under the {@code access}
   * section in the configuration, and can be used by plugin providers to pass custom settings for the attribute (e.g.,
   * database column mappings, LDAP field names, etc.).
   *
   * @param access the map of additional access properties to associate with this attribute
   */
  public void setAccess(final Map<String, Object> access) {
    this.access = access;
  }

  /**
   * Indicates whether the attribute is required.
   *
   * <p>
   * This flag determines if a value must be provided for the attribute. It can be used by front-end clients or
   * validation logic to enforce mandatory fields.
   *
   * @return {@code true} if the attribute is required; {@code false} otherwise
   */
  public boolean getRequired() {
    return required;
  }

  /**
   * Sets whether the attribute is required.
   *
   * @param required {@code true} to mark the attribute as mandatory; {@code false} otherwise
   */
  public void setRequired(boolean required) {
    this.required = required;
  }

  /**
   * Specifies the front-end input type to use for this attribute.
   *
   * @return the input type to be used by the front-end
   */
  public String getInput() {
    return input;
  }

  /**
   * Sets the input type used by the front-end for this attribute.
   *
   * @param input the input type
   */
  public void setInput(String input) {
    this.input = input;
  }

  /**
   * Returns the map of additional settings used to configure the input component on the front-end.
   *
   * @return a map of input-specific settings
   */
  public Map<String, Object> getInputSettings() {
    return inputSettings;
  }

  /**
   * Sets the additional front-end settings for the input component.
   *
   * @param inputSettings a map of input-specific settings
   */
  public void setInputSettings(Map<String, Object> inputSettings) {
    this.inputSettings = inputSettings;
  }

  /**
   * Returns whether empty strings are treated as {@code null}.
   *
   * @return {@code true} if empty strings should be considered {@code null}, {@code false} otherwise
   */
  public boolean isNullIfEmpty() {
    return nullIfEmpty;
  }

  /**
   * Sets whether empty strings should be treated as {@code null}.
   *
   * @param nullIfEmpty {@code true} to treat empty strings as {@code null}, {@code false} otherwise
   */
  public void setNullIfEmpty(boolean nullIfEmpty) {
    this.nullIfEmpty = nullIfEmpty;
  }
}
