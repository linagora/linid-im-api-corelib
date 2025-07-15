# Creating an Authorization Plugin for Identity Manager API

This guide walks you through the steps to create an **Authorization Plugin** using the `linid-im-api-corelib` library.
An authorization plugin is responsible for validating tokens and evaluating whether a request is allowed to access or
modify certain resources based on the configured access control logic.

---

## ✨ Goal

We will create a simple authorization plugin named `ExampleAuthorizationPlugin` that demonstrates how to extend the core
framework and implement basic token validation and permission checks.

---

## 📁 1. Create the Java Class

Create a new Java class in your plugin project:

```java
package io.github.linagora.linid.im.myplugin;

import jakarta.servlet.http.HttpServletRequest;
import authorization.plugin.io.github.linagora.linid.im.corelib.AbstractAuthorizationPlugin;
import dto.config.plugin.io.github.linagora.linid.im.corelib.RootConfiguration;
import entity.plugin.io.github.linagora.linid.im.corelib.DynamicEntity;
import task.plugin.io.github.linagora.linid.im.corelib.TaskExecutionContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class ExampleAuthorizationPlugin extends AbstractAuthorizationPlugin {

    @Override
    public boolean supports(@NonNull String type) {
        return "example-auth".equalsIgnoreCase(type);
    }

    @Override
    public void updateConfiguration(RootConfiguration configuration) {
        // Optional: add any additional entity or validation config if needed
    }

    @Override
    public void validateToken(HttpServletRequest request, TaskExecutionContext context) {
        // Implement token extraction and validation logic
    }

    @Override
    public void isAuthorized(HttpServletRequest request, DynamicEntity entity, String action,
                             TaskExecutionContext context) {
        // Implement per-entity authorization logic
    }

    @Override
    public void isAuthorized(HttpServletRequest request, DynamicEntity entity, String id, String action,
                             TaskExecutionContext context) {
        // Implement per-record authorization logic
    }

    @Override
    public void isAuthorized(HttpServletRequest request, DynamicEntity entity, MultiValueMap<String, String> filters,
                             String action, TaskExecutionContext context) {
        // Implement filtered dataset authorization logic
    }
}
```

---

## 🧩 2. Key Concepts

### ✅ Annotation `@Component`

Marks the plugin class as a Spring-managed bean so it can be auto-discovered by the runtime.

### 🔁 `supports(String type)`

Defines the plugin identifier (in this case, `example-auth`) that is used in the dynamic configuration to select the
appropriate authorization strategy.

### 🔐 Authorization Responsibilities

The plugin must implement the following responsibilities:

* **Token validation**: via `validateToken(...)`
* **Authorization checks**: using one of the `isAuthorized(...)` overloads depending on the scope

    * Entire entity
    * Specific record (by ID)
    * Filtered collection (with filters)

Each method should throw an exception (typically a `ForbiddenException` or `UnauthorizedException`) if the request is
not allowed.

---

## 🛠️ 3. Configure Your Plugin

Ensure your `pom.xml` includes the necessary dependency:

```xml

<dependency>
    <groupId>io.github.linagora.linid.im</groupId>
    <artifactId>linid-im-api-corelib</artifactId>
</dependency>
```

Also add translation files for localized error messages:

```
src/main/resources/i18n/fr.json
src/main/resources/i18n/en.json
```

Example:

```json
{
  "authplugin.error.token.invalid": "Invalid authentication token",
  "authplugin.error.access.denied": "Access denied for this action"
}
```

---

## 🧵 4. Advanced: Modifying or Inspecting the HTTP Request

### 🔁 Using `OncePerRequestFilter` for Token Injection or Auditing

If your plugin needs to **modify the request**, **inject data** (e.g. a decoded token), or perform **auditing/logging
logic**, you can implement a Spring `OncePerRequestFilter`.

This is useful when:

* You want to parse a token **once** and make its contents available downstream (e.g., by storing it in a `ThreadLocal`,
  `TaskExecutionContext`, or request attribute).
* You need to log or audit authorization decisions, user identity, or request metadata.
* You want to pre-process requests before `AuthorizationPlugin.validateToken(...)` is invoked.

Example:

```java

@Component
public class TokenExtractionFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String token = request.getHeader("Authorization");
    if (token != null && token.startsWith("Bearer ")) {
      String decoded = decodeJwt(token.substring(7));
      request.setAttribute("decodedToken", decoded);
    }

    filterChain.doFilter(request, response);
  }
}
```

> ✅ Spring allows **multiple** `OncePerRequestFilter` beans in the application. Their order is determined by the
`@Order` annotation or by registering them in a `FilterRegistrationBean`.

---

### ⚠️ Built-in Copyright Filter

The system provides a **built-in `OncePerRequestFilter`** responsible for **appending copyright information** at the
end of the request lifecycle. This filter is always executed **last** to ensure it doesn't interfere with user filters
or error handling.

You **do not need to override or disable it**—just be aware that it will always be invoked at the end of the filter
chain.

---

## 🔄 5. Test & Deploy

Build your plugin:

```bash
mvn clean install
```

Copy the resulting `.jar` into the `plugins/` directory of your `linid-im-api` deployment.
It will be loaded and discovered automatically at runtime.

---

## 🔍 Related Topics

* [How to Create a Plugin](./how-to-create-a-plugin.md)
