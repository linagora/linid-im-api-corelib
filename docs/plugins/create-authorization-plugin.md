# Creating an Authorization Plugin for Identity Manager API

This guide walks you through the steps to create an **Authorization Plugin** using the `linid-im-api-corelib` library.
An authorization plugin is solely responsible for **token validation**: verifying that the incoming HTTP request carries
a valid, non-expired token. Authorization and permission checks are handled separately in the pipeline.

---

## ✨ Goal

We will create a simple authorization plugin named `ExampleAuthorizationPlugin` that demonstrates how to extend the core
framework and implement basic token validation.

---

## 📁 1. Create the Java Class

Create a new Java class in your plugin project:

```java
package io.github.linagora.linid.im.myplugin;

import io.github.linagora.linid.im.corelib.plugin.authorization.AbstractAuthorizationPlugin;
import io.github.linagora.linid.im.corelib.plugin.config.dto.AuthorizationConfiguration;
import io.github.linagora.linid.im.corelib.plugin.task.TaskExecutionContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ExampleAuthorizationPlugin extends AbstractAuthorizationPlugin {

    @Override
    public boolean supports(@NonNull String type) {
        return "example-auth".equalsIgnoreCase(type);
    }

    @Override
    public void validateToken(AuthorizationConfiguration configuration, HttpServletRequest request,
                              TaskExecutionContext context) {
        // Implement token extraction and validation logic using the provided configuration
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

### 🔐 Plugin Responsibility: Token Validation

The plugin has a single responsibility:

* **Token validation** via `validateToken(AuthorizationConfiguration configuration, HttpServletRequest request, TaskExecutionContext context)`

The `AuthorizationConfiguration` parameter provides the active plugin configuration (e.g. issuer URL, audience, public
key) so that token validation can be fully parameterized without storing state on the plugin instance.

The method should throw an exception (typically an `ApiException` with HTTP 401 Unauthorized) if the token is missing,
invalid, or expired. Authorization and permission checks are handled elsewhere in the pipeline.

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
