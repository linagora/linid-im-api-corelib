# Creating a Provider Plugin for Identity Manager API

This guide walks you through the steps to create a **Provider Plugin** using the `linid-im-api-corelib` library. A 
provider
plugin is responsible for implementing the persistence and retrieval logic for a specific data source (e.g., LDAP, SQL,
API, etc.).

---

## ✨ Goal

We will create a simple provider plugin named `LdapProviderPlugin` that demonstrates how to extend the core framework.

---

## 📁 1. Create the Java Class

Create a new Java class in your plugin project:

```java
package io.github.linagora.linid.im.ldap;

import org.linagora.linid.im.corelib.plugin.entity.DynamicEntity;
import org.linagora.linid.im.corelib.plugin.task.TaskExecutionContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class LdapProviderPlugin implements ProviderPlugin {

  @Override
  public boolean supports(@NonNull String type) {
    return "ldap".equals(type);
  }

  @Override
  public DynamicEntity create(TaskExecutionContext context,
                              ProviderConfiguration configuration,
                              DynamicEntity dynamicEntity) {
    return new DynamicEntity();
  }

  @Override
  public DynamicEntity update(TaskExecutionContext context,
                              ProviderConfiguration configuration,
                              String id,
                              DynamicEntity dynamicEntity) {
    return new DynamicEntity();
  }

  @Override
  public DynamicEntity patch(TaskExecutionContext context,
                             ProviderConfiguration configuration,
                             String id,
                             DynamicEntity dynamicEntity) {
    return new DynamicEntity();
  }

  @Override
  public boolean delete(TaskExecutionContext context,
                        ProviderConfiguration configuration,
                        String id,
                        DynamicEntity dynamicEntity) {
    return true;
  }

  @Override
  public DynamicEntity findById(TaskExecutionContext context,
                                ProviderConfiguration configuration,
                                String id,
                                DynamicEntity dynamicEntity) {
    return new DynamicEntity();
  }

  @Override
  public Page<DynamicEntity> findAll(TaskExecutionContext context,
                                     ProviderConfiguration configuration,
                                     MultiValueMap<String, String> filters,
                                     Pageable pageable,
                                     DynamicEntity dynamicEntity) {
    return Page.empty();
  }
}
```

---

## 🧩 2. Key Concepts

### ✅ Annotation `@Component`

Marks the plugin class as a Spring-managed bean so it can be auto-discovered by the runtime.

### 🔁 `supports(String type)`

Defines the plugin identifier (in this case, `ldap`) that is used in the dynamic entity configuration to select the
appropriate provider.

### 🔨 Core Methods

You must implement the following CRUD-style methods:

* `create(...)`
* `update(...)`
* `patch(...)`
* `delete(...)`
* `findById(...)`
* `findAll(...)`

Each receives a `DynamicEntity`, which contains the configured structure and attributes of the entity defined by the
user.

---

## 🛠️ 3. Configure Your Plugin

Make sure your `pom.xml` includes the required dependency:

```xml

<dependency>
    <groupId>io.github.linagora.linid.im</groupId>
    <artifactId>linid-im-api-corelib</artifactId>
</dependency>
```

And ensure you have a valid translation file under:

```
src/main/resources/i18n/fr.json
src/main/resources/i18n/en.json
```

For example:

```json
{
  "ldapplugin.error.user.notfound": "User not found in LDAP"
}
```

---

## 🔄 4. Test & Deploy

Build your plugin:

```bash
mvn clean install
```

Place the generated `.jar` file in the `plugins/` folder of your `linid-im-api` application. It will be automatically 
loaded at runtime.

---

## 🔍 Related Topics

* [Getting Started with Plugin Creation](./how-to-create-a-plugin.md)
