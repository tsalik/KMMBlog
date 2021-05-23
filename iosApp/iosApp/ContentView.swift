import SwiftUI
import shared

struct ContentView: View {
    @State var greeting = Greeting().greeting()
    let greet = Greeting().greeting()
    
    var body: some View {
        Text(greeting)
            .accessibilityIdentifier("greeting")
        
        let _: Void = BlogApi(hostname: "http://localhost:8080/").posts { (postResponse: PostResponse?, error: Error?) in
            if (postResponse != nil) {
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
