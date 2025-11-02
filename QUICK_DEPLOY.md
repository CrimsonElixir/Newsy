# Quick Deployment Steps

## 🚀 Deploy Backend to Render

1. **Create account**: https://render.com
2. **New Web Service** → Connect GitHub repo
3. **Settings**:
   - Name: `newsy-backend`
   - Branch: `main`
   - Root Directory: `.` (root)
   - Environment: `Java 21`
   - Build Command: `mvn clean install -DskipTests`
   - Start Command: `java -jar target/newsy-0.0.1-SNAPSHOT.jar`

4. **Add Environment Variables**:
   ```
   MONGODB_URI=mongodb+srv://newsy_user:yh4jZuGPZKQELAcL@newsy.yvyoly8.mongodb.net/?appName=Newsy
   MONGODB_DATABASE=newsy_db
   NEWSDATA_API_KEY=pub_1ab7ba6fae4c4f2e8684e18d0fb9b86c
   NEWSDATA_API_URL=https://newsdata.io/api/1/latest
   NEWS_MAX_ARTICLE_AGE_DAYS=2
   NEWS_FETCH_CRON=0 0 8 * * ?
   SERVER_PORT=8080
   FRONTEND_URL=http://localhost:4200
   ```

5. **Deploy** → Wait for build (5-10 mins)
6. **Copy your Render URL** (e.g., `https://newsy-backend-xyz.onrender.com`)

---

## 🎨 Deploy Frontend to Vercel

1. **Update Backend URL**:
   - Edit `newsy-ui/src/environments/environment.ts`
   - Replace `your-render-backend.onrender.com` with your actual Render URL

2. **Create account**: https://vercel.com
3. **Import Project** → Connect GitHub repo
4. **Settings**:
   - Framework Preset: `Angular`
   - Root Directory: `newsy-ui`
   - Build Command: `npm run build:prod`
   - Output Directory: `dist/newsy-ui/browser`

5. **Deploy** → Wait for build (2-3 mins)
6. **Copy your Vercel URL** (e.g., `https://newsy-xyz.vercel.app`)

---

## 🔄 Update CORS

1. Go back to **Render Dashboard**
2. Update `FRONTEND_URL` environment variable:
   ```
   FRONTEND_URL=https://newsy-xyz.vercel.app
   ```
3. **Save** → Render will auto-redeploy

---

## ✅ Test Your App

1. Visit your Vercel URL
2. Click "Refresh" to fetch initial news
3. Test all categories
4. Toggle dark/light theme
5. Check MongoDB Atlas to verify data is being stored

---

## 🎉 You're Live!

Your app is now deployed and accessible worldwide for **FREE**!

**Share your URL**: `https://your-app.vercel.app`

