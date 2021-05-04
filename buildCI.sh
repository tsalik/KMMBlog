#!/bin/zsh
# Build and test(unit and instrumentation) for Android
./gradlew clean build :androidApp:connectedAndroidTest

# Build and test for iOS
xcodebuild build-for-testing -project iosApp/iosApp.xcodeproj -scheme iosAppUITests -sdk iphonesimulator -destination 'platform=iOS Simulator,name=iPhone 12,OS=14.4'
xcodebuild test-without-building -project iosApp/iosApp.xcodeproj -scheme iosAppUITests -destination 'platform=iOS Simulator,name=iPhone 12,OS=14.4'