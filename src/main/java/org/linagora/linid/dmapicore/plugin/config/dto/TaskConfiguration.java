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
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the configuration of a task within the plugin system.
 *
 * <p>Each task is identified by a {@code name} and associated with a list of execution {@code
 * phases}. Additional custom properties related to the task can be dynamically added and stored in
 * the {@code options} map using JSON deserialization with {@link JsonAnySetter}. Reserved keys such
 * as "name" and "phases" are excluded from the {@code options} map.
 */
public class TaskConfiguration implements PluginConfiguration {

  /**
   * Map holding additional task-specific configuration options not explicitly defined as fields.
   * This is populated dynamically during JSON deserialization via {@link JsonAnySetter}.
   */
  private final Map<String, Object> options = new HashMap<>();

  /**
   * The name identifier of this task configuration instance. This allows multiple configurations of
   * the same task type to coexist.
   */
  private String name;

  /**
   * The type of task plugin this configuration targets. Typically corresponds to the plugin
   * implementation identifier, such as "S3SaveDocumentTask".
   */
  private String type;

  /** A list of phases in which this task is executed. */
  private List<String> phases = new ArrayList<>();

  /** Default constructor. */
  public TaskConfiguration() {}

  @Override
  @JsonAnySetter
  public void addOption(final String key, final Object value) {
    if (!"name".equals(key) && !"type".equals(key) && !"phases".equals(key)) {
      this.options.put(key, value);
    }
  }

  /**
   * Returns the list of phases in which this task is executed.
   *
   * @return the list of task phases
   */
  @JsonProperty("phases")
  public List<String> getPhases() {
    return phases;
  }

  /**
   * Sets the list of phases in which this task is executed.
   *
   * @param phases the list of phases to set
   */
  @JsonProperty("phases")
  public void setPhases(final List<String> phases) {
    this.phases = phases;
  }

  /**
   * Returns the name of this task configuration instance.
   *
   * @return the configuration instance name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of this task configuration instance.
   *
   * @param name the name to set
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Returns the type of task plugin this configuration targets.
   *
   * @return the task type identifier
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type of task plugin this configuration targets.
   *
   * @param type the task type identifier to set
   */
  public void setType(final String type) {
    this.type = type;
  }

  /**
   * Returns the map of additional configuration options for this task.
   *
   * @return the options map
   */
  @Override
  public Map<String, Object> getOptions() {
    return options;
  }
}
