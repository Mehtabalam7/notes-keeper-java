# Keeper — Notes App (Mini Java Full-Stack Project)

A simple Google-Keep-style notes application built with **JSP, Servlets, and JPA/Hibernate**, following the **MVC architecture**. This was built as a hands-on practice project to apply core Java web development concepts learned in class — not a tutorial clone, but something built (and debugged) from scratch.

---

## Why this project

This is intentionally a **small** project. The goal wasn't to build something complex — it was to actually *practice* the full request-response cycle (form → servlet → database → back to the view) end to end, on my own, and fix the real errors that come up along the way (connection pool issues, JPA mapping errors, layout bugs, etc.) instead of just following a tutorial.

This is a starting point. Bigger, more feature-rich projects are next.

---

## Tech Stack

- **Frontend:** HTML, CSS, minimal JavaScript
- **View layer:** JSP (JSTL + scriptlets)
- **Backend:** Java Servlets (Controller layer)
- **Persistence:** JPA (Hibernate as provider) using `EntityManager`
- **Database:** MySQL
- **Architecture:** MVC — Servlet (Controller) → JSP (View) → DAO/JPA (Model)
- **Build tool:** Maven
- **IDE:** Eclipse IDE for Enterprise Java and Web Developers
- **Server:** Apache Tomcat 9

---

## Features

- Add a new note (title + content)
- View all saved notes, displayed as cards in a responsive grid
- Delete a note
- Data persisted in MySQL via JPA (`EntityManager`, not raw JDBC)

---

## Project Structure

```
Keeper/
├── pom.xml
├── src/main/java/com/keeper/
│   ├── model/         → Keeper.java (JPA entity)
│   ├── dao/            → KeeperDao.java (save / getAll / delete)
│   ├── util/            → JpaUtil.java (shared EntityManagerFactory)
│   └── controller/      → NotesServlet.java
└── src/main/webapp/
    ├── WEB-INF/
    │   ├── web.xml
    │   └── list.jsp     → main view (form + notes grid)
    └── style.css
```

---

## Setup Instructions

### 1. Prerequisites
- JDK 11+
- Eclipse IDE for Enterprise Java and Web Developers
- Apache Tomcat 9 (registered in Eclipse under Servers)
- MySQL Server + MySQL Workbench (or CLI)

### 2. Set up the database
Create the database in MySQL:
```sql
CREATE DATABASE keeper_db;
```
The `keeper` table is created automatically by Hibernate (`hibernate.hbm2ddl.auto=update`), so no manual table creation is needed.

### 3. Import into Eclipse
1. `File → Import → Maven → Existing Maven Projects`
2. Browse to the cloned project folder → Finish
3. Let Maven download dependencies (Hibernate, MySQL connector, etc.) — needs internet on first run

### 4. Configure the database connection
Open `src/main/resources/META-INF/persistence.xml` and set your own local MySQL credentials:
```xml
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/keeper_db?useSSL=false&amp;serverTimezone=UTC"/>
<property name="jakarta.persistence.jdbc.user" value="your_username_here"/>
<property name="jakarta.persistence.jdbc.password" value="your_password_here"/>
```
> ⚠️ Credentials in this repo are placeholders — put in your own local MySQL username/password before running.

### 5. Run it
1. Right-click the project → `Run As → Run on Server` → choose Tomcat
2. Open: `http://localhost:8080/Keeper/`
3. Add a note using the form, it'll show up as a card below

---

## Things I ran into while building this (and fixed)

- `EntityManagerFactory` being created fresh on every DB call instead of being shared → caused a **"Too many connections"** MySQL error. Fixed by moving it to a single shared instance in `JpaUtil`.
- Forgetting `return;` after a `response.sendRedirect()` inside an `if` block → caused `IllegalStateException: Cannot forward after response has been committed`.
- Notes list showing in the wrong order → fixed by adding `ORDER BY` to the JPQL query (no `ORDER BY` means the database doesn't guarantee row order).
- Note cards not lining up in a proper grid → a duplicated/misnamed CSS class was applying `flex` layout rules to individual cards instead of their container. Switched the container to CSS Grid to fix it properly.

## Possible Next Steps
- Add an **Edit note** feature (using `em.merge()`)
- Add note timestamps ("created on")
- Add basic search/filter by title
- Move to Spring Boot + REST API + a JS frontend as the next, bigger project

---

## Author
Built while learning full-stack Java (JSP, Servlets, Hibernate/JPA) — this is a practice project, and the first of hopefully many bigger ones.