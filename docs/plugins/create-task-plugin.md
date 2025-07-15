# Creating a Task Plugin for Identity Manager API

This guide shows how to implement a **Task Plugin** for the Identity Manager API using `linid-im-api-corelib`.

Task plugins allow you to define logic that will be executed **before or after** specific entity lifecycle phases (e.g.,
create, update, delete).

---

## 🧱 Example Plugin Implementation

Below is a basic example of a task plugin that supports the type `ldap`:

```java
package io.github.linagora.linid.im.ldap;

import org.linagora.linid.im.corelib.plugin.entity.DynamicEntity;
import org.linagora.linid.im.corelib.plugin.task.TaskExecutionContext;
import org.linagora.linid.im.corelib.plugin.task.TaskPlugin;
import org.springframework.lang.NonNull;

public class LdapTaskPlugin implements TaskPlugin {

  @Override
  public boolean supports(@NonNull String type) {
    return "ldap".equals(type);
  }

  @Override
  public void execute(DynamicEntity entity, TaskExecutionContext context) {
    // Access values from the context
    String phase = context.get("DATA", String.class);
    System.out.println("Executing LDAP task for data: " + data);

    // Optionally modify the entity
    entity.getAttributes().put("ldapHandled", true);
    // Or distribute new context
    context.put("ldapInformation", "test");
  }
}
```

---

## 🧠 Understanding `TaskExecutionContext`

The `TaskExecutionContext` is a specialized `HashMap<String, Object>` that carries data required by the plugin during
execution. It provides a type-safe accessor:

```java
<T> T get(String key, Class<T> type);
```

### Example Usage:

```java
String data = context.get("data", String.class);
Integer retryCount = context.get("retries", Integer.class);
```

If the type doesn't match or the key is missing, it returns `null`.

---

## 📄 Document Expected Context Values

Each TaskPlugin **must document** the expected keys and their types in the context.

For example, the `LdapTaskPlugin` expects:

| Key     | Type    | Description                           |
|---------|---------|---------------------------------------|
| data    | String  | Some data use by the plugin           |
| retries | Integer | (Optional) Retry count for the action |

---

## ✅ Integration

Once your task plugin is implemented:

1. Build it using Maven: `mvn clean install`
2. Place the `.jar` in the `plugins/` folder of the `linid-im-im-api` runtime
3. It will be auto-discovered and invoked during the lifecycle phases.

---

## 🔍 Related Topics

* [Getting Started with Plugin Creation](./how-to-create-a-plugin.md)
