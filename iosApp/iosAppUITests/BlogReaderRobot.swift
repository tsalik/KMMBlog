//
//  BlogReaderRobot.swift
//  iosAppUITests
//
//  Created by Kostas Tsalikis on 28/9/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import XCTest

class BlogReaderRobot {
    
    private let app = XCUIApplication()
    
    func browsePosts() {
        app.launchEnvironment = ["host":"http://localhost:8080/"]
        app.launch()
    }
    
}
