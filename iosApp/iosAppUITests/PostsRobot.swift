//
//  PostsRobot.swift
//  iosAppUITests
//
//  Created by Kostas Tsalikis on 28/9/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import XCTest

class PostsRobot {
    
    private let app: XCUIApplication
    private let viewTimeout = 2.0
    private let noPostsMessage: XCUIElement
    private let progress: XCUIElement
    private let blogEntries: XCUIElement
    
    init() {
        app = XCUIApplication()
        progress = app.activityIndicators["loading"]
        noPostsMessage = app.staticTexts["noPostsMessage"]
        blogEntries = app.tables["blog_entries"]
    }
        
    func browsePosts() {
        app.launchEnvironment = ["host":"http://localhost:8080/"]
        app.launch()
    }
    
    func loadingIsShowing() {
        XCTAssertTrue(progress.exists)
        XCTAssertFalse(noPostsMessage.exists)
    }
    
    func showsNoPostsYet() {
        XCTAssertTrue(noPostsMessage.waitForExistence(timeout: viewTimeout))
        XCTAssertFalse(progress.exists)
        XCTAssertFalse(blogEntries.exists)
    }
    
    func showsPostEntryWith(title: String, date: String, description: String) {
        XCTAssertTrue(blogEntries.waitForExistence(timeout: viewTimeout))
        
        let firstElement = blogEntries.cells.element(boundBy: 0)
        XCTAssertEqual(title, firstElement.descendants(matching: .staticText)["title"].label)
        XCTAssertEqual(date, firstElement.descendants(matching: .staticText)["date"].label)
        XCTAssertEqual(description, firstElement.descendants(matching: .staticText)["description"].label)
        XCTAssertFalse(progress.exists)
        XCTAssertFalse(noPostsMessage.exists)
    }
    
}
