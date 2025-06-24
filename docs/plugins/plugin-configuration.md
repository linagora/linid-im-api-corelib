# Plugin Configuration System

This plugin system enables dynamic configuration of **providers**, **entities**, **routes**, **validations**, and *
*tasks** using a YAML or JSON-based format. It is designed to allow runtime definition of data models, integrations, and
processing logic with no need for code changes.

---

## Configuration Structure

The configuration contains four main sections:

- `providers`: External data sources or services.
- `routes`: API or processing endpoints.
- `tasks`: Reusable processing steps or background jobs.
- `validations`: Reusable configuration for field validation.
- `entities`: Logical data objects exposed by the API and linked to providers, routes, and tasks.

```yaml
providers:
  - ...
routes:
  - ...
tasks:
  - ...
validations:
  - ...
entities:
  - ...
````

📄 See a full example here: [example-config.yaml](example-config.yaml)

---

## Providers

A **provider** defines how to connect to an external data source (e.g., LDAP, database, API). Each provider has:

* `name`: A unique identifier.
* `type`: The plugin type (e.g., `ldap`, `api`, `database`).
* Custom plugin-specific options (e.g., `url`, `credentials`, etc.).

### Example

```yaml
providers:
  - name: LDAP1
    type: ldap
    url: ldap://host1
  - name: LDAP2
    type: ldap
    url: ldap://host2
```

---

## Routes

A **route** represents an exposed API or processing path (e.g., for exporting data).

* `type`: A route type (e.g., `export`, `sync`, etc.).
* Additional plugin-defined options.

### Example

```yaml
routes:
  - type: export
    exportType: xml
```

---

## Tasks

A **task** is a reusable operation that can be scheduled globally or linked to an entity lifecycle phase.

* `type`: The plugin-defined task type (e.g., `S3SaveTask`).
* `name`: The unique identifier of the task instance.
* Custom plugin options (e.g., `url`, `bucket`, etc.).

### Example

```yaml
tasks:
  - type: S3SaveTask
    name: saveDocument
    url: https://s3.example.com/documents
  - type: S3SaveTask
    name: saveExcel
    url: https://s3.example.com/excels
```

---

## Validations

A **validation** defines how to validate to data (e.g., regex, required). Each validation has:

* `name`: A unique identifier.
* `type`: The plugin type (e.g., `regex`, `not-null`).
* Custom plugin-specific options (e.g., `pattern`, etc.).

### Example

```yaml
validation:
  - name: regex1
    type: regex
    pattern: .+
  - name: regex2
    type: regex
    pattern: .{0,3}
```

---

## Entities

An **entity** defines a managed object with:

* `name`: Logical name of the entity.
* `provider`: Reference to the provider name.
* `route`: (Optional) Associated route.
* `tasks`: List of tasks to run at specific lifecycle phases.
* `attributes`: List of entity attributes with type and validations.
* `access`: (Optional) A map of additional custom options used by the provider to access or resolve the entity.

### Example

```yaml
entities:
  - name: user
    provider: LDAP1
    route: users
    access:
      baseDn: "ou=users,dc=example,dc=com"
      filter: "(objectClass=person)"
    tasks:
      - type: S3SaveTask
        phase: [ 'beforeCreate', 'beforeExport' ]
        # plugin options
        # (...)
      - name: SaveDocument # refer to named task "SaveDocument"
        phase: [ 'beforeCreate', 'beforeExport' ]
      # plugin options
      # (...)
    attributes:
      - name: id
        type: UUID
        required: true
        input: text
        inputSettings:
          option: "X"
        access:
          ldapField: givenName
        validations:
          - type: regex
            pattern: ^/d$
            ignoreCase: true
            accessibleFront: false
            phase: [ 'beforeCreate' ]
          - type: regex
            pattern: ^/d$
            ignoreCase: true
            phase: [ 'beforeUpdate' ]
```

### Attributes

Each attribute supports:

* `name`: Attribute name.
* `type`: Attribute type (e.g., `UUID`, `String`, etc.).
* `required`: Boolean indicating whether the attribute is mandatory.
* `input`: String specifying the front-end input type to use (e.g., "text", "select", "checkbox").
* `inputSettings`: Map of settings for the input type, such as options, placeholders, or validation constraints.
* `validations`: Optional list of validation rules.
* `access`: (Optional) A map of additional provider-specific settings to retrieve or store the attribute.

#### Validations

Each validation supports

* `name`: the identifier of global configuration to used.
* `type`: The validation type (e.g., `regex`, `notNull`). This determines which plugin handles the validation.
* `phase`: (Optional) When the validation applies (e.g., `beforeCreate`, `beforeUpdate`).
  If omitted, the plugin will be executed for **all phases** by default.

Additional fields are **plugin-specific options**, and depend on the validation `type`.

If global configuration are set, options defined in this section will override it.

Refer to each plugin’s documentation for the complete list of supported options.

#### Input

> ⚠️ **Under construction:** This feature is still being developed and may change in future versions.

---

## Phases

Lifecycle phases define when tasks or validations are executed:

* `beforeCreate`
* `beforeUpdate`
* `beforeExport`
* (Custom phases may be supported depending on plugin logic)

---

## Extensibility

All components (providers, tasks, routes) support arbitrary custom fields to allow plugin-specific configuration without
schema changes. This makes the system flexible and extensible for multiple use cases.
