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
    
    private let app = XCUIApplication()
        
    func browsePosts() {
        app.launchEnvironment = ["host":"http://localhost:8080/"]
        app.launch()
    }
    
    func greetingIsShowing() {
        let greeting = app.staticTexts["greeting"]
        XCTAssertTrue(greeting.waitForExistence(timeout: 2.0))
    }
    
    func showsNoPostsYet() {
        let greeting = app.staticTexts["greeting"]
        XCTAssertEqual("No posts yet", greeting.label)
    }
    
    func showsPostEntryWith(title: String, date: String, description: String) {
        let blogEntries = app.tables["blog_entries"]
        XCTAssertTrue(blogEntries.waitForExistence(timeout: 2.0))
        
        let firstElement = blogEntries.cells.element(boundBy: 0)
        XCTAssertEqual(title, firstElement.descendants(matching: .staticText)["title"].label)
        XCTAssertEqual(date, firstElement.descendants(matching: .staticText)["date"].label)
        XCTAssertEqual(description, firstElement.descendants(matching: .staticText)["description"].label)
    }
    
}
