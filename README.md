# Newsy - Full Stack News Aggregator

A modern news aggregator application with Spring Boot backend and Angular frontend that fetches and displays news articles from NewsData.io.

## 🚀 Features

- **5 News Categories**: Technology, Sports, Business, Education, Entertainment
- **4 Articles per Category**: Total of 20 unique, fresh articles daily
- **Scheduled News Fetching**: Automatically fetches articles daily at 8:00 AM UTC
- **Beautiful UI**: Modern Angular frontend with Lucide icons
- **Dark/Light Theme**: Toggle between themes with smooth transitions
- **Responsive Design**: 4-column grid layout that adapts to all screen sizes
- **Image Fallbacks**: Automatic fallback to category-specific images for CORS/403 errors
- **REST API**: Clean RESTful endpoints for news retrieval
- **MongoDB Storage**: Cloud-based storage with MongoDB Atlas
- **Error Handling**: Comprehensive error handling across the stack

## 🛠️ Tech Stack

### Backend
- **Java 21**
- **Spring Boot 3.5.7**
- **MongoDB Atlas** (Cloud Database)
- **NewsData.io API** (News Provider - Free Tier)
- **Lombok** (Reduce boilerplate)
- **Maven** (Build tool)

### Frontend
- **Angular 17**
- **TypeScript**
- **Lucide Angular** (Beautiful SVG icons)
- **RxJS** (Reactive programming)
- **CSS Variables** (Theme support)

## 📁 Project Structure

```
newsy/
├── src/main/java/com/newsy/newsy/          # Backend (Spring Boot)
│   ├── NewsyApplication.java
│   ├── config/AppConfig.java
│   ├── controller/NewsController.java
│   ├── dto/NewsApiResponse.java & NewsDTO.java
│   ├── exception/GlobalExceptionHandler.java
│   ├── model/News.java
│   ├── repository/NewsRepository.java
│   ├── scheduler/NewsScheduler.java
│   └── service/NewsService.java
├── newsy-ui/                                # Frontend (Angular)
│   ├── src/app/
│   │   ├── app.component.*
│   │   ├── models/news.model.ts
│   │   ├── services/
│   │   │   ├── news.service.ts
│   │   │   └── theme.service.ts
│   │   └── environments/
│   │       ├── environment.ts              # Production config
│   │       └── environment.development.ts  # Development config
│   └── vercel.json                         # Vercel deployment config
├── .env.example                            # Environment variables template
├── .gitignore                              # Git ignore rules
├── DEPLOYMENT_GUIDE.md                     # Detailed deployment guide
├── QUICK_DEPLOY.md                         # Quick deployment steps
└── PRODUCTION_READINESS.md                 # Production checklist
```

## 📋 Prerequisites

### For Local Development:
1. **Java 21** installed
2. **Maven 3.8+** installed
3. **Node.js 18+** and **npm** installed
4. **MongoDB Atlas** account (free tier)
5. **NewsData.io API Key** (free tier - 200 requests/day)

### For Production Deployment:
1. **GitHub** account (for code repository)
2. **Render** account (free tier for backend)
3. **Vercel** account (free tier for frontend)
4. **MongoDB Atlas** account (free tier)
5. **NewsData.io API Key**

## 🔧 Local Setup Instructions

### 1. Clone the Repository
```bash
git clone <your-repo-url>
cd newsy
```

### 2. Configure Environment Variables

Copy `.env.example` to `.env` and update with your credentials:

```bash
# MongoDB Atlas
MONGODB_URI=mongodb+srv://username:password@cluster.mongodb.net/?appName=Newsy
MONGODB_DATABASE=newsy_db

# NewsData.io API
NEWSDATA_API_KEY=your_newsdata_api_key_here
NEWSDATA_API_URL=https://newsdata.io/api/1/latest

# Configuration
NEWS_MAX_ARTICLE_AGE_DAYS=2
NEWS_FETCH_CRON=0 0 8 * * ?
SERVER_PORT=8080
FRONTEND_URL=http://localhost:4200
```

### 3. Setup MongoDB Atlas

1. Go to https://www.mongodb.com/cloud/atlas
2. Create a free cluster
3. Create a database user
4. Whitelist your IP (or use 0.0.0.0/0 for development)
5. Get your connection string and update `MONGODB_URI`

### 4. Get NewsData.io API Key

1. Go to https://newsdata.io
2. Sign up for a free account (200 requests/day)
3. Get your API key from the dashboard
4. Update `NEWSDATA_API_KEY` in your environment

### 5. Run Backend

