# Quick Deployment Checklist for Render

## ✅ Pre-Deployment Checklist

### Local Testing
- [ ] Run `mvn clean package` - Build succeeds without errors
- [ ] Run application locally - `mvn spring-boot:run`
- [ ] Test homepage - `http://localhost:8080` loads
- [ ] Test database connection - No errors in logs
- [ ] Verify `Dockerfile` exists in root directory
- [ ] Verify `render.yaml` exists in root directory

### Git Setup
- [ ] Initialize git: `git init`
- [ ] Add files: `git add .`
- [ ] Commit: `git commit -m "Initial commit"`
- [ ] Create GitHub repository at github.com
- [ ] Add remote: `git remote add origin https://github.com/YOUR_USERNAME/foodfactory.git`
- [ ] Push to GitHub: `git push -u origin main`

---

## 🚀 Deployment Steps

### Step 1: Create Render Account
- [ ] Visit [render.com](https://render.com)
- [ ] Sign up with GitHub
- [ ] Authorize Render to access GitHub

### Step 2: Create Web Service
- [ ] In Render dashboard, click **"New +"** → **"Web Service"**
- [ ] Connect your `foodfactory` GitHub repository
- [ ] Name: `foodfactory-backend`
- [ ] Environment: `Docker`
- [ ] Region: `Oregon`
- [ ] Plan: `Free`

### Step 3: Add Environment Variables
In Render dashboard → **Environment** section, add these 6 variables:

```
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=jdbc:mysql://gateway01.ap-southeast-1.prod.aws.tidbcloud.com:4000/test?useSSL=true&serverTimezone=UTC
DB_USER=3AHxi4bKkwrCqvA.root
DB_PASSWORD=GxgBEyntx5OqzQ5t
PORT=8080
JAVA_OPTS=-Xmx512m -XX:+UseContainerSupport
```

- [ ] All 6 variables added
- [ ] All values correct (copy-paste carefully)
- [ ] Save configuration

### Step 4: Deploy
- [ ] Click **"Deploy latest commit"** in Render dashboard
- [ ] Watch the build logs
- [ ] Wait for "Application started" message
- [ ] Deployment should take 3-5 minutes

### Step 5: Verify
- [ ] Get your URL from Render dashboard (e.g., `https://foodfactory-backend.onrender.com`)
- [ ] Visit the URL in browser
- [ ] Verify homepage loads
- [ ] Check for any errors in Render logs

---

## 📊 Expected Deployment Timeline

| Phase | Duration | Status |
|-------|----------|--------|
| Build (Maven compile) | 1-2 min | 🔨 Building |
| Docker image creation | 1-2 min | 🐳 Creating image |
| Application startup | 30-60 sec | ⚙️ Starting |
| Health check | 10-20 sec | ✅ Ready |
| **Total** | **3-5 minutes** | **✅ Live** |

---

## 🔧 Environment Variables Explained

| Variable | Value | Why Needed |
|----------|-------|-----------|
| `SPRING_PROFILES_ACTIVE` | `prod` | Activates production configuration file |
| `DATABASE_URL` | TiDB Cloud connection | Tells app where TiDB Cloud is |
| `DB_USER` | Cluster username | Authenticates with TiDB |
| `DB_PASSWORD` | Cluster password | Authenticates with TiDB |
| `PORT` | `8080` | Render requires this for routing |
| `JAVA_OPTS` | Memory settings | Optimize JVM for Render's free tier |

---

## ❌ Common Issues & Solutions

### Issue: "Build failed"
**Check:**
- [ ] Git repo has all files (run `git status`)
- [ ] `pom.xml` has no syntax errors
- [ ] `Dockerfile` exists in root
- [ ] Push latest code to GitHub

**Fix:**
```bash
git status
git add .
git commit -m "Fix"
git push origin main
```

### Issue: "Application won't start / 500 error in logs"
**Check:**
- [ ] All 6 environment variables are set
- [ ] `DATABASE_URL` starts with `jdbc:mysql://`
- [ ] `SPRING_PROFILES_ACTIVE=prod` is set
- [ ] TiDB Cloud credentials are correct

**Fix:**
1. Go to Web Service settings
2. Click "Environment"
3. Verify all 6 variables
4. Click "Redeploy"

### Issue: "Database connection timeout"
**Check:**
- [ ] TiDB Cloud is accessible (not blocked by firewall)
- [ ] Database name is `test` (from connection string)
- [ ] Username format: `CLUSTER.root` (with dot)
- [ ] Credentials have no extra spaces

---

## 📝 Configuration Files Summary

### application.properties (LOCAL)
- Used when running: `mvn spring-boot:run`
- Connects to: Local MySQL (localhost:3306)
- Default username/password in file

### application-prod.properties (RENDER)
- Used when: `SPRING_PROFILES_ACTIVE=prod`
- Connects to: TiDB Cloud via environment variables
- No hardcoded credentials in file (uses `${ENV_VAR}` notation)

---

## 🎯 Success Criteria

After deployment, verify:
- ✅ Application accessible at `https://yourdomain.onrender.com`
- ✅ Homepage loads without 500 errors
- ✅ No "database connection failed" errors in logs
- ✅ All pages load (Home, Products, Login, etc.)
- ✅ No security warnings in browser console

---

## 📞 Need Help?

**Render Logs:**
- Go to Web Service → Logs tab
- Watch real-time deployment logs
- Copy error messages for troubleshooting

**Common Render Docs:**
- Docs: https://render.com/docs
- Docker deployment: https://render.com/docs/deploy-docker
- Environment variables: https://render.com/docs/environment-variables

**TiDB Cloud:**
- Connection docs: https://docs.tidbcloud.com
- MySQL compatibility: https://docs.tidbcloud.com/tidb-cloud/sql-syntax

---

**Last Updated:** February 11, 2026  
**Project:** FoodFactory  
**Deployment Platform:** Render  
**Database:** TiDB Cloud
