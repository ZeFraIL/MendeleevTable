# Element Class Description

## 1. General Information
*   **Class Name:** `Element`
*   **Type:** Normal Class (POJO) — A simple data object.
*   **Purpose:** Describes a single chemical element from the periodic table. It is a "blueprint" or "template" for storing data: element name, number, mass, and image URL.
*   **Interaction:** Created in `MainActivity`, stored in a list, and passed to `ElementDetailActivity`.

## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `symbol` | `String` | Chemical symbol (e.g., "H", "O"). | List and Detail display |
| `name` | `String` | Full name (e.g., "Hydrogen"). | List and Detail display |
| `atomicNumber` | `int` | Atomic number (order in the table). | Sorting and display |
| `atomicMass` | `double` | Atomic mass. | Detail description |
| `imageUrl` | `String` | Image URL from the internet. | Image loading in the adapter |

## 3. Methods

### Method: Constructor `Element`
*   **Type:** `public`
*   **What it does:** Fills the object fields with data upon creation.
*   **Parameters:** Accepts all 5 parameters (symbol, name, number, mass, link).

### Methods: Getters (`getSymbol`, `getName`, etc.)
*   **Type:** `public`
*   **Purpose:** Fields are `private` (encapsulation). Other classes must use these methods to access the data.

## 6. Interaction with other components
*   **Serializable:** The class implements the `Serializable` interface. This allows the object to be converted into bytes so it can be sent between screens (Activities) via an `Intent`.

## 7. General Logic
This is just a container. If we need to pass information about "Gold", we create an object `new Element("Au", "Gold", 79, ...)`. Now this "Gold" object can be sent across the app as a single package.

## 8. Simple Explanation
`Element` is like a **form** or **profile**. It has lines like "Name", "Surname", "Age". The form itself is the class. A filled-out form for a specific person (e.g., Hydrogen) is an **object** of the class. The app creates 118 of these forms and shows them to us.

---
⚠️ **Bug Note:** `ElementDetailActivity` uses `getParcelableExtra`, but this class implements `Serializable`. This is a mismatch and should be fixed by using `getSerializableExtra` or making the class `Parcelable`.
