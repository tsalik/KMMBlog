#!/bin/sh
# Build and test(unit and instrumentation) for Android
./gradlew clean :androidApp:connectedAndroidTest

# Build and test for iOS
xcodebuild build-for-testing -workspace iosApp/iosApp.xcworkspace -scheme iosAppUITests -sdk iphonesimulator -destination 'platform=iOS Simulator,name=iPhone 12,OS=14.4'
xcodebuild test-without-building -workspace iosApp/iosApp.xcworkspace -scheme iosAppUITests -destination 'platform=iOS Simulator,name=iPhone 12,OS=14.4'