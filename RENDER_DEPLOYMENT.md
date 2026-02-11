# 🚀 Render Deployment Guide - Oracle MySQL Cloud

This guide explains how to deploy your FoodFactory application on Render using Oracle MySQL Cloud database.

---

## 📋 Prerequisites

1. **Render Account** - [Sign up at render.com](https://render.com)
2. **Oracle Cloud Account** - [Sign up at oracle.com/cloud](https://www.oracle.com/cloud/)
3. **Oracle MySQL Database** - Create a MySQL database instance in Oracle Cloud

---

## 🗄️ Step 1: Setup Oracle MySQL Cloud Database

### Create MySQL Database Instance:

1. Log in to Oracle Cloud Console
2. Navigate to **Databases** → **MySQL** → **DB Systems**
3. Click **Create MySQL DB System**
4. Configure:
   - **Name:** foodfactory-db
   - **Administrator username:** admin (or your choice)
   - **Administrator password:** (save this securely)
   - **Configure networking:** Allow public access or use VPN
   - **Configure security:** Add your IP and Render's IP ranges

5. After creation, note down:
   - **Hostname/Endpoint:** (e.g., abc123.mysql.database.oracle.com)
   - **Port:** 3306 (default)
   - **Database name:** foodfactory
   - **Username:** admin
   - **Password:** (your password)

### Create Database:

Connect to your Oracle MySQL instance and create the database:

```sql
CREATE DATABASE foodfactory CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

---

## 🌐 Step 2: Deploy on Render

### Create New Web Service:

1. Log in to [Render Dashboard](https://dashboard.render.com)
2. Click **New** → **Web Service**
3. Connect your GitHub repository: `imvaibhav100/Foodfactory`
4. Configure:
   - **Name:** foodfactory
   - **Environment:** Java
   - **Build Command:** `./mvnw clean package -DskipTests`
   - **Start Command:** `java -jar target/FoodFactory-0.0.1-SNAPSHOT.jar`
   - **Instance Type:** Free or Starter (based on your needs)

### Set Environment Variables:

Add the following environment variables in Render:

```bash
SPRING_PROFILES_ACTIVE=prod

# Oracle MySQL Cloud Connection
DATABASE_URL=jdbc:mysql://your-oracle-endpoint:3306/foodfactory?useSSL=true&requireSSL=true
DB_USER=admin
DB_PASSWORD=your_secure_password
DB_HOST=your-oracle-endpoint
DB_PORT=3306
DB_NAME=foodfactory

# Optional: Port configuration
PORT=8080
```

**Important:** Replace placeholders with your actual Oracle MySQL Cloud credentials!

---

## 🔐 Step 3: Configure Security

### Oracle Cloud Firewall Rules:

1. Navigate to your MySQL instance in Oracle Cloud
2. Go to **Security** → **Network Security Groups**
3. Add Ingress Rules:
   - **Source:** Render's IP ranges or 0.0.0.0/0 (for testing)
   - **Destination Port:** 3306
   - **Protocol:** TCP

### SSL/TLS Configuration:

Your connection string already includes SSL:
```
?useSSL=true&requireSSL=true
```

For enhanced security, you can download Oracle's SSL certificate and configure it in your application.

---

## ✅ Step 4: Deploy & Verify

1. Click **Create Web Service** in Render
2. Wait for deployment to complete (usually 5-10 minutes)
3. Once deployed, Render will provide your application URL:
   ```
   https://foodfactory-xxxx.onrender.com
   ```

4. Verify deployment:
   - Visit your URL
   - Check Render logs for any errors
   - Test database connection by registering a user

---

## 🔍 Troubleshooting

### Common Issues:

**1. Connection Timeout:**
- Check Oracle Cloud firewall rules
- Verify VCN security lists allow port 3306
- Ensure public access is enabled (if using public endpoint)

**2. Authentication Failed:**
- Verify `DB_USER` and `DB_PASSWORD` are correct
- Check if user has proper privileges:
  ```sql
  GRANT ALL PRIVILEGES ON foodfactory.* TO 'admin'@'%';
  FLUSH PRIVILEGES;
  ```

**3. SSL/TLS Errors:**
- Try adding `&sslMode=REQUIRED` to DATABASE_URL
- Or temporarily use `&useSSL=false` for testing (not recommended for production)

**4. Application Not Starting:**
- Check Render logs for detailed error messages
- Verify all environment variables are set correctly
- Ensure Java version matches (Java 17)

---

## 📊 Monitoring

### Render Dashboard:
- View application logs in real-time
- Monitor CPU and memory usage
- Set up health checks

### Oracle Cloud Monitoring:
- Database performance metrics
- Connection statistics
- Query performance

---

## 💰 Cost Optimization

**Render:**
- Free tier: 750 hours/month (with limitations)
- Starter: $7/month (recommended for small apps)

**Oracle Cloud:**
- Always Free tier includes MySQL instances
- Monitor usage to stay within free limits

---

## 🔄 Local to Production Workflow

**Development (Local MySQL):**
```bash
# Simply run - uses application.properties automatically
mvn spring-boot:run
```

**Production (Oracle MySQL Cloud on Render):**
```bash
# Deployed automatically on git push
# Activates application-prod.properties when SPRING_PROFILES_ACTIVE=prod
```

### Configuration Files:
- **application.properties** → Local MySQL (default)
- **application-prod.properties** → Oracle MySQL Cloud (production)

---

## 📞 Support

If you encounter issues:
1. Check Render logs
2. Review Oracle Cloud MySQL logs
3. Verify network connectivity between Render and Oracle Cloud
4. Open an issue on GitHub repository

---

**Happy Deploying! 🎉**
