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

## 🚀 Getting Started

1. Clone the repository:  
   ```bash
   git clone https://github.com/<your-username>/bussin-burgers.git
2. Open the project in IntelliJ IDEA or any Java IDE
3. Run the Main class
4. Follow on-screen prompts to create orders

---
## System Design

### Class Diagram

<img width="1080" height="720" alt="image" src="https://github.com/user-attachments/assets/b192743c-79f3-448e-a9a2-94a0dd1e8b7f" />

