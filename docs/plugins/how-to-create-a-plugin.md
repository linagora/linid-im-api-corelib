# Getting Started: Creating a Plugin for Directory Manager API

This guide explains how to create a plugin for the **Directory Manager API** platform, using the provided template and
following conventions for packaging, translation, and integration.

---

## 🚀 1. Clone the Plugin Template Repository

To begin, you can use the official plugin template as a starting point:

```bash
git clone https://ci.linagora.com/linagora/lrs/LinID/linid-dm-v2/template-dm-api-plugin.git my-plugin
cd my-plugin
```

Then, configure your Git remotes:

```bash
git remote rename origin upstream

git remote add origin <your-git-repository-url>
```

Replace `<your-git-repository-url>` with the URL of your own Git repository.

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

The template already includes a default `pom.xml` preconfigured for a **standard Directory Manager plugin**.
It declares:

* Required dependencies, including `dm-api-core`
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

You can place the generated `.jar` file into the `plugins/` folder of the `dm-api` runtime.

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
