# 🚗 Car Rent App
A desktop car rental management system built in Java using Swing.
The application allows users to manage vehicles, customers, and system users as well as handling rentals with persistent data storage.


## 📑 Table of Contents
- [Features](#-features)
- [Tech Stack](#tech-stack)
- [Usage](#-usage)
- [The Process](#-the-process)
- [Future Improvements](#-future-improvements)
- [Code Highlights](#-code-highlights)
- [Installation](#-installation)
- [Contributing](#-contributing)
- [License](#-license)


## ✨ Features
- Vehicle inventory management
- Customer management system
- User (admin) management with login authentication
- Rental system with date selection
- Unique rental ID generation
- Rental history tracking (who rented what and when)
- Input validation for all entities
- Desktop GUI built using Java Swing
- Multi-language support (English / Greek) using internationalization (I18n)
- Persistent storage using binary files (.bin)


## 🛠️ Tech Stack <a name="tech-stack"></a>
- **Language:** Java
- **GUI:** Java Swing
- **Tools:** IntelliJ IDEA, Git, GitHub


## 🚀 Usage
A desktop application for managing all aspects of a car rental business.
The application is controlled through a menu-driven interface, allowing users to navigate between different management sections (Users, Customers, Vehicles, Rentals).

- Log in using valid user credentials
- Manage vehicles, customers, and users
- Create and track rentals
- View rental history
- All changes are automatically saved locally


## 📍 The Process
After completing several smaller projects, I wanted to build a larger, more complete application that combined multiple concepts such as GUI development, data management, and application logic. A car rental system was a good fit because it involves multiple entities and real-world interactions.

I started by designing the main application structure and creating the primary GUI frame with a navigation menu. I then implemented a login system to restrict access to authorized users.

Next, I developed the core entities of the application:
- Users (admins)
- Customers
- Vehicles

Each entity was implemented as a class with its own attributes and validation rules.

After defining the data models, I focused on building the business logic, including validation (e.g. email format, required fields) and CRUD operations (create, edit, delete).

Once the core functionality was stable, I implemented the rental system. This included:
- Linking customers to vehicles
- Selecting rental dates
- Generating unique IDs for each rental
- Storing rental history

I implemented data persistence using Java serialization. All entities are saved to `.bin` files when the application closes and loaded again when it starts, ensuring data is preserved between sessions.

Finally, I added internationalization (I18n) support, allowing the application to switch between English and Greek dynamically through the UI.

Throughout the project, I also documented the code using Javadoc to improve readability and maintainability.


**Key learnings:**
- Designing and structuring a multi-entity application
- Building a complete GUI application using Java Swing
- Implementing authentication systems (login logic)
- Working with object serialization and persistent storage
- Writing reusable and maintainable code across multiple classes
- Applying input validation and handling edge cases
- Designing and implementing internationalization (I18n) for dynamic language switching (English / Greek)
- Documenting code using Javadoc


## 🔧 Future Improvements
- Improve UI/UX design
- Add advanced filtering and search functionality
- Replace file storage with a database (SQL)
- Improve validation and error handling
- Add reporting/analytics (e.g. most rented vehicles)


## 💡 Code Highlights
- Full multi-entity system (Users, Customers, Vehicles, Rentals)
- Object-oriented design with clear separation of concerns
- Java Swing GUI with multiple interactive screens
- Login/authentication system implementation
- Rental system with relationships between entities
- Automatic persistence using Java serialization
- Input validation for safer data handling
- Internationalization (I18n) support with dynamic language switching
- Javadoc documentation for maintainability


## 🧾 How To Run


### 🔐 Login
- The application starts with a login window
- You must enter a valid username and password to access the system
- Only registered users (admins) can log in
- Default credentials:
  - Username: `admin`
  - Password: `admin`
  (You can delete this user after creating a new one)

---

### 🧭 Navigation Overview

The application is controlled through the top menu bar, which contains the following sections:

- **User**
  - Add User
  - Remove User
  - Sign Out

- **Customer**
  - Add Customer
  - Edit Customer
  - Search Customer
  - View Customer History

- **Vehicles**
  - Add Vehicle
  - Edit Vehicle
  - Search Vehicle
  - View Vehicle History

- **Rent**
  - Rent a Vehicle
  - Return a Vehicle
 
- **Language**
  - English
  - Greek

---

### 👤 Users (Admins)
You can:
- Add a new user
- Remove users

**Requirements:**
- Name, surname, username, email, password
- All fields must be filled
- Email must be unique and valid (contain `@`)
- Username must be unique

---

### 🧑 Customers
You can:
- Add new customers
- Edit customer information
- Search customers
- See customer history

**Requirements:**
- Name, surname, phone, email, ID
- All fields must be filled
- Phone must be valid (only digits)
- Email must be valid (contain `@`)
- ID must be unique and valid (only digits)

---

### 🚗 Vehicles
You can:
- Add vehicles
- Edit vehicle details
- Search vehicles
- See vehicle history

**Requirements:**
- ID, number plate, make, model, type, year of production, color
- All fields must be filled
- ID must be unique and valid (only digits)
- Number plate must be unique
- Year must be valid (4-digit number 1885-2026)

---

### 📅 Rentals
You can:
- Rent a vehicle to a customer
- Return a vehicle
- Select rental dates  
- View rental history  

**System behavior:**
- Each rental gets a unique ID  
- Rentals are linked to the current user and both customer and vehicle  
- All rental data is stored and can be viewed later  

---

### 🌐 Language Selection
- Use the **Language** menu in the top bar to switch between:
  - English
  - Greek
- The interface updates dynamically based on the selected language

---

### 📁 Data Persistence
- All data is automatically saved to `.bin` files  
- Data is loaded when the application starts  
- No manual saving is required  

---

## 💾 Installation

Follow these steps to clone and run the application locally. This guide works for **Linux, macOS, and Windows** (via Git Bash or WSL).

### 1. Clone the repository
Make sure you have git installed. Open your terminal or command prompt and run:

```bash

git clone https://github.com/XM-George/Car-Rent-App.git

cd Car-Rent-App

```

### 2. Open the project
You can open the project in 
- IntelliJ IDEA(recommended)
- Eclipse
- or any Java IDE

Make sure the **src** folder is marked as the source root if required by your IDE.

### 3. Run the application

#### Using an IDE:

Navigate to:

```bash

src/GUI/Main.java

```

and run the **Main** class directly.

#### Alternatively using the terminal

```bash

cd src
javac GUI/Main.java
java GUI.Main

```


## 🤝 Contributing
Contributions are welcome. Feel free to fork the repository and submit a pull request.


## 📜 License
This project is licensed under the MIT License. See the LICENSE file for details.

