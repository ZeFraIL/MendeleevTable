📱 Android Application Documentation: MendeleevTable (Level 10/10)
________________________________________
🧾 General Information
Project Name:
MendeleevTable
Author(s):
Zeev Fraiman
Date:
July 1, 2024
Language:
Java
Development Environment:
Android Studio
Android Version (minSdk / targetSdk):
28 / 34
________________________________________
🎯 Project Goals
• What task does the application solve?
The application provides convenient access to the Mendeleev periodic system of chemical elements, allowing users to view a list of elements with their main characteristics and images.

• Why is this task important?
The periodic table is a fundamental tool for students, scientists, and anyone interested in chemistry. A mobile application makes this data accessible anywhere and anytime.

• Target Audience
Students, chemistry teachers, researchers, and school students.
________________________________________
📌 Application Requirements
Functional Requirements
• Fetching up-to-date element data from a remote JSON resource.
• Displaying the list of elements as cards (RecyclerView).
• Displaying detailed information about the selected element in a separate window.
• Loading and displaying element images.

Non-functional Requirements
• Performance: Fast loading and smooth list scrolling.
• Usability: Intuitive interface in the Material Design style.
• Reliability: Handling network connection errors and correct data parsing.
________________________________________
🧠 General Architecture
• Selected Approach:
– MVC (Model-View-Controller)

• Why this approach was chosen:
For this project (viewing a list and details), the MVC architecture is optimal as it is simple to implement and doesn't overload the project with unnecessary abstractions for a small amount of logic.

• Main System Components:
– Model: The `Element` class, representing the entity of a chemical element.
– View: XML layouts (`activity_main.xml`, `item_element.xml`, `activity_element_detail.xml`).
– Controller: `MainActivity` and `ElementDetailActivity`, managing logic and user interaction.
________________________________________
🧩 UML Diagram (Required)
[MainActivity] –> [ElementAdapter]
[ElementAdapter] –> [Element]
[MainActivity] –> [Element]
[MainActivity] –> [ElementDetailActivity]
[ElementDetailActivity] –> [Element]

Explain:
- Why these packages are separated:
This project uses a basic package structure. All classes are located in the main package `zeev.fraiman.mendeleevtable`.
- How this helps scaling:
For further scaling, it is recommended to divide the code into packages like `data`, `ui`, `model`, which will allow for easy addition of new features such as a local database (Room) or search.
________________________________________
🧩 Detailed Class Description
📌 Class: MainActivity
Role:
The main screen of the application.
Responsibility:
Loading data from the network, initializing RecyclerView, handling transitions to the details screen.
Main Methods:
- onCreate() — UI initialization and starting data loading.
- loadElementsFromJSON() — Performing network requests via ExecutorService.
- parseJSON() — Processing the received string and creating Element objects.
Interaction with other classes:
Uses `ElementAdapter` to display the list and passes data to `ElementDetailActivity`.
________________________________________
📌 Class: ElementDetailActivity
Role:
Detailed information screen.
Why it is used:
For a detailed look at the characteristics of a specific chemical element.
________________________________________
📌 Class: ElementAdapter
Role:
The link between the data (ArrayList<Element>) and the graphical representation (RecyclerView).
________________________________________
🔄 Application Workflow Diagram
Describe the scenario:
1. The user launches the application.
2. `MainActivity` requests the JSON file from GitHub in a background thread.
3. After receiving a response, the data is parsed into a list of `Element` objects.
4. `ElementAdapter` updates the `RecyclerView` on the main screen.
5. Clicking an element opens `ElementDetailActivity`, where the data of the selected element is passed.
________________________________________
🎨 UI/UX Analysis
• Why the interface is designed this way:
A card design was chosen for the list to clearly separate information about each element. Using Material Design provides a familiar user experience.

• What principles are used:
– Simplicity: No extra elements, focus on data.
– Logic: Transition from a general list to details.
– Accessibility: Clear fonts and high-contrast images.

• What can be improved:
Add a search by name/symbol, filtering by groups, and a dark theme.
________________________________________
⚙️ Multithreading
Describe:
- Are these used:
- ExecutorService (in MainActivity and ElementDetailActivity)
- AsyncTask (in ElementAdapter for loading images)
• Why this method was chosen:
ExecutorService provides efficient execution of network operations outside the main (UI) thread, preventing freezes.

• How these are prevented:
– freezes (ANR): All network requests and parsing are performed in background threads.
– memory leaks: Using shutdown() for ExecutorService in the onDestroy() method.
________________________________________
💾 Data Management
• Where data is stored:
Data is loaded dynamically from a remote JSON file.

• Why this method was chosen:
This allows for always having an up-to-date list of elements without the need to update the application itself.

• How it is ensured:
– security: Data is stored in the cloud (GitHub).
– correctness: Using try-catch blocks during JSON parsing.
________________________________________
🌐 Networking (if any)
• How requests are performed:
Standard `HttpURLConnection` is used within `ExecutorService`.

• Error handling:
Checking the response code (HTTP 200) and displaying Toast messages in case of failure.

• What happens if there is no internet:
A Toast message about the data loading error is displayed.
________________________________________
🔐 Security (Basic Level)
• Is there sensitive data?
The application contains no user data or confidential information.

• How is it protected?
HTTPS protocol is used to retrieve data.
________________________________________
🧪 Testing
• What tests are there:
– Unit tests (basic JUnit tests)
– UI tests (Espresso - boilerplate in the project)
• What exactly is checked:
Correctness of creating an Element object.
________________________________________
🐞 Error Handling
• What errors are anticipated:
No network, JSON parsing errors, empty data.

• How the app reacts:
Informs the user via Toast notifications.
________________________________________
⚡ Performance
• Are there optimizations:
Using ViewHolder in the adapter for efficient reuse of UI components. Image caching (via Glide/Coil, if fully integrated).

• Where potential bottlenecks might be:
Loading a large number of high-resolution images on a slow internet connection.
________________________________________
🚀 Scalability
• How can the project be developed:
Add an interactive periodic table, chemistry quizzes, and multi-language support within the app.

• What functions are easy to add:
Search, sorting by atomic mass.
________________________________________
📊 Project Self-Assessment
Rate according to criteria:
Criterion	Score (1–10)
Architecture	8
Code	9
UI/UX	8
Reliability	9
Overall Level	9
________________________________________
🏁 Conclusion
• What turned out best:
Stable data loading and clean code.

• What was difficult:
Parsing nested JSON structures and working with images.

• What skills did you acquire:
Working with HTTP requests, multithreading in Android, using RecyclerView.
________________________________________
📎 Appendices
• Screenshots: In the screenshots folder (if any)
• Diagrams: UML Class Diagram
• Repository links: [GitHub Link]
