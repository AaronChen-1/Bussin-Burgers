# 🍔 Bussin Burgers POS System

[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)

Bussin Burgers is a console-based point-of-sale (POS) application for a custom burger restaurant.  
Customers can create personalized orders with burgers, drinks, and sides, including free and premium toppings, and the app generates a detailed receipt in a timestamped text file.

---

## 📋 Features

### Ordering
- Add burgers with:
  - Choice of bun type (Regular, Whole Wheat, Lettuce Wrap)  
  - Regular toppings (free)  
  - Premium toppings (extra cost)  
  - Special options (e.g., toasted bun)  
- Add drinks with size options (Small, Medium, Large)  
- Add sides (e.g., chips, garlic knots)

### Order Management
- Validates orders (must contain at least 1 item)  
- Checkout displays a detailed receipt and total cost  
- Receipts saved in `receipts/` folder using timestamped filenames  


---

## ⚙️ Technical Details
- Built in **Java**, console-based interface  
- **Object-Oriented Design**:
  - `MenuItem` (abstract class)  
  - `Burger`, `Drink`, `Side` extend `MenuItem`  
  - `Order` handles multiple items and receipt generation  
  - Enums for bun types, drink types, and toppings  
- Utilities:
  - `TimeUtil` for timestamp generation  
  - `ReceiptWriter` to save receipts as `.txt` files  

---

## 🚀 Getting Started

1. Clone the repository:  
   ```bash
   git clone https://github.com/AaronChen-1/bussin-burgers.git
2. Open the project in IntelliJ IDEA or any Java IDE
3. Run the Main class
4. Follow on-screen prompts to create orders

---
## System Design

### Class Diagram

<img width="1080" height="720" alt="image" src="https://github.com/user-attachments/assets/b192743c-79f3-448e-a9a2-94a0dd1e8b7f" />

---

## Project Structure

```
BussinBurgers/
│
├─ src/
│   └─ main/
│       └─ java/
│           └─ com/
│               └─ bussinburgers/
│                   ├─ Main.java                
│                   │
│                   ├─ models/                 
│                   │   ├─ MenuItem.java        
│                   │   ├─ Burger.java
│                   │   ├─ Drink.java
│                   │   ├─ Side.java            
│                   │   └─ Order.java
│                   │
│                   ├─ models/enums/          
│                   │   ├─ BunType.java
│                   │   ├─ DrinkType.java
│                   │   ├─ DrinkSize.java
│                   │   ├─ RegularTopping.java
│                   │   └─ PremiumTopping.java
│                   │
│                   ├─ ui/                       
│                   │   └─ UserInterface.java
│                   │
│                   └─ util/                     
│                       ├─ ReceiptWriter.java
│                       └─ TimeUtil.java
│
├─ receipts/                                     
│
├─ README.md                                    
├─ .gitignore                                    
└─ pom.xml
```

---

## Usage Guide

### Screen Navigation

```
Home Screen
    ↓
    1) New Order
    ↓
Order Screen
    ↓
    ├─ 1) Add Burger → [Customization Wizard]
    ├─ 2) Add Drink → [Size & Type Selection]
    ├─ 3) Add Side → [Type Selection]
    ├─ 4) Checkout → [Confirmation & Receipt]
    └─ 0) Cancel Order
```

### Example Order Session

```
1. Start Application
2. Select "1) New Order"
3. Select "1) Add Burger"
   - Choose Regular
   - Add Lettuce
   - Add Tomato
   - Add Onions
   - Add Cheese, Bacon
   - Add bussin sauce
4. Select "2) Add Drink"
   - Choose Coca-Cola
   - Choose Large size
5. Select "4) Checkout"
6. Confirm Order
7. Receipt saved to receipts/20231114-143052.txt
```
## Picture showcase of App

<img width="362" height="236" alt="image" src="https://github.com/user-attachments/assets/4a63d41a-7eb5-49f9-a45b-6ec6ca4d20cb" />

--

<img width="187" height="167" alt="image" src="https://github.com/user-attachments/assets/a77e572e-b2af-46de-9ead-be054bd058fd" />

--

<img width="231" height="111" alt="image" src="https://github.com/user-attachments/assets/7afde9d7-672b-4f0a-ad53-da372068fc27" />

--

<img width="369" height="381" alt="image" src="https://github.com/user-attachments/assets/84dbfcb5-4b33-4e84-874a-e9d85e9ca340" />

--

<img width="351" height="209" alt="image" src="https://github.com/user-attachments/assets/42c94be0-dcbb-443e-99ab-c289412fdbb8" />

---
## 🧾 Receipt Example
```
        Bussin Burgers
The Ultimate Burger Experience
----------------------------------
Order ID: 20251114-143210
Date/Time: 2025-11-14 14:32:10
----------------------------------
Order Items:
1) Burger (Regular Bun)
   Regular Toppings: Lettuce Tomato
   Premium Toppings: Cheese
   Special Option Included
   Price: $6.75
2) Medium Cola - $2.25
----------------------------------
Total: $9.00

Thank you for dining with us!
Visit us at bussinburgers.com
```
---

# Interesting Code Highlights



### 1.Combo suggester 
This app features a way to recommend a drink or side if the user only added a burger. This makes sense right after the user adds a burger, since combos usually revolve around burgers.

```
    private void suggestCombo(Order order) {
        boolean hasBurger = order.getItems().stream().anyMatch(i -> i instanceof Burger);
        boolean hasDrinkOrSide = order.getItems().stream().anyMatch(i -> i instanceof Drink || i instanceof Side);

        if (hasBurger && !hasDrinkOrSide) {
            System.out.println("\n Suggestion: Add a drink or side to make it a complete combo!");
        }
    }
```

### 2. Calorie window
This shows the user how much calories right before the order whether they want to check out or cancel and retry.

```
    private void showCalories(Order order) {
        System.out.println("\n̶=̶=̶=̶ CALORIE SUMMARY ̶=̶=̶=̶ ̶=̶=̶=̶ ̶=̶=̶=̶");
        for (MenuItem item : order.getItems()) {
            if (item instanceof Burger burger) {
                int totalCalories = 0;

                for (RegularTopping rt : burger.getRegularToppings()) {
                    totalCalories += rt.getCalories();
                }

                for (PremiumTopping pt : burger.getPremiumToppings()) {
                    totalCalories += pt.getCalories();
                }

                // Display burger summary with calories
                System.out.println("Burger - Total Calories " + totalCalories);
            } else if (item instanceof Drink drink) {
                System.out.println(drink.getDescription().trim()
                        + " - " + drink.getCalories() + " cal");
            } else if (item instanceof Side side) {
                System.out.println(side.getDescription().trim()
                        + " - " + side.getType().getCalories() + " cal");
            }
        }
        System.out.println("=̶=̶=̶=̶̶=̶=̶=̶=̶̶=̶=̶=̶=̶̶=̶=̶=̶=̶̶=̶=̶=̶=̶=̶=̶ ̶=̶=̶=̶ ̶=̶=̶=̶");
    }
```
