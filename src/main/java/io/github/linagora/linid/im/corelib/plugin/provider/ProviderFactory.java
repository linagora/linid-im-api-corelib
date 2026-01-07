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

package io.github.linagora.linid.im.corelib.plugin.provider;

import java.util.Optional;

/**
 * Factory interface for retrieving a {@link ProviderPlugin} instance by its configured name.
 *
 * <p>This abstraction allows dynamic resolution of provider plugins based on their logical
 * identifier (usually defined in configuration as {@code name}). It supports scenarios where
 * multiple configurations of the same provider type (e.g., multiple LDAP providers) are needed.
 *
 * <p>Example use case:
 *
 * <pre>{@code
 * Optional<ProviderPlugin> plugin = providerFactory.getProviderByName("ldap1");
 * plugin.ifPresent(p -> p.performOperation(...));
 * }</pre>
 */
public interface ProviderFactory {
  /**
   * Retrieves a {@link ProviderPlugin} based on the given provider name.
   *
   * <p>This allows selecting a specific configured instance of a provider, such as "ldap1" or
   * "ldap2", depending on the name used in the configuration.
   *
   * @param name the logical name of the provider to retrieve
   * @return an {@link Optional} containing the matching {@code ProviderPlugin}, or an empty {@code
   *     Optional} if no match is found
   */
  Optional<ProviderPlugin> getProviderByName(String name);
}
