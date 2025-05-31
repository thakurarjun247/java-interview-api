
# 🚀 Project Freedom: AI Job Applyer MVP

> **Old Project Freedom Goal:** Build a **free, high-volume job scraping system** that auto-applies to remote jobs using AI, with Java/Python microservices.

---

## 📌 MVP Architecture

### 🧩 Core Services

| Service          | Tech Stack           | Purpose                                           |
|------------------|----------------------|---------------------------------------------------|
| Auth-Service     | Java 21 + Spring Boot| Cookie-based auth (JSESSIONID) + user preferences |
| Scraper-Service  | Python + FastAPI     | Scrapes free job boards → Kafka                   |
| Applier-Service  | Python + Django      | AI resume tailoring → submits applications        |
| Kafka            | Docker (Bitnami)     | Event bus for jobs                                |
| PostgreSQL       | Docker (Official)    | User/auth data                                    |

### 🛠️ Key Tools

- **Scraping**: BeautifulSoup, free proxies, rotating user agents
- **AI**: OpenAI GPT-3.5-turbo (free tier)
- **DevOps**: Docker, Docker Compose

---

## 📅 MVP Timeline (3 Weeks, 3h/Day)

### Week 1: Core Setup

| Day | Task                            | Deliverable                     | Tech Used              |
|-----|----------------------------------|----------------------------------|-------------------------|
| 1   | Java Auth-Service (JSESSIONID)  | /signup, /login endpoints        | Java 21, Spring         |
| 2   | User Preferences in Auth-Service| user_preferences table + API    | PostgreSQL, JPA         |
| 3   | FastAPI Scraper Setup           | Scrape 1 job board (RemoteOK)   | FastAPI, BeautifulSoup  |
| 4   | Kafka Producer Test             | Publish jobs → raw_jobs topic   | kafka-python            |
| 5   | Django Applier Skeleton         | /apply endpoint (logs only)     | Django                  |

### Week 2: Scraping at Scale

| Day | Task                       | Deliverable                     | Tech Used              |
|-----|-----------------------------|----------------------------------|-------------------------|
| 6   | Proxy Rotation             | Avoid IP bans (free proxies)     | requests + proxies      |
| 7   | Rate Limiting (10 reqs/min)| FastAPI middleware               | FastAPI                 |
| 8   | Skip CAPTCHA-heavy sites   | Manual allowlist                 | Manual allowlist        |
| 9   | AI Resume Tailoring        | GPT-3.5 + templates              | OpenAI API              |
| 10  | Streamlit Dashboard        | Track applications               | Streamlit               |

### Week 3: Fault Tolerance

| Day | Task                         | Deliverable                     | Tech Used         |
|-----|-------------------------------|----------------------------------|--------------------|
| 11  | Exponential Backoff Retries | Auto-retry failed scrapes       | Python tenacity    |
| 12  | Logging + Alerts            | Slack alerts for failures       | Python logging     |
| 13  | Dockerize All Services      | docker-compose.yml with Kafka   | Docker             |
| 14  | Demo Prep                   | Ngrok tunnel + video            | Ngrok              |
| 15  | Manual Testing              | Verify 100+ jobs scraped/applied| Manual             |

---

## 🔧 Scraper Optimizations

### 1. Free Proxy Rotation

```python
proxies = ["http://proxy1:port", "http://proxy2:port"]  # From free lists  
proxy = random.choice(proxies)  
requests.get(url, proxies={"http": proxy}, timeout=5)
```

### 2. Allowlist Easy Job Boards

```python
ALLOWED_DOMAINS = ["weworkremotely.com", "remoteok.io"]  
if not any(domain in url for domain in ALLOWED_DOMAINS):  
    skip_job(url)
```

### 3. Rate Limiting

```python
from fastapi import Request  
from fastapi.middleware import Middleware  

@app.middleware("http")  
async def rate_limit(request: Request, call_next):  
    if request.url.path == "/scrape":  
        await asyncio.sleep(6)  # 10 reqs/min  
    return await call_next(request)
```

---

## 🚀 Deployment

```bash
docker-compose up  # Starts all services
```

