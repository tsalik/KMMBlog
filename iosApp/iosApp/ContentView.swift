import SwiftUI
import shared

struct ContentView: View {
    @State var greeting = Greeting().greeting()
    let greet = Greeting().greeting()
    
    var body: some View {
        let _: Void = BlogApi().posts { (postResponse: PostResponse?, error: Error?) in
            if (postResponse != nil) {
                greeting = "not empty"
            } else {
                greeting = "wut happened"
                print(error?.localizedDescription ?? "")
            }
        }
        
        Text(greeting)
            .accessibilityIdentifier("greeting")
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
