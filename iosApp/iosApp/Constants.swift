//
//  Constants.swift
//  iosApp
//
//  Created by Kostas Tsalikis on 5/30/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

struct Constants {
    
    private static let developmentHost = "http://192.168.1.7:1313/"
    
    static func hostname() -> String {
        if ProcessInfo.processInfo.environment["host"] != nil {
            return ProcessInfo.processInfo.environment["host"]!
        } else {
            return developmentHost
        }
    }
    
}
