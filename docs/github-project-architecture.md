# Github Project Architecture - IM Platform

This document describes the organization of GitLab projects related to the IM platform, including core APIs, plugins,
client-specific projects, and templates.

## 📦 General Structure

### 🧱 Core Projects (Platform Foundation)

| Project                          | Description                                                                                           |
|----------------------------------|-------------------------------------------------------------------------------------------------------|
| `linid-im-api-corelib`           | Shared Maven library. Contains common code used by both plugins and the main API.                     |
| `linid-im-api`                   | Main Spring Boot REST API. Uses `linid-im-api-corelib` and loads plugins dynamically.                 |
| `linid-im-api-community-plugins` | Repository for official LINID IM API plugins.                                                         |
| `linid-im-api-plugin-template`   | Backend plugin template. Provides `pom.xml`, checkstyle configuration, GitLab CI, and base structure. |

---

### 👥 Clients

Each client can choose whether their plugin code is **public** (e.g. hosted on GitHub or GitLab) or **private**, depending on their internal policies and use cases.
Regardless of visibility, the `linid-im-api` runtime supports both public and private plugin repositories through proper configuration.

---

## 🔁 Custom Plugin Creation Workflow

To create a new backend plugin:

Start by creating a new repository (public or private) where your plugin will live.

### On GitHub

1. Use `linid-im-api-plugin-template` as the repository template.
2. Customize the plugin to implement the desired functionality.
3. The plugin will be automatically loaded by `linid-im-api` through configuration or dynamic discovery.

### On Another Git Hosting Platform

1. Create a new repository and add `linid-im-api-plugin-template` as an **upstream remote**:

```bash
git remote add upstream https://github.com/linagora/linid-im-api-plugin-template.git
git fetch upstream
git merge upstream/main
```

2. Customize the plugin to implement the desired functionality.
3. The plugin will be automatically loaded by `linid-im-api` through configuration or dynamic discovery.


---

## 📊 Mermaid Diagram - GitHub Architecture

![Github Architecture](github-architecture.svg)

---
