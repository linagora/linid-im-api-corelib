# Error Management (`ApiException`)

## 🎯 Purpose

To provide a standardized mechanism for reporting errors within plugins, combining:

* An HTTP status code (e.g., `400`, `404`, `500`),
* An internationalized message (`I18nMessage`),
* Optional contextual details for debugging or client feedback,
* A flag to control whether the error should be logged.

---

## 🧱 The `ApiException` Class

`ApiException` is a custom runtime exception used for reporting API-specific errors.

It extends `RuntimeException` and includes:

* An HTTP status code (`statusCode`)
* A localized message (`I18nMessage`)
* Optional error `details` (`Map<String, Object>`)
* A `needToBeLogged` flag (default is `true`)

### Common Constructors

```java
throw new ApiException(400,I18nMessage.of("error.plugin.invalid.parameter"));
```

```java
throw new ApiException(
    500,
    I18nMessage.of("error.plugin.missing.option", Map.of("option", "pattern"))
    );
```

```java
throw new ApiException(
    500,
    I18nMessage.of("error.plugin.missing.option", Map.of("option", "pattern")),
    false
    );
```

---

## 🧾 HTTP Error Response Format

When an `ApiException` is thrown, it is automatically intercepted and converted into a structured HTTP response by the
`@ExceptionHandler`.

### Example JSON Response

```json
{
  "error": "The 'pattern' option is missing",
  "errorKey": "error.plugin.missing.option",
  "errorContext": {
    "option": "pattern"
  },
  "status": 500,
  "timestamp": "2025-06-13T10:12:34.567Z",
  "entity": "User",
  "operation": "delete"
}
```

---

## 🧠 Best Practices for Plugin Developers

### Always Use Translation Keys

Use `I18nMessage.of(...)` with valid keys that are recognized by the translation system.
See [using-translations.md](./internationalization.md) for more information.

### Add Useful Details

The `details` map can provide technical or business context to enrich the error response.

```java
throw new ApiException(
    500,
    I18nMessage.of("error.plugin.missing.option", Map.of("option", "pattern")),
    Map.of("additionalData","test")
    );
```

### Customize Logging Behavior

By default, all exceptions are logged. To avoid logging known or expected errors (e.g., user input errors), set
`needToBeLogged` to `false`:

```java
throw new ApiException(
    404,
    I18nMessage.of("error.entity.not.found", Map.of("id", id)),
    false
    );
```

---

## ✅ Summary

| Field            | Description                                        |
|------------------|----------------------------------------------------|
| `statusCode`     | The HTTP status code returned                      |
| `I18nMessage`    | Translation key and dynamic context                |
| `details`        | Additional structured context                      |
| `needToBeLogged` | Controls whether the error is logged automatically |

---

## 🔍 Related Topics

* [Getting Started with Plugin Creation](./how-to-create-a-plugin.md)
