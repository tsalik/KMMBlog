import SwiftUI
import shared

struct ContentView: View {
    @State var greeting = Greeting().greeting()
    let greet = Greeting().greeting()
    
    let blog: Void = BlogApi().posts { (postResponse: PostResponse?, error: Error?) in
        if (postResponse != nil) {
            print("not empty")
        } else {
            print("wut happened" + error!.localizedDescription)
        }
    }
    
    var body: some View {
        Text(greet)
            .accessibilityIdentifier("greeting")
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
