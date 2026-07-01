# ElementAdapter Class Description

## 1. General Information
*   **Class Name:** `ElementAdapter`
*   **Type:** Adapter — A mediator.
*   **Purpose:** Responsible for filling the `RecyclerView`. It takes data from the `Element` list and "inserts" it into visual cells (cards) visible on the screen.
*   **Interaction:** Works inside `MainActivity` and uses the `item_element.xml` layout.

## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `elementList` | `ArrayList<Element>` | The list of data to be displayed. | Throughout the class |

## 3. Methods

### Method: `onCreateViewHolder`
*   **What it does:** "Inflates" the XML file for one cell (`item_element.xml`) and turns it into a Java object. Creates an empty card.

### Method: `onBindViewHolder`
*   **What it does:** Takes data from `elementList` at a specific `position` and writes it into the card's text fields. It also starts image loading.

### Class: `ElementViewHolder`
*   **Purpose:** A "holder" for UI elements. It finds `TextViews` and `ImageViews` once so they don't have to be searched for repeatedly, saving processing power.

### Class: `LoadImageTask`
*   **Type:** `AsyncTask` (Deprecated).
*   **Purpose:** Downloads images from the internet in the background so the app doesn't freeze.

## 8. Simple Explanation
Imagine `RecyclerView` is a **library shelf**, and the `Adapter` is the **librarian**. The librarian takes raw books (data), puts them in nice covers (XML layout), and places them on the shelf for the reader to see. If there's no more space, they reuse an old cover for a new book, just changing the title.

---
⚠️ **Critical Note:** `AsyncTask` is deprecated in modern Android development. Using libraries like **Coil** or **Glide** is the recommended way to handle image loading.
