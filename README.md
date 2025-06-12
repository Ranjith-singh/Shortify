# Shortify

## Overview
This Shortify project is a web application that allows users to convert long URLs into short, easy-to-manage links. It features a **Spring Boot backend**, a **React frontend**, and a **PostgreSQL database** hosted on **Neon Tech**. The backend is **containerized with Docker** and deployed on **Render**, while the frontend is hosted on **Netlify**.

---

## Architecture
- **Register Page** – Allows new users to sign up and manage their shortened URLs.
- **Login Page** – Enables existing users to log in and access their dashboard.
- **Dashboard Page** – Displays shortened URLs, user statistics, and profile details.

---

## How It Works
### 1. User Input
Users enter a long URL through the frontend.

### 2. Backend Processing
- The backend generates a **unique short link** for the URL.
- It stores the original and shortened URLs in the **PostgreSQL database**.

### 3. Short URL Redirection
- When a user accesses a short URL, the backend retrieves the **original URL** and redirects them.

---

## Deployment & Scalability
✔ **Backend** – Containerized and deployed on **Render**.  
✔ **Frontend** – Hosted on **Netlify**.  
✔ **Docker Image** – Stored on **Docker Hub** for easy replication and scaling.  

---

## Technologies Used
| Component   | Technology Used            |
|------------|---------------------------|
| **Backend** | Spring Boot (Java), Docker, Render |
| **Frontend** | React, Netlify |
| **Database** | PostgreSQL (Neon Tech) |
| **Containerization** | Docker (Image hosted on Docker Hub) |

---

## Key Features
✅ **URL Shortening** – Convert long URLs into short, easy-to-share links.  
✅ **Redirection** – Shortened URLs automatically redirect users to the original links.  
✅ **Database Storage** – PostgreSQL ensures persistent storage of URL mappings.  
✅ **Scalability** – Docker makes deployment and scaling seamless.  
✅ **User-friendly Interface** – A clean, interactive React-based frontend.  

---

## Deployment Details
- The **backend** is built as a **Docker image** and pushed to **Docker Hub**.
- **Render** pulls the image from Docker Hub to deploy the backend.
- The **frontend** is deployed using **Netlify**, which fetches code from GitHub.
- **Neon Tech (PostgreSQL)** is used as the database for secure and scalable storage.

---

## Why This Project?
🚀 **Real-World Deployment** – Demonstrates full-stack **Dockerized** deployment.  
🔗 **Backend-Frontend Separation** – Microservice-like architecture.  
☁ **Cloud-Based Hosting** – Uses **Render, Netlify, and Neon Tech** for seamless cloud deployment.  
⚡ **Scalable & Efficient** – Easily scalable due to containerization and cloud hosting.

This project is an excellent example of a **modern, cloud-native web application** that is both efficient and scalable. 🌍✨

## Resources

- **[NeonDB – PostgreSQL Database](https://console.neon.tech/)**  
  Cloud-hosted PostgreSQL database with branching and autoscaling features.

- **[Backend – Render Deployment](https://dashboard.render.com/)**  
  Render dashboard for managing and deploying backend services.

- **[Frontend – Netlify Deployment](https://app.netlify.com/)**  
  Netlify interface for hosting and deploying frontend applications.

- **[Docker Hub](https://hub.docker.com/)**  
  Repository for managing Docker container images.

- **[Cron Job Scheduler](https://console.cron-job.org/)**  
  Free online cron job service for scheduling recurring tasks.