```bash
# Set environment variables (Windows)
set MONGODB_URI=your_mongodb_uri
set NEWSDATA_API_KEY=your_api_key

# Build and run
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

Backend will start on http://localhost:8080

### 6. Run Frontend

```bash
cd newsy-ui
npm install
npm start
```

Frontend will start on http://localhost:4200

## 🌐 REST API Endpoints

### 1. Get All News (Grouped by Category)
```
GET /api/news
```

**Response:**
```json
{
  "totalCategories": 5,
  "totalArticles": 20,
  "news": {
    "Technology": [/* 4 articles */],
    "Sports": [/* 4 articles */],
    "Business": [/* 4 articles */],
    "Education": [/* 4 articles */],
    "Entertainment": [/* 4 articles */]
  }
}
```

### 2. Get News by Category
```
GET /api/news/{category}
```

**Categories:** `technology`, `sports`, `business`, `education`, `entertainment`

**Example:**
```bash
curl http://localhost:8080/api/news/technology
```

### 3. Manual Fetch News
```
POST /api/news/fetch
```

Manually triggers news fetch (useful for testing or admin purposes).

```bash
curl -X POST http://localhost:8080/api/news/fetch
```

## ⏰ Scheduled Jobs

- **Schedule**: Daily at 8:00 AM UTC
- **Cron Expression**: `0 0 8 * * ?`
- **Action**: Fetches 4 unique articles for each of the 5 categories from NewsData.io
- **Total API Calls**: 5 calls per day (well within free tier limit of 200/day)

## 🎨 Frontend Features

- **5 Category Pills**: All, Technology, Sports, Business, Education, Entertainment
- **Lucide Icons**: Beautiful SVG icons for all categories
- **4-Column Grid**: Responsive layout that adapts to screen size
- **Theme Toggle**: Smooth dark/light mode switching
- **Image Fallbacks**: Automatic fallback images when article images fail to load
- **Loading States**: Elegant loading spinner
- **Error Handling**: User-friendly error messages

## 🚀 Production Deployment

### Quick Deployment (15 minutes)

**See `QUICK_DEPLOY.md` for step-by-step instructions.**

**Deployment Order:**
1. Deploy Backend to Render (5-10 mins)
2. Update Frontend environment with Render URL
3. Deploy Frontend to Vercel (2-3 mins)
4. Update Backend CORS with Vercel URL

**Detailed Guide:** See `DEPLOYMENT_GUIDE.md`

**Production Checklist:** See `PRODUCTION_READINESS.md`

## 💰 Cost Breakdown (FREE!)

- **Backend (Render)**: Free tier ✅
- **Frontend (Vercel)**: Free tier ✅
- **Database (MongoDB Atlas)**: Free tier (512MB) ✅
- **API (NewsData.io)**: Free tier (200 requests/day) ✅

**Total: $0/month**

## 🔒 Security Features

- ✅ No hard-coded credentials
- ✅ Environment variables for sensitive data
- ✅ CORS configured with environment-based origins
- ✅ `.gitignore` properly configured
- ✅ MongoDB Atlas with authentication
- ✅ API keys externalized

## 📊 Monitoring & Logs

**Backend Logs:**
```
2025-11-02 08:00:00 - Starting scheduled news fetch at 2025-11-02T08:00:00
2025-11-02 08:00:01 - Fetching latest 10 articles for category: technology
2025-11-02 08:00:02 - Successfully saved 4 recent articles for category: Technology
```

**Health Check:**
```bash
curl http://localhost:8080/actuator/health
```

## 🐛 Troubleshooting

### Backend Issues

**MongoDB Connection Error:**
```
Solution: 
1. Check MongoDB Atlas connection string
2. Verify IP whitelist includes your IP
3. Ensure database user credentials are correct
```

**NewsData.io API Error:**
```
Solution:
1. Verify API key is correct
2. Check if free tier limit (200/day) is exceeded
3. Ensure internet connection is active
```

### Frontend Issues

**Cannot connect to backend:**
```
Solution:
1. Verify backend is running
2. Check environment.ts has correct API URL
3. Verify CORS settings on backend
```

**Images not loading:**
```
Solution: Automatic fallback is enabled - category-specific 
placeholder images from Unsplash will be used
```

## 📝 API Limitations (Free Tier)

**NewsData.io Free Tier:**
- 200 API calls per day
- Latest news only (no historical data)
- No `from_date` parameter support
- Client-side filtering for date ranges

**Current Usage:**
- 5 API calls per day (one per category)
- Well within free tier limits
- Plenty of room for manual refreshes

## 🤝 Contributing

This project is for educational/portfolio purposes. Feel free to fork and modify!

## 📄 License

This project is open source and available for educational use.

## 📞 Support

For deployment help, see:
- `QUICK_DEPLOY.md` - Fast deployment guide
- `DEPLOYMENT_GUIDE.md` - Detailed deployment instructions
- `PRODUCTION_READINESS.md` - Pre-deployment checklist

## 🎉 Live Demo

Once deployed:
- **Frontend**: `https://your-app.vercel.app`
- **Backend API**: `https://your-backend.onrender.com/api/news`

---

**Built with ❤️ using Spring Boot & Angular**
