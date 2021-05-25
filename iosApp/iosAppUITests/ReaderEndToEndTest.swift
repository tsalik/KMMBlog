//
//  iosAppUITests.swift
//  iosAppUITests
//
//  Created by Kostas Tsalikis on 5/4/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import XCTest
import Embassy
import EnvoyAmbassador

class ReaderEndToEndTest: XCTestCase {
    
    private var eventLoop: EventLoop!
    private var router: Router!
    private var server: HTTPServer!
    private var eventLoopThreadCondition: NSCondition!
    private var eventLoopThread: Thread!

    override func setUpWithError() throws {
        // Put setup code here. This method is called before the invocation of each test method in the class.

        setupWebServer()
        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
    }

    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        server.stopAndWait()
        eventLoopThreadCondition.lock()
        eventLoop.stop()
        while eventLoop.running {
            if !eventLoopThreadCondition.wait(until: Date().addingTimeInterval(10)) {
                fatalError("Join eventLoopThread timeout")
            }
        }
    }

    func test_shows_no_posts_yet_for_empty_blog() throws {
        // UI tests must launch the application that they test.
        let networkRequestExpectation = XCTestExpectation(description: "network call")
        router["/posts/index.json"] = DelayResponse(JSONResponse(handler: { _ in
            networkRequestExpectation.fulfill()
            return [
                "data" : []
            ]
        }), delay: .none)
        
        let app = XCUIApplication()
        app.launch()

        // Use recording to get started writing UI tests.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
        
        wait(for: [networkRequestExpectation], timeout: 2.0)
        
    
        let exists = NSPredicate(block: { any, _ in
            let greeting = app.staticTexts["greeting"]
            return greeting.label.contains("No posts yet")
        })
        expectation(for: exists, evaluatedWith: app, handler: .none)
        
        waitForExpectations(timeout: 2, handler: .none)
    }
    
    private func setupWebServer() {
        eventLoop = try! SelectorEventLoop(selector: try! KqueueSelector())
        router = Router()
        server = DefaultHTTPServer(eventLoop: eventLoop, port: 8080, app: router.app)
        
        try! server.start()
        
        eventLoopThreadCondition = NSCondition()
        eventLoopThread = Thread(target: self, selector: #selector(runEventLoop), object: nil)
        eventLoopThread.start()
    }
    
    @objc private func runEventLoop() {
        eventLoop.runForever()
        eventLoopThreadCondition.lock()
        eventLoopThreadCondition.signal()
        eventLoopThreadCondition.unlock()
      }

}
