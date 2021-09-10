import SwiftUI
import shared

struct ContentView: View {
    @State var greeting = Greeting().greeting()
    let greet = Greeting().greeting()
    
    var body: some View {
        Text(greeting)
            .accessibilityIdentifier("greeting")
        
        let url = Constants.hostname()
        let catalogPosts = CatalogPosts(repository: BlogApi(hostname: url))
        let _: Void = catalogPosts.byDescendingDate { (posts: [PostDescription]?, error: Error?) in
            if (posts?.isEmpty != nil && posts!.isEmpty) {
                greeting = "No posts yet"
            } else {
                greeting = "wut happened"
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
