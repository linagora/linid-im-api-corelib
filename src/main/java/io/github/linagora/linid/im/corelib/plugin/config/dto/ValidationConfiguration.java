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

package io.github.linagora.linid.im.corelib.plugin.config.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the configuration of a validation step associated with an attribute.
 *
 * <p>This class includes the validation {@code type}, the {@code phases} during which it applies,
 * and an {@code options} map for additional, arbitrary configuration parameters. Fields not
 * explicitly declared are added to the {@code options} map during JSON deserialization.
 */
public class ValidationConfiguration implements PluginConfiguration {

  /**
   * Map holding additional arbitrary properties that are not mapped to predefined fields. Populated
   * via {@link JsonAnySetter} during JSON deserialization.
   */
  private final Map<String, Object> options = new HashMap<>();

  /**
   * The name identifier of this validation configuration instance. This allows multiple
   * configurations of the same validation type to coexist.
   */
  private String name;

  /** The type of validation to apply (e.g., "not_null", "regex", "range", etc.). */
  private String type;

  /** The phases in which this validation should be applied (e.g., "create", "update"). */
  private List<String> phases = new ArrayList<>();

  /** Default constructor. */
  public ValidationConfiguration() {}

  /**
   * Returns the name of this validation configuration instance.
   *
   * @return the configuration instance name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of this validation configuration instance.
   *
   * @param name the name to set
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Returns the type of the validation.
   *
   * @return the validation type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type of the validation.
   *
   * @param type the validation type
   */
  public void setType(final String type) {
    this.type = type;
  }

  /**
   * Returns the list of phases in which the validation should be executed.
   *
   * @return the list of validation phases
   */
  @JsonProperty("phases")
  public List<String> getPhases() {
    return phases;
  }

  /**
   * Sets the list of phases during which the validation applies.
   *
   * @param phases the list of validation phases
   */
  @JsonProperty("phases")
  public void setPhases(final List<String> phases) {
    this.phases = phases;
  }

  @Override
  @JsonAnySetter
  public void addOption(final String key, final Object value) {
    if (!"name".equals(key) && !"type".equals(key) && !"phases".equals(key)) {
      this.options.put(key, value);
    }
  }

  /**
   * Returns the map of additional, arbitrary validation options.
   *
   * @return the options map
   */
  @Override
  public Map<String, Object> getOptions() {
    return options;
  }
}
