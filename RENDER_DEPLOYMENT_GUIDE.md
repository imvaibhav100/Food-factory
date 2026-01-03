# FoodFactory - Render Deployment Guide 🚀

## Complete Step-by-Step Deployment Instructions

### Step 1: Render Account Setup
1. Go to [render.com](https://render.com)
2. Sign in with your account
3. Create a new project (click "New +")

---

### Step 2: Create PostgreSQL Database
1. Click **"New +"** → **"PostgreSQL"**
2. Fill in details:
   - **Name**: `foodfactory-db`
   - **Database**: `foodfactory`
   - **User**: `postgres`
   - **Region**: Oregon (yaa aapka closest)
   - **Plan**: Free
3. Click **"Create Database"**
4. **Database URL copy kar lo** - ye important hai!
   - Connection string kuch aise dikhega:
   ```
   postgresql://postgres:password@foodfactory-db.render.com/foodfactory
   ```

---

### Step 3: Deploy Backend (Spring Boot)
1. Github pe apna code push karo (Render ko GitHub access chahiye)
   ```bash
   git add .
   git commit -m "Ready for Render deployment"
   git push origin main
   ```

2. Render mein click **"New +"** → **"Web Service"**

3. **Connect GitHub**:
   - Click "GitHub"
   - Authorize Render
   - Select `foodfactory` repository

4. **Configure Web Service**:
   - **Name**: `foodfactory-backend`
   - **Environment**: Docker
   - **Region**: Oregon
   - **Plan**: Free
   - **Branch**: main

5. **Environment Variables** (Add these):
   ```
   DATABASE_URL = postgresql://postgres:PASSWORD@HOST/foodfactory
   DB_USER = postgres
   DB_PASSWORD = YOUR_PASSWORD
   PORT = 8080
   ```
   
   *(Database URL waha se copy karo jo Step 2 mein mila)*

6. Click **"Create Web Service"**

7. Render automatically deploy karega! 🎉
   - Build logs dekho - 5-10 minutes lagega
   - Deploy complete hone ke baad URL mil jayega

---

### Step 4: Test Deployment
1. Render dashboard se apka URL dekho (kuch aise: `https://foodfactory-backend.onrender.com`)
2. Browser mein open karo:
   ```
   https://foodfactory-backend.onrender.com/
   ```
3. Home page load hona chahiye!

---

## Important Notes ⚠️

### Database Connection String Format
```
postgresql://username:password@host:5432/database
```

### Free Tier Limits
- 0.5 CPU
- 512 MB RAM
- Database auto-spins down after 15 mins of inactivity
- Limited to 1 concurrent connection

### If Deploy Fails
1. **Check Logs**:
   - Render dashboard → Logs tab
   - Common issues:
     - `DATABASE_URL` not set
     - Port `8080` not exposed
     - Wrong Maven version

2. **Fix & Redeploy**:
   ```bash
   git push origin main  # Automatically redeploys
   ```

---

## Local Testing (Optional)
```bash
# Test locally with Postgres
mvn clean install
mvn spring-boot:run
```

---

## Features Deployed ✅
- ✅ Full Spring Boot Backend
- ✅ Thymeleaf Frontend (HTML/CSS/JS)
- ✅ MySQL → PostgreSQL Database
- ✅ Docker containerization
- ✅ Environment-based configuration

---

## Troubleshooting

| Problem | Solution |
|---------|----------|
| Database connection failed | Check DATABASE_URL env variable |
| Port 8080 not accessible | Verify PORT env var = 8080 |
| Build timeout | Increase Maven heap size in build command |
| Database spin-down | Free tier - wake it by making request |

---

**Deployment Complete!** 🎊

Agar koi issue ho to database logs check karna!
