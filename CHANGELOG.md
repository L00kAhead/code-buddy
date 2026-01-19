<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Code Buddy Changelog

## [Unreleased]
### Added
- No unreleased changes yet

---

## [1.0.0] - 2026-01-17
### Added
- Initial scaffold created from the [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template)
- Animated cat companion that walks along the bottom of the IDE window
- Smooth frame based animation using individual PNG frames
- Project level service to manage pet state and lifecycle
- Tool window with controls to pause and resume the cat’s movement
- Option to completely enable or disable the cat overlay
- Automatic startup integration so the cat appears when a project opens

### Fixed
- Corrected cat overlay positioning to stay aligned with the bottom of the IDE
- Fixed edge to edge walking behavior when resizing the IDE window
- Resolved lifecycle cleanup warnings when closing a project