**Access URLs**:
- Java Auth: http://localhost:8080
- FastAPI Scraper: http://localhost:8000
- Django Applier: http://localhost:8001
- Streamlit Dashboard: http://localhost:8501

---

## 🔮 Future Enhancements

### Phase 2
- Replace Streamlit with React frontend
- Add Kafka Streams for job filtering

### Phase 3
- CI/CD using GitHub Actions → AWS ECS
- Monitoring with Prometheus + Grafana

---

## 🎯 Key Takeaways

| Feature              | Benefit                                              |
|----------------------|------------------------------------------------------|
| Zero-Cost Scraping   | Free proxies + allowlisted job boards               |
| Java 21              | Virtual threads for scalable auth service           |
| Kafka-Ready          | Event-driven architecture from the start            |
| AI Integration       | GPT-based resume customization and auto-apply logic |

**Next Steps**:
1. Start with Day 1 (Java Auth)
2. Test the scraper (Day 3)
3. MVP + Demo by end of Week 3

---

# 📦 Monorepo Structure & Development Guide

This document explains how to set up, develop, and test services in the monorepo while keeping workflows IDE-friendly.

---

## 📂 Monorepo Structure

```bash
/project-freedom  
├── /auth-service          # Java + Spring Boot  
│   ├── src/               # Source code  
│   ├── Dockerfile         # Container setup  
│   ├── pom.xml            # Maven config  
│   └── README.md          # Service-specific docs  
├── /scraper-service       # Python + FastAPI  
│   ├── app/               # FastAPI code  
│   ├── Dockerfile  
│   ├── requirements.txt   # Python dependencies  
│   └── README.md  
├── /applier-service       # Python + Django  
│   ├── applier/           # Django project  
│   ├── Dockerfile  
│   ├── requirements.txt  
│   └── README.md  
├── /infra                 # Shared infrastructure  
│   ├── kafka/             # Kafka configs  
│   └── postgres/          # PostgreSQL init scripts  
├── docker-compose.yml     # Runs all services  
└── README.md              # Project overview
```

---

## 🛠️ Local Development Setup

### 1. IDE Configuration

**Java (IntelliJ IDEA):**
- Open the monorepo root
- Mark `auth-service` as Maven project
- Set Java SDK to 21

**Python (VS Code / PyCharm):**
```bash
cd scraper-service && python -m venv .venv && source .venv/bin/activate  
pip install -r requirements.txt
```

Repeat for `applier-service`.

---

### 2. Running Services

#### Option A: In IDE

**Java Auth-Service**: Run `AuthApplication.java`  
**Python Scraper-Service**:

```json
{
  "type": "python",
  "request": "launch",
  "module": "uvicorn",
  "args": ["app.main:app", "--reload"]
}
```

**Python Applier-Service**:
```bash
python manage.py runserver
```

#### Option B: With Docker

```bash
docker-compose up
```

---

## ✅ Testing

| Service          | Command                  | IDE Shortcut                    |
|------------------|--------------------------|----------------------------------|
| Java Auth        | `mvn test`               | IntelliJ JUnit                   |
| Python Scraper   | `pytest tests/`          | VS Code / PyCharm Test Explorer  |
| Python Applier   | `python manage.py test`  | Django Test Runner               |

---

## 🔌 Debugging Workflows

**Java (IntelliJ):**
- Set breakpoints, click Debug on `AuthApplication`

**Python (VS Code):**
- Set breakpoints in `.py`, press F5

**Hybrid Mode:**
```bash
docker-compose up kafka postgres
```

Then run services inside your IDE.

---

## ⚙️ CI/CD (GitHub Actions)

```yaml
name: Build & Test
on: [push]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - run: docker-compose -f docker-compose.test.yml build
      - run: docker-compose -f docker-compose.test.yml up --abort-on-container-exit
```

---

## ✅ Monorepo Benefits

- Single clone:
```bash
git clone https://github.com/your/project-freedom.git
```
- Unified Docker services:
```bash
docker-compose up
```
- Cross-service commits & schema evolution

---

## 🚀 Future Scalability

- Add Bazel or Nx for large-scale builds
- Split into polyrepos only if needed

---

**Monorepo = Less Ops, More Coding.** 🚀
