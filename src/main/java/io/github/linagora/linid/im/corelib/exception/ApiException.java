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

package io.github.linagora.linid.im.corelib.exception;

import io.github.linagora.linid.im.corelib.i18n.I18nMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Custom exception class to represent API-specific exceptions.
 *
 * <p>This exception carries an HTTP status code, a localized error message, optional detailed
 * context information, and a flag indicating whether the exception should be logged.
 *
 * <p>It extends {@link RuntimeException} and supports various constructors to provide flexibility
 * in error reporting.
 */
public class ApiException extends RuntimeException {

  /**
   * Flag indicating whether this exception needs to be logged. Default value is {@code true},
   * meaning the exception should be logged unless explicitly set otherwise.
   */
  private final boolean needToBeLogged;

  /** The HTTP status code associated with the error (e.g., 404, 500). */
  private final int statusCode;

  /** The internationalized error message associated with this exception. */
  private final I18nMessage error;

  /** Additional contextual details related to the exception. */
  private final Map<String, Object> details;

  /**
   * Constructs a new {@code ApiException} with the given HTTP status code and error message. The
   * exception will be logged by default.
   *
   * @param statusCode the HTTP status code associated with the error
   * @param error the internationalized error message
   */
  public ApiException(final int statusCode, final I18nMessage error) {
    this(statusCode, error, null, null, true);
  }

  /**
   * Constructs a new {@code ApiException} with the given HTTP status code, error message, and
   * additional details. The exception will be logged by default.
   *
   * @param statusCode the HTTP status code associated with the error
   * @param error the internationalized error message
   * @param details additional contextual details about the exception
   */
  public ApiException(
      final int statusCode, final I18nMessage error, final Map<String, Object> details) {
    this(statusCode, error, details, null, true);
  }

  /**
   * Constructs a new {@code ApiException} with the given HTTP status code, error message, and a
   * flag indicating whether it should be logged.
   *
   * @param statusCode the HTTP status code associated with the error
   * @param error the internationalized error message
   * @param needToBeLogged whether this exception should be logged
   */
  public ApiException(final int statusCode, final I18nMessage error, final boolean needToBeLogged) {
    this(statusCode, error, null, null, needToBeLogged);
  }

  /**
   * Constructs a new {@code ApiException} with the given HTTP status code, error message,
   * additional details, and a flag indicating whether it should be logged.
   *
   * @param statusCode the HTTP status code associated with the error
   * @param error the internationalized error message
   * @param details additional contextual details about the exception
   * @param needToBeLogged whether this exception should be logged
   */
  public ApiException(
      final int statusCode,
      final I18nMessage error,
      final Map<String, Object> details,
      final boolean needToBeLogged) {
    this(statusCode, error, details, null, needToBeLogged);
  }

  /**
   * Constructs a new {@code ApiException} with the given HTTP status code, error message, and a
   * cause. The exception will be logged by default.
   *
   * @param statusCode the HTTP status code associated with the error
   * @param error the internationalized error message
   * @param cause the cause of this exception
   */
  public ApiException(final int statusCode, final I18nMessage error, final Throwable cause) {
    this(statusCode, error, null, cause, true);
  }

  /**
   * Constructs a new {@code ApiException} with the given HTTP status code, error message,
   * additional details, and a cause. The exception will be logged by default.
   *
   * @param statusCode the HTTP status code associated with the error
   * @param error the internationalized error message
   * @param details additional contextual details about the exception
   * @param cause the cause of this exception
   */
  public ApiException(
      final int statusCode,
      final I18nMessage error,
      final Map<String, Object> details,
      final Throwable cause) {
    this(statusCode, error, details, cause, true);
  }

  /**
   * Constructs a new {@code ApiException} with the given HTTP status code, error message, a cause,
   * and a flag indicating whether it should be logged.
   *
   * @param statusCode the HTTP status code associated with the error
   * @param error the internationalized error message
   * @param cause the cause of this exception
   * @param needToBeLogged whether this exception should be logged
   */
  public ApiException(
      final int statusCode,
      final I18nMessage error,
      final Throwable cause,
      final boolean needToBeLogged) {
    this(statusCode, error, null, cause, needToBeLogged);
  }

  /**
   * Constructs a new {@code ApiException} with all parameters specified.
   *
   * @param statusCode the HTTP status code associated with the error
   * @param error the internationalized error message
   * @param details additional contextual details about the exception
   * @param cause the cause of this exception
   * @param needToBeLogged whether this exception should be logged
   */
  public ApiException(
      final int statusCode,
      final I18nMessage error,
      final Map<String, Object> details,
      final Throwable cause,
      final boolean needToBeLogged) {
    super(error.key(), cause);
    this.error = error;
    this.statusCode = statusCode;
    this.details = Optional.ofNullable(details).orElse(new HashMap<>());
    this.needToBeLogged = needToBeLogged;
  }

  /**
   * Returns whether this exception should be logged.
   *
   * @return {@code true} if the exception needs to be logged; {@code false} otherwise.
   */
  public boolean isNeedToBeLogged() {
    return needToBeLogged;
  }

  /**
   * Returns the HTTP status code associated with this exception.
   *
   * @return the HTTP status code
   */
  public int getStatusCode() {
    return statusCode;
  }

  /**
   * Returns additional details providing context about the exception.
   *
   * @return a map of detail keys to their values
   */
  public Map<String, Object> getDetails() {
    return details;
  }

  /**
   * Returns the internationalized error message for this exception.
   *
   * @return the {@link I18nMessage} instance representing the error message
   */
  public I18nMessage getError() {
    return error;
  }
}
