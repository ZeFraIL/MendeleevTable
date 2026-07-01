# ElementDetailActivity Class Description

## 1. General Information
*   **Class Name:** `ElementDetailActivity`
*   **Type:** Activity — The detail screen.
*   **Purpose:** Displays detailed information about a specific selected chemical element: full name, symbol, number, and a large image.
*   **Interaction:** Receives data from `MainActivity` via an `Intent`.

## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `executorService` | `ExecutorService` | A pool of 4 threads for fast image loading. | `loadImageWithExecutor` |

## 3. Methods

### Method: `onCreate`
*   **What it does:** 
    1. Extracts the `Element` object from the "package" (`Intent`).
    2. Links variables to the screen's visual elements.
    3. Fills text fields with data.
    4. Calls the image loading method.

### Method: `loadImageWithExecutor`
*   **What it does:** Runs a background task to download the image from the URL. Once finished, it uses `imageView.post()` to return to the main thread and show the image.

## 4. Lifecycle
*   **`onCreate()`**: Data and screen initialization.
*   **`onDestroy()`**: Shuts down the `executorService`. This is vital to prevent memory leaks.

## 8. Simple Explanation
`ElementDetailActivity` is like a **magnifying glass**. On the main screen, we see many elements briefly. When we click on one, this "glass" opens full screen and shows us everything interesting about that specific element.

---
⚠️ **Improvement Tip:** Instead of manually managing an `ExecutorService` for images, using a library like Coil/Glide is better as it handles caching and memory management automatically.
