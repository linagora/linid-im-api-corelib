# Creating a Route Plugin

Route plugins allow you to extend the REST API of the Directory Manager by providing custom HTTP endpoints. This is
especially useful when you want to expose specialized routes (e.g., export, batch processing) beyond the default CRUD
operations.

---

## ✨ Example Implementation

```java
package org.linagora.linid.ldap;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.linagora.linid.dmapicore.plugin.config.PluginConfigurationService;
import org.linagora.linid.dmapicore.plugin.provider.ProviderFactory;
import org.linagora.linid.dmapicore.plugin.route.AbstractRoutePlugin;
import org.linagora.linid.dmapicore.plugin.task.TaskEngine;
import org.linagora.linid.dmapicore.plugin.validation.ValidationEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

public class LdapRoutePlugin extends AbstractRoutePlugin {

  private final ProviderFactory providerFactory;
  private final PluginConfigurationService configurationService;
  private final ValidationEngine validationEngine;
  private final TaskEngine taskEngine;

  @Autowired
  public LdapRoutePlugin(final ProviderFactory providerFactory,
                         final PluginConfigurationService configurationService,
                         final ValidationEngine validationEngine,
                         final TaskEngine taskEngine) {
    super();
    this.providerFactory = providerFactory;
    this.configurationService = configurationService;
    this.validationEngine = validationEngine;
    this.taskEngine = taskEngine;
  }

  @Override
  public boolean supports(@NonNull String type) {
    return true;
  }

  @Override
  public List<RouteDescription> getRoutes(List<EntityConfiguration> entities) {
    // Return all routes managed by you plugins.
    return List.of();
  }

  @Override
  public boolean match(String url, String method) {
    return "GET".equals(method) && url.endsWith("/api/export");
  }

  @Override
  public ResponseEntity<?> execute(HttpServletRequest request) {
    // Your custom export logic here
    return ResponseEntity.ok().build();
  }
}
```

---

## 🔧 How It Works

* `getRoutes(List<EntityConfiguration> entities)`: Returns the list of route descriptions dynamically generated based on
  the provided entity configurations.
* `match(String url, String method)`: defines which routes this plugin handles.
* `execute(HttpServletRequest request)`: performs the logic for the matched route.

Route plugins can:

* Call `ProviderPlugin`s via the `ProviderFactory`
* Leverage validation through the `ValidationEngine`
* Execute custom `TaskPlugin`s for extensible logic through the `TaskEngine`

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

## 🔍 Related Topics

* [Getting Started with Plugin Creation](./how-to-create-a-plugin.md)
