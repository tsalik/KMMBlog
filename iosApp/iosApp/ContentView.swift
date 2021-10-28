import SwiftUI
import shared

struct ContentView: View {
    @State var loaded = false
    @State var postDescriptions: [PostDescription] = []
    
    var body: some View {
        ZStack {
            if !loaded {
                ProgressView()
                    .accessibilityIdentifier("loading")
            }
            if loaded && !postDescriptions.isEmpty {
                ZStack {
                    BlogEntries(blogEntries: postDescriptions.map({ postDescription in
                        return BlogEntry(id: UUID(), postDescription: postDescription)
                    }))
                }
            } else if loaded && postDescriptions.isEmpty {
                Text("No posts yet")
                    .accessibilityIdentifier("noPostsMessage")
            }
            
        }.onAppear { loadPosts() }
    
    }
    
    func loadPosts() {
        let url = Constants.hostname()
        let catalogPosts = CatalogPosts(repository: BlogApi(hostname: url))
        let _: Void = catalogPosts.byDescendingDate { (posts: [PostDescription]?, error: Error?) in
            loaded = true
            if posts != nil {
                postDescriptions = posts!
            } else if error != nil {
                print(error?.localizedDescription ?? "")
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
