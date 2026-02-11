# FoodFactory - Render Deployment Guide (Step by Step)

## 🚀 Complete Deployment Process for Render with TiDB Cloud

---

## **Step 1: Prepare Your Project**

### 1.1 Build the Application Locally
```bash
mvn clean package
```
Expected output: `target/FoodFactory-0.0.1-SNAPSHOT.jar`

### 1.2 Test Local Configuration
```bash
mvn spring-boot:run
```
Verify that the application:
- Starts successfully on `http://localhost:8080`
- Connects to local MySQL database
- Homepage loads without errors

---

## **Step 2: Prepare Your Repository**

### 2.1 Initialize Git (if not already)
```bash
git init
git add .
git commit -m "Initial commit: FoodFactory with TiDB Cloud configuration"
git branch -M main
```

### 2.2 Create GitHub Repository
1. Go to [github.com](https://github.com) and sign in
2. Click **"New"** to create a new repository
3. Name it: `foodfactory` (or similar)
4. Choose **Public** (recommended for Render integration)
5. **Don't** initialize with README (your project already has one)

### 2.3 Push Code to GitHub
```bash
git remote add origin https://github.com/YOUR_USERNAME/foodfactory.git
git push -u origin main
```

Replace `YOUR_USERNAME` with your actual GitHub username.

---

## **Step 3: Create Render Account & Web Service**

### 3.1 Sign Up on Render
1. Go to [render.com](https://render.com)
2. Click **"Sign Up"** (use GitHub for easy integration)
3. Authorize Render to access your GitHub account
4. Complete profile setup

### 3.2 Create a New Web Service
1. From Render dashboard, click **"New +"** → **"Web Service"**
2. Select your **`foodfactory`** repository from the list
3. If not visible, click **"Connect account"** to authorize GitHub

### 3.3 Configure Web Service Settings

| Setting | Value |
|---------|-------|
| **Name** | `foodfactory-backend` |
| **Environment** | `Docker` |
| **Region** | `Oregon` (us-west) |
| **Branch** | `main` |
| **Build Command** | Leave empty (Docker will handle it) |
| **Start Command** | Leave empty (Dockerfile specifies it) |

---

## **Step 4: Set Environment Variables**

### 4.1 Add Environment Variables in Render Dashboard
Go to **Web Service Settings** → **Environment** section and add:

```
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=jdbc:mysql://gateway01.ap-southeast-1.prod.aws.tidbcloud.com:4000/test?useSSL=true&serverTimezone=UTC
DB_USER=3AHxi4bKkwrCqvA.root
DB_PASSWORD=GxgBEyntx5OqzQ5t
PORT=8080
JAVA_OPTS=-Xmx512m -XX:+UseContainerSupport
```

### 4.2 Understand Each Variable

| Variable | Purpose | Example |
|----------|---------|---------|
| `SPRING_PROFILES_ACTIVE` | Activates production profile | `prod` |
| `DATABASE_URL` | TiDB Cloud connection string | `jdbc:mysql://gateway01...` |
| `DB_USER` | TiDB Cloud username | `3AHxi4bKkwrCqvA.root` |
| `DB_PASSWORD` | TiDB Cloud password | `GxgBEyntx5OqzQ5t` |
| `PORT` | Application port (required by Render) | `8080` |
| `JAVA_OPTS` | JVM memory settings | `-Xmx512m...` |

---

## **Step 5: Deploy to Render**

### 5.1 Trigger Deployment
1. In Render dashboard, click **"Deploy latest commit"**
2. Watch the deployment logs in real-time
3. Render will:
   - Build Docker image from your `Dockerfile`
   - Pull dependencies from Maven
   - Start the application with environment variables

### 5.2 Expected Logs
```
Building Docker image...
Step 1/X : FROM ...
...
[INFO] Building jar: target/FoodFactory-0.0.1-SNAPSHOT.jar
Successfully built Docker image
Starting web service...
Application started successfully
Server started on port 8080
```

### 5.3 Deployment Complete
- Your application URL: `https://foodfactory-backend.onrender.com`
- (The exact name depends on what you named the service)

---

## **Step 6: Verify Deployment**

### 6.1 Check Health Status
```bash
curl https://YOUR_APP_URL/
```

Expected response: HTML homepage loads successfully

### 6.2 View Live Logs
1. Go to **Web Service** → **Logs** in Render dashboard
2. Look for:
   - `Application started successfully`
   - No error messages about database connection

### 6.3 Access Your Application
1. Visit: `https://YOUR_APP_URL`
2. Test features:
   - Login page loads
   - Navigate to products
   - Try admin panel

---

## **Step 7: Troubleshooting**

### Issue: "Application failed to start"
**Solution:**
1. Check logs: `Web Service → Logs`
2. Look for error messages
3. Common issues:
   - Missing environment variables → Add them in Step 4
   - Dockerfile issues → Verify `Dockerfile` exists in root

### Issue: "500 Error / Database Connection Failed"
**Solution:**
1. Verify TiDB Cloud is accessible:
   ```bash
   telnet gateway01.ap-southeast-1.prod.aws.tidbcloud.com 4000
   ```
2. Check credentials in environment variables
3. Ensure `spring.profiles.active=prod` is set

### Issue: "Build Fails / Docker Error"
**Solution:**
1. Verify `Dockerfile` exists in project root
2. Check `pom.xml` has no syntax errors
3. Try local build: `mvn clean package`
4. Rebuild in Render: Clear build cache → Redeploy

### Issue: "Port Already in Use"
**Solution:**
- Render automatically handles ports
- Ensure `PORT` environment variable is set to `8080`
- Application will be exposed via Render's reverse proxy

---

## **Step 8: Post-Deployment**

### 8.1 Monitor Application
- Set up **Alerts** in Render dashboard
- Monitor logs regularly
- Check memory usage (Free plan has limits)

### 8.2 Update Application
To deploy new changes:
```bash
git add .
git commit -m "Your message"
git push origin main
```
Render automatically redeploys when you push to main branch.

### 8.3 Scale if Needed
- Free plan: 750 hours/month (single instance)
- Paid plans: Higher memory, auto-scaling, priority support

---

## **Quick Summary**

| # | Step | Command/Action |
|---|------|----------------|
| 1 | Build locally | `mvn clean package` |
| 2 | Push to GitHub | `git push origin main` |
| 3 | Create Render service | Visit render.com → New Web Service |
| 4 | Add env variables | Set 6 variables in Render dashboard |
| 5 | Deploy | Click "Deploy latest commit" |
| 6 | Verify | Visit `https://YOUR_APP_URL` |

---

## **Environment Variables Reference**

```properties
# application-prod.properties uses these environment variables:

# Primary configuration
SPRING_PROFILES_ACTIVE=prod

# Database connection
DATABASE_URL=jdbc:mysql://gateway01.ap-southeast-1.prod.aws.tidbcloud.com:4000/test?useSSL=true&serverTimezone=UTC
DB_USER=3AHxi4bKkwrCqvA.root
DB_PASSWORD=GxgBEyntx5OqzQ5t

# Server
PORT=8080

# JVM
JAVA_OPTS=-Xmx512m -XX:+UseContainerSupport
```

---

## **File Structure for Deployment**

```
foodfactory/
├── pom.xml                    ✅ Maven config
├── Dockerfile                 ✅ Docker build instructions
├── render.yaml               ✅ Render configuration
├── mvnw                       ✅ Maven wrapper
├── mvnw.cmd                   ✅ Maven wrapper (Windows)
├── src/main/java/...          ✅ Source code
├── src/main/resources/
│   ├── application.properties           ✅ Local config
│   └── application-prod.properties      ✅ Render config
└── README.md                  ✅ Documentation
```

---

## **Success Indicators**

✅ Application deployed and running on Render  
✅ Homepage accessible at production URL  
✅ Database connections working (no errors in logs)  
✅ TiDB Cloud credentials properly secured in environment variables  
✅ No sensitive data in code or GitHub repository  

---

## **Need Help?**

- Render Docs: https://render.com/docs
- Check logs in Render dashboard → Web Service → Logs
- GitHub Issues or community forums

Good luck with your deployment! 🎉
