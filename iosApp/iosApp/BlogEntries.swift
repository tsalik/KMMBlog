//
//  BlogEntryRow.swift
//  iosApp
//
//  Created by Kostas Tsalikis on 11/9/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct BlogEntries: View {
    var blogEntries: [BlogEntry]
    
    var body: some View {
        List(blogEntries, id: \.id) { blogEntry in
            VStack {
                HStack {
                    Text(blogEntry.postDescription.title)
                        .accessibilityIdentifier("title")
                    Spacer()
                    Text(blogEntry.postDescription.publishDate)
                        .accessibilityIdentifier("date")
                }
                Spacer()
                Text(blogEntry.postDescription.summary)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .accessibilityIdentifier("description")
            }
        }
        .accessibilityIdentifier("blog_entries")
    }
}


struct BlogEntries_Previews: PreviewProvider {
    static var previews: some View {
        BlogEntries(blogEntries: [BlogEntry(id: UUID(), postDescription: PostDescription(title: "Blogging With Hugo", path: "first/post", summary: "How I built this blog", publishDate: "2018-10-21"))])
    }
}


struct BlogEntry: Identifiable {
    let id: UUID
    let postDescription: PostDescription
}
