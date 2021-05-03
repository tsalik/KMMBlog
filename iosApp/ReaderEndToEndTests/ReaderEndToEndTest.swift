//
//  ReaderEndToEndTests.swift
//  ReaderEndToEndTests
//
//  Created by Kostas Tsalikis on 5/3/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import XCTest

class ReaderEndToEndTest: XCTestCase {

    override func setUpWithError() throws {
        continueAfterFailure = false
    }

    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func test_proper_greeting_for_iOS() throws {
        // UI tests must launch the application that they test.
        let app = XCUIApplication()
        app.launch()

        // Use recording to get started writing UI tests.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
        let greetingText = app.staticTexts["greeting"]
        XCTAssertTrue(greetingText.label.contains("iOS"))
    }

}
