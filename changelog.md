# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.11.0] - 2026-02-04

### Added

- add Jackson serialization support to I18nMessage

## [0.10.0] - 2026-01-29

### Added

- rename getProviderByName to getProviderByType

## [0.9.1] - 2026-01-29

### Fixed

- ensure list getters return empty list instead of null

## [0.9.0] - 2026-01-07

### Added

- expose attribute-only validation API

## [0.8.0] - 2026-01-07

### Added

- add validateAttribute method to ValidationEngine interface

### Fixed

- add Maven cache to CI workflow to prevent rate limiting

## [0.7.2] - 2025-08-22

### Fixed

- retrieve valid tag and commits for github release

## [0.7.1] - 2025-08-22

### Fixed

- using previous tag in maven publish

## [0.7.0] - 2025-08-22

### Added

- migrate to gitlab ci to github actions
- renaming Directory manager to Identity Manager

### Fixed

- bot push release
- separate workflow and fix them
- add missing url in pom.xml

## [0.6.0] - 2025-07-07

### Added

- provide entity mapping spring component
- add attribute to manage null value during entity mapping

## [0.5.0] - 2025-07-02

### Added

- add service for jinja rendering
- make provider plugin can use TaskExecutionContext
- add option disabledRoutes on entity configuration

## [0.4.0] - 2025-06-26

### Added

- setup publishing to linagora nexus

### Fixed

- remove central publishing plugin for Nexus-only deployment

## [0.3.0] - 2025-06-25

### Added

- add new plugin to manage authorization

## [0.2.0] - 2025-06-24

### Added

- add metadata for routes and entities

### Fixed

- add missing doc for access and access property declaration
- add missing method to retrieve configuration for validation plugin

## [0.1.0] - 2025-06-23

### Added

- add base of all plugins
- add global entity for all plugins
- add plugin configuration management
- add class to manage default exception
- add default classes for internationalization
- setup maven projet

### Fixed

- ensure correct branch is pushed during release


[0.11.0]: https://github.com/linagora/linid-im-api-corelib/compare/v0.10.0...v0.11.0
[0.10.0]: https://github.com/linagora/linid-im-api-corelib/compare/v0.9.1...v0.10.0
[0.9.1]: https://github.com/linagora/linid-im-api-corelib/compare/v0.9.0...v0.9.1
[0.9.0]: https://github.com/linagora/linid-im-api-corelib/compare/v0.8.0...v0.9.0
[0.8.0]: https://github.com/linagora/linid-im-api-corelib/compare/v0.7.3...v0.8.0
[0.7.3]: https://github.com/linagora/linid-im-api-corelib/compare/v0.7.2...v0.7.3
[0.7.2]: https://github.com/linagora/linid-im-api-corelib/compare/v0.7.1...v0.7.2
[0.7.1]: https://github.com/linagora/linid-im-api-corelib/compare/v0.7.0...v0.7.1
[0.7.0]: https://github.com/linagora/linid-im-api-corelib/compare/v0.6.0...v0.7.0
[0.6.0]: https://github.com/linagora/linid-im-api-corelib/compare/v0.5.0...v0.6.0
[0.5.0]: https://github.com/linagora/linid-im-api-corelib/compare/v0.4.0...v0.5.0
[0.4.0]: https://github.com/linagora/linid-im-api-corelib/compare/v0.3.0...v0.4.0
[0.3.0]: https://github.com/linagora/linid-im-api-corelib/compare/v0.2.0...v0.3.0
[0.2.0]: https://github.com/linagora/linid-im-api-corelib/compare/v0.1.0...v0.2.0
[0.1.0]: https://github.com/linagora/linid-im-api-corelib/releases/tag/v0.1.0
