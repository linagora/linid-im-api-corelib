# Getting Started: Creating a Plugin for Identity Manager API

This guide explains how to create a plugin for the **Identity Manager API** platform, using the provided template and
following conventions for packaging, translation, and integration.

---

## 🧩 1. Plugin Development Workflow

You can create two types of plugins for `linid-im-api`:

---

### ✅ Creating an Official Plugin (Maintained in `linid-im-api-community-plugins`)

To contribute an official plugin:

1. Clone the `linid-im-api-community-plugins` repository.
2. Create a new folder at the root of the project, using `template-plugin` as a reference:

    * Copy the entire `template-plugin` directory.
    * Rename it with the appropriate plugin name.
3. Customize the code to implement the desired functionality.
4. Submit a **merge request** to propose the new plugin.

> These plugins are considered official and publicly maintained by the LINID team or trusted contributors.

---

### 🔁 Creating a Custom Plugin (In Your Own Repository)

To develop a private or public plugin outside the official repository:

Start by creating a new repository (public or private) to host your plugin code.

#### 📦 On GitHub

1. Create a new repository using `linid-im-api-plugin-template` as the template.
2. Customize the plugin to implement the required functionality.
3. The plugin will be automatically loaded by `linid-im-api` through configuration or dynamic discovery.

#### ☁️ On Another Git Hosting Platform (e.g., GitLab, Bitbucket)

1. Create a new repository and add `linid-im-api-plugin-template` as an **upstream remote**:

   ```bash
   git remote add upstream https://github.com/linagora/linid-im-api-plugin-template.git
   git fetch upstream
   git merge upstream/main
   ```

2. Customize the plugin to implement the required functionality.

3. The plugin will be automatically loaded by `linid-im-api` through configuration or dynamic discovery.

> Clients are free to choose whether their plugin code is public or private, depending on their internal policies and requirements. Both options are fully supported.


---

## 📦 2. Understand the Project Structure

A plugin is a standard Maven project. Important folders and files:

```
my-plugin/
├── pom.xml
├── src/
│   └── main/
│       ├── java/                         # Java source files
│       └── resources/
│           └── i18n/
│               └── fr.json              # i18n file for French messages
```

### 📄 `pom.xml`

The template already includes a default `pom.xml` preconfigured for a **standard Identity Manager plugin**.
It declares:

* Required dependencies, including `linid-im-api-corelib`
* Correct packaging type (`jar`)
* Standard plugin metadata (`groupId`, `artifactId`, etc.)

You can customize the `artifactId`, `name`, and version according to your plugin's identity.

---

## 🌐 3. Internationalization (i18n)

Each plugin **must** contain an i18n file under `src/main/resources/i18n`:

```
src/main/resources/i18n/fr.json
```

This file contains key-value pairs used for translations:

```json
{
  "myplugin.error.invalid.input": "Invalid input for {field}"
}
```

### ✅ Naming Convention

All translation keys should be prefixed with your plugin's name to avoid collisions. For example:

* `myplugin.error.invalid.input`
* `myplugin.success.creation`

You can retrieve translations in your plugin using the i18n service available in the core module.

---

## 🧩 4. Implementing Plugin Types

Once the project is set up, you can create different plugin types by implementing the appropriate interface:

* [Creating an Authorization Plugin](./create-authorization-plugin.md)
* [Creating a Provider Plugin](./create-provider-plugin.md)
* [Creating a Task Plugin](./create-task-plugin.md)
* [Creating a Route Plugin](./create-route-plugin.md)
* [Creating a Validation Plugin](./create-validation-plugin.md)

Each guide contains examples and recommendations.

---

## 📤 5. Deploying the Plugin

After building the plugin with:

```bash
mvn clean install
```

You can place the generated `.jar` file into the `plugins/` folder of the `linid-im-api` runtime.

The plugin will be discovered and loaded automatically at application startup.

---

## 🛠️ Next Steps

* [Creating an Authorization Plugin](./create-authorization-plugin.md)
* [Creating a Provider Plugin](./create-provider-plugin.md)
* [Creating a Task Plugin](./create-task-plugin.md)
* [Creating a Route Plugin](./create-route-plugin.md)
* [Creating a Validation Plugin](./create-validation-plugin.md)
* [Internationalization](./internationalization.md)
* [Error management](./error-management.md)
