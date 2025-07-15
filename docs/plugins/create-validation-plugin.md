# Creating a Validation Plugin for Identity Manager API

This guide explains how to create a **Validation Plugin** for the Identity Manager API platform. A validation plugin is
responsible for enforcing specific data validation rules defined in entity configurations.

---

## 📦 Sample Plugin: NotNull Validator

```java
package io.github.linagora.linid.im.notnull;

import io.github.linagora.linid.im.corelib.exception.ApiException;
import io.github.linagora.linid.im.corelib.i18n.I18nMessage;
import io.github.linagora.linid.im.corelib.model.ValidationConfiguration;
import io.github.linagora.linid.im.corelib.plugin.validation.ValidationPlugin;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NotNullValidationPlugin implements ValidationPlugin {

    @Override
    public boolean supports(String type) {
        return "not-null".equals(type);
    }

    @Override
    public Optional<I18nMessage> validate(ValidationConfiguration configuration, Object value) {
        boolean checkEmpty = this.getOption(configuration, "check-empty", boolean.class).orElse(false);
        if (value == null) {
            return Optional.of(I18nMessage.of("error.plugin.notnull.invalid"));
        }

        if (checkEmpty && value.toString().isEmpty()) {
            return Optional.of(I18nMessage.of("error.plugin.notnull.empty"));
        }

        return Optional.empty();
    }
}
```

---

## 🧠 Key Concepts

* `supports(String type)`:
  Declares which configuration type this validator handles (in this case: `not-null`).

* `validate(ValidationConfiguration configuration, Object value)`:
  Executes validation logic and returns an `Optional<I18nMessage>`.

    * Return `Optional.empty()` if the value is valid
    * Return an `Optional` containing an error message if validation fails

* `getOption()` helper:
  Use this method to retrieve typed options from the validation configuration (e.g., `check-empty`).

---

## ✅ i18n Integration

Make sure to define i18n keys used in your plugin:

```json
{
  "error.plugin.notnull.invalid": "The value is null",
  "error.plugin.notnull.empty": "The value is empty"
}
```

Add this to your `src/main/resources/i18n/en.json` file. Remember to prefix all keys with your plugin name.

---

## 🔍 Related Topics

* [Getting Started with Plugin Creation](./how-to-create-a-plugin.md)

---

For more advanced use cases, your validation plugin can use external services, support dynamic error messages, and
integrate with phases of the execution lifecycle.
