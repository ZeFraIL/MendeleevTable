# MainActivity Class Description

## 1. General Information
*   **Class Name:** `MainActivity`
*   **Type:** Activity — The main window of the application.
*   **Purpose:** It is the entry point and primary screen. This class handles downloading the list of chemical elements from the internet, processing it, and displaying it to the user in a list format.
*   **Interaction:** 
    *   Uses `ElementAdapter` to display data.
    *   Creates a list of `Element` objects.
    *   Launches `ElementDetailActivity` when an item is selected.

## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `recyclerView` | `RecyclerView` | A container list to display items on the screen. | `onCreate` |
| `adapter` | `ElementAdapter` | A "bridge" between data and the list. Manages row creation. | `onCreate`, `parseJSON` |
| `elementList` | `ArrayList<Element>` | A dynamic array that stores all loaded elements. | Throughout the class |
| `executorService` | `ExecutorService` | A service for running tasks in a background thread (so the app doesn't "freeze"). | `loadElementsFromJSON`, `onDestroy` |

## 3. Methods

### Method: `onCreate`
*   **Type:** `protected`
*   **Return value:** `void` (nothing)
*   **Parameters:** `Bundle savedInstanceState` (state of the application).
*   **What it does:** Performs initial setup: sets the layout, finds the `RecyclerView` in the layout, initializes the adapter, and starts the data loading process.
*   **When called:** The system calls it once when the activity window is created.
*   **Important:** Long operations (like network loading) must not be done here, or the screen will remain blank.

### Method: `loadElementsFromJSON`
*   **Type:** `private`
*   **What it does:** 
    1. Creates a "task" for a background thread.
    2. Connects to GitHub via URL.
    3. Downloads JSON text (a specific data format).
    4. Passes the text to the `parseJSON` method.
*   **When called:** From `onCreate` immediately after startup.

### Method: `parseJSON`
*   **Type:** `private`
*   **Parameters:** `String json` (text in JSON format).
*   **What it does:** Iterates through the text, extracts the name, symbol, mass, and image link for each element, creates an `Element` object, and adds it to the list. Finally, it tells the adapter to update the screen.
*   **Important:** UI updates must happen on the main thread (using `runOnUiThread`).

## 4. Lifecycle
*   **`onCreate()`**: Window creation, list setup.
*   **`onDestroy()`**: Called when the app is closed. We stop the `executorService` here to prevent memory leaks.

## 5. UI Interaction
*   **Elements:** `RecyclerView` (dynamic list).
*   **Connection:** Finding the element via `findViewById(R.id.recyclerView)`.

## 8. Simple Explanation
Think of `MainActivity` as a **Restaurant Manager**.
1. They prepare the dining hall (set up `RecyclerView`).
2. They call the warehouse (internet) and order ingredients (`loadElementsFromJSON`).
3. When ingredients arrive, they check the invoice and pack them into boxes (`parseJSON`).
4. Finally, they give the ingredients to the **Chef** (`Adapter`) to plate them beautifully for the guests.

---
⚠️ **Code Tip:** Network requests in Android MUST run in a background thread to avoid ANR (Application Not Responding) errors.
