# Creating a Route Plugin for Identity Manager API

Route plugins allow you to extend the REST API of the Identity Manager by providing custom HTTP endpoints. This is
especially useful when you want to expose specialized routes (e.g., export, batch processing) beyond the default CRUD
operations.

---

## ✨ Example Implementation

```java
package io.github.linagora.linid.im.ldap;

import io.github.linagora.linid.im.corelib.plugin.config.PluginConfigurationService;
import io.github.linagora.linid.im.corelib.plugin.config.dto.EntityConfiguration;
import io.github.linagora.linid.im.corelib.plugin.config.dto.RouteConfiguration;
import io.github.linagora.linid.im.corelib.plugin.provider.ProviderFactory;
import io.github.linagora.linid.im.corelib.plugin.route.RouteDescription;
import io.github.linagora.linid.im.corelib.plugin.route.RoutePlugin;
import io.github.linagora.linid.im.corelib.plugin.task.TaskEngine;
import io.github.linagora.linid.im.corelib.plugin.validation.ValidationEngine;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

public class LdapRoutePlugin implements RoutePlugin {

    private final ProviderFactory providerFactory;
    private final PluginConfigurationService configurationService;
    private final ValidationEngine validationEngine;
    private final TaskEngine taskEngine;

    @Autowired
    public LdapRoutePlugin(final ProviderFactory providerFactory,
                           final PluginConfigurationService configurationService,
                           final ValidationEngine validationEngine,
                           final TaskEngine taskEngine) {
        this.providerFactory = providerFactory;
        this.configurationService = configurationService;
        this.validationEngine = validationEngine;
        this.taskEngine = taskEngine;
    }

    @Override
    public boolean supports(@NonNull String type) {
        return "ldap-export".equals(type);
    }

    @Override
    public List<RouteDescription> getRoutes(RouteConfiguration configuration,
                                            List<EntityConfiguration> entities) {
        // Return all routes managed by your plugin.
        return List.of();
    }

    @Override
    public boolean match(RouteConfiguration configuration, String url, String method) {
        return "GET".equals(method) && url.endsWith("/api/export");
    }

    @Override
    public ResponseEntity<?> execute(RouteConfiguration configuration,
                                     HttpServletRequest request) {
        // Your custom export logic here
        return ResponseEntity.ok().build();
    }
}
```

---

## 🔧 How It Works

- `getRoutes(RouteConfiguration configuration, List<EntityConfiguration> entities)`: Returns the list of route
  descriptions dynamically generated based on the provided route configuration and entity configurations.
- `match(RouteConfiguration configuration, String url, String method)`: defines which routes this plugin handles.
- `execute(RouteConfiguration configuration, HttpServletRequest request)`: performs the logic for the matched route.

> The `RouteConfiguration` is passed as a parameter to each method, making plugins stateless and thread-safe.

Route plugins can:

- Call `ProviderPlugin`s via the `ProviderFactory`
- Leverage validation through the `ValidationEngine`
- Execute custom `TaskPlugin`s for extensible logic through the `TaskEngine`

> ⚠️ **Note:** Avoid injecting plugins directly unless they are guaranteed to be present in the final `.jar`. Prefer
> using factories or service locators.

---

## ⚖️ Example: Adding Custom Phases

You can define custom task phases to allow users to hook into route-specific logic:

- `beforeLdapExport`
- `afterLdapExport`

This enables users to plug their own `ValidationPlugin`s / `TaskPlugin`s before or after your route logic, giving full
extensibility.

---

## 🔄 Converting Entities for Custom Endpoint Responses and Usage in Route Plugins

When implementing a new custom route that returns entities in the HTTP response, you need to convert your internal
`DynamicEntity` objects into a format suitable for serialization, typically a `Map<String, Object>`.

To achieve this, you can use the `DynamicEntityMapper` interface:

```java
public interface DynamicEntityMapper extends Function<DynamicEntity, Map<String, Object>> {
}
```

This mapper converts a `DynamicEntity` into a `Map<String, Object>`, handling attribute mapping, renaming, type
conversion, and other transformations based on the plugin configuration.

### How to use `DynamicEntityMapper` in your Route Plugin

1. **Inject the mapper** into your route plugin class:

```java

@Component
public class LdapRoutePlugin implements RoutePlugin {

  private final DynamicEntityMapper entityMapper;

  @Autowired
  public LdapRoutePlugin(DynamicEntityMapper entityMapper /*, other dependencies */) {
    this.entityMapper = entityMapper;
  }

  // ...
}
```

2. **Use the mapper when building the response** inside your `execute` method:

```java

@Override
public ResponseEntity<?> execute(RouteConfiguration configuration, HttpServletRequest request) {
  DynamicEntity entity = // retrieve or build your entity
  Map<String, Object> mappedEntity = entityMapper.apply(entity);

  return ResponseEntity.ok(mappedEntity);
}
```

By leveraging the `DynamicEntityMapper`, you ensure that entity attribute mappings are consistent with your
configuration, reducing manual mapping code and potential errors.

---

## 🔍 Related Topics

- [Getting Started with Plugin Creation](./how-to-create-a-plugin.md)
