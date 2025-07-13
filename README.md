# Cap10 V2 - Amateur Football Platform

## Overview

Cap10 V2 is a comprehensive mobile application designed for amateur football players, built using **Compose Multiplatform (CMP)** and **Kotlin Multiplatform Mobile (KMM)**. This platform serves as a complete ecosystem for amateur football communities, enabling players to discover, book, and participate in football activities.

## 🏟️ Project Purpose

Cap10 is a startup company focused on revolutionizing the amateur football experience by providing a digital platform that connects players, teams, and facilities. The platform bridges the gap between casual football enthusiasts and organized play opportunities.

## 🚀 Key Features

### Stadium Management
- **Stadium Discovery**: Browse and explore available football stadiums in your area
- **Slot Booking**: Reserve time slots at preferred stadiums for games or practice sessions
- **Stadium Details**: View comprehensive information about facilities, amenities, and pricing

### Team & Player Profiles
- **Player Profiles**: Individual player profiles with statistics, performance history, and achievements
- **Team Profiles**: Complete team information including roster, match history, and team statistics
- **Cap10 System**: Unique player rating and reputation system within the community

### Match Management
- **Game Scheduling**: Organize and schedule matches between teams
- **Match Statistics**: Detailed statistics and analytics for games played
- **Game Results**: Track wins, losses, and performance metrics

### Community Features
- **Game Posts**: Players can post looking for games to join or teams to play with
- **League System**: Multiple leagues with different skill levels and competition formats
- **Social Features**: Connect with other players and teams in the community

### League Management
- **Multiple Leagues**: Various competitive and recreational leagues
- **League Details**: Comprehensive information about league structure, rules, and standings
- **Tournament Organization**: Support for tournament formats and brackets

## 🛠️ Technical Stack

### Frontend
- **Compose Multiplatform (CMP)**: Cross-platform UI framework for Android and iOS
- **Kotlin Multiplatform Mobile (KMM)**: Shared business logic and data layer
- **Material Design 3**: Modern, accessible UI components

### Architecture
- **MVVM Pattern**: Clean architecture with ViewModels and state management
- **Shared Code**: Maximum code reuse between Android and iOS platforms
- **Platform-Specific**: Native implementations where needed for platform-specific features

### Project Structure
```
composeApp/
├── src/
│   ├── commonMain/          # Shared code for all platforms
│   │   ├── kotlin/
│   │   │   └── com/alhussain/cap10/
│   │   │       ├── screens/     # UI screens
│   │   │       ├── viewModels/  # Business logic
│   │   │       ├── widgets/     # Reusable UI components
│   │   │       └── theme/       # Design system
│   │   └── composeResources/    # Shared resources
│   ├── androidMain/         # Android-specific code
│   └── iosMain/            # iOS-specific code
```

## 📱 Platform Support

- **Android**: Native Android application
- **iOS**: Native iOS application
- **Shared Logic**: Common business logic and data models
- **Unified UI**: Consistent user experience across platforms

## 🎯 Target Audience

- **Amateur Football Players**: Individuals looking to play organized football
- **Team Captains**: Players organizing teams and matches
- **Stadium Owners**: Facility managers offering booking services
- **League Organizers**: Administrators managing competitive leagues

## 🔄 Development Status

This is the **V2** iteration of the Cap10 platform, representing a complete rewrite and modernization of the original application. The new version leverages modern cross-platform technologies to provide a better user experience and more efficient development process.

## 📋 Getting Started

### Prerequisites
- Android Studio / IntelliJ IDEA
- Xcode (for iOS development)
- Kotlin Multiplatform Mobile plugin
- Compose Multiplatform dependencies

### Building the Project
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle dependencies
4. Run on Android device/emulator
5. For iOS: Open `iosApp/iosApp.xcodeproj` in Xcode

## 🤝 Contributing

This is a startup project. For contribution guidelines, please contact the development team.

## 📄 License

Proprietary software - All rights reserved by Cap10.

---

**Cap10 V2** - Connecting amateur football players, one game at a time. ⚽