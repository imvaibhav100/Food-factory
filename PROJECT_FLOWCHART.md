# FoodFactory Project Flowchart

Below is a high-level flowchart of the FoodFactory Spring Boot application, showing user and admin flows, template interactions, backend layers, and MySQL data persistence.

```mermaid
flowchart TD

%% Client
U[User (Browser)]

%% UI Templates
subgraph UI[Thymeleaf Templates (resources/templates)]
  T1[Home.html]
  T2[Products.html]
  T3[Login.html]
  T4[UserDashboard.html]
  T5[Admin_Page.html]
  T6[Add_Product.html]
  T7[Order_success.html]
  T8[About_modern.html]
end

%% Static Assets
subgraph Static[Static Assets (resources/static)]
  S1[CSS / JS / Images]
end

%% Backend Layers
subgraph Backend[Spring Boot (com.example.demo)]
  C[Controllers]
  S[Services]
  R[Repositories]
end

%% Database
subgraph DB[MySQL (foodfactory)]
  D[(Tables)]
end

%% Client → UI
U --> T1
U --> T2
U --> T3
U --> T8

%% UI → Backend
T1 --> C
T2 --> C
T3 --> C
T4 --> C
T5 --> C
T6 --> C
T7 --> C

%% Backend layering
C --> S
S --> R
R --> D

%% Login Flow
C -->|Login Request| Auth[Authenticate User]
Auth -->|Valid| T4
Auth -->|Invalid| T3

%% Browse Products
C -->|Browse Products| P[List Products]
P --> T2

%% Buy / Order Flow
C -->|Buy Product| B[Create Order]
B --> S --> R --> D --> T7

%% Admin Flow
C -->|Admin Login| AAuth[Authenticate Admin]
AAuth -->|Valid| T5
T5 --> M[Manage Users / Products]
M --> T6
M --> S --> R --> D

%% Assets used by templates
UI --- S1

%% Server information
note over Backend: Runs on port 8080
```

## Notes
- Uses Spring Boot MVC: Controllers → Services → Repositories → MySQL.
- UI rendered with Thymeleaf templates; styles and scripts from `resources/static`.
- Key user paths: Home, Products, Login, Buy (Order), Dashboard.
- Key admin paths: Admin page, Add/Update products/users.
