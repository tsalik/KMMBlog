import SwiftUI
import shared

struct ContentView: View {
    @State var greeting = Greeting().greeting()
    @State var loaded = false
    @State var postDescriptions: [PostDescription] = []
    let greet = Greeting().greeting()
    
    var body: some View {
        ZStack {
            Text(greeting)
                .accessibilityIdentifier("greeting")
            
            if loaded && !postDescriptions.isEmpty {
                ZStack {
                    BlogEntries(blogEntries: postDescriptions.map({ postDescription in
                        return BlogEntry(id: UUID(), postDescription: postDescription)
                    }))
                }
            }
            
        }.onAppear { loadPosts() }
    
    }
    
    func loadPosts() {
        let url = Constants.hostname()
        let catalogPosts = CatalogPosts(repository: BlogApi(hostname: url))
        let _: Void = catalogPosts.byDescendingDate { (posts: [PostDescription]?, error: Error?) in
            loaded = true
            if (posts?.isEmpty != nil && posts!.isEmpty) {
                greeting = "No posts yet"
            } else {
                greeting = "wut happened"
                postDescriptions = posts!
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
