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

package io.github.linagora.linid.im.corelib.i18n;

import java.util.List;
import java.util.Map;

/**
 * Service interface for internationalization (i18n) operations.
 *
 * <p>Provides methods to retrieve supported languages, translations, and to translate messages
 * using {@link I18nMessage} objects.
 */
public interface I18nService {
  /**
   * Returns the list of supported language codes.
   *
   * @return a list of language codes (e.g., "en", "fr", "de")
   */
  List<String> getLanguages();

  /**
   * Returns a map of translation keys and their corresponding translated strings for the specified
   * language.
   *
   * @param language the language code (e.g., "en", "fr")
   * @return a map where keys are message keys and values are translated strings
   */
  Map<String, String> getTranslations(String language);

  /**
   * Translates the given internationalized message using the default or current language context.
   *
   * @param message the {@link I18nMessage} to translate
   * @return the translated string
   */
  String translate(I18nMessage message);

  /**
   * Translates the given internationalized message for the specified language.
   *
   * @param language the language code to translate to
   * @param message the {@link I18nMessage} to translate
   * @return the translated string in the specified language
   */
  String translate(String language, I18nMessage message);
}
