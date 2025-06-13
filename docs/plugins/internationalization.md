# Using Translations in Directory Manager Plugins

This guide explains how to use internationalized messages (i18n) in your plugin code for the **Directory Manager API**.
Translation support ensures consistent and customizable error and UI messages across different languages.

---

## 🧩 I18nMessage Utility Class

All plugins should use the `I18nMessage` class provided by `dm-api-core` to define and return translated messages.

### Basic Usage

```java
I18nMessage message = I18nMessage.of("myplugin.error.missing.field");
```

### With Context Parameters

You can provide dynamic parameters using a context map:

```java
Map<String, Object> context = Map.of("field", "email");
I18nMessage message = I18nMessage.of("myplugin.error.invalid.email", context);
```

This will allow translation templates like:

```json
{
  "myplugin.error.invalid.email": "The field {field} is not a valid email address."
}
```

---

## 📂 Where to Define Translations

Translations are defined in the `en.json` file (or other language codes) located in your plugin:

```
src/main/resources/i18n/fr.json
src/main/resources/i18n/en.json
```

### Example:

```json
{
  "myplugin.error.missing.field": "Le champ requis est manquant.",
  "myplugin.error.invalid.email": "Le champ {field} n'est pas une adresse e-mail valide."
}
```

> ⚠️ All keys **must be prefixed with your plugin name** to avoid naming conflicts.

---

## 🛂 Usage in Validation Plugins

Validation plugins return an `Optional<I18nMessage>` to signal a validation failure:

```java
return Optional.of(I18nMessage.of(
                       "myplugin.error.regex.invalid",
                   Map.of("pattern", pattern, "value",value)
));
```

### Validation Context

Inside a validation plugin, you receive additional context such as:

* `entity`: the name of the entity being validated
* `attribute`: the name of the attribute

### JSON Template

```json
{
  "myplugin.error.regex.invalid": "The value {value} for attribute {attribute} in entity {entity} does not match the pattern."
}
```

---

## 🛠 Tips

* Always provide useful keys and parameter names.
* Group your messages logically (e.g., `error.*`, `info.*`, `success.*`).
* Document expected i18n keys and parameters in your plugin's documentation.

---

## 🔍 Related Topics

* [Getting Started with Plugin Creation](./How-to-create-a-plugin.md)
