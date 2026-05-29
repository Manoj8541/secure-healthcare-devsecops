<div align="center">

# Secure Healthcare DevSecOps

<img src="https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen?style=for-the-badge&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker&logoColor=white"/>
<img src="https://img.shields.io/badge/GitHub%20Actions-CI%2FCD-2088FF?style=for-the-badge&logo=githubactions&logoColor=white"/>
<img src="https://img.shields.io/badge/HIPAA-Compliant-red?style=for-the-badge&logo=health&logoColor=white"/>
<img src="https://img.shields.io/badge/Security-Enterprise%20Grade-black?style=for-the-badge&logo=shield&logoColor=white"/>

<br/>

> 🔐 **Enterprise-grade DevSecOps pipeline** for a Healthcare Patient Management System —
> featuring automated security scanning, AI-powered analysis, policy enforcement and HIPAA compliance.

<br/>

![Pipeline](https://img.shields.io/badge/Pipeline-9%20Stages-blueviolet?style=flat-square)
![Security](https://img.shields.io/badge/Security%20Tools-5%20Scanners-critical?style=flat-square)
![AI](https://img.shields.io/badge/AI-Groq%20Llama%203.3-ff69b4?style=flat-square)
![License](https://img.shields.io/badge/License-Educational-yellow?style=flat-square)

</div>

---

## 📌 What is This?

A **production-ready**, **security-first** CI/CD pipeline built specifically for healthcare — where data sensitivity is critical and compliance is non-negotiable.

This project does not just build and deploy. It **scans, validates, enforces and blocks** — automatically.

```
Push code → Scan → Test → Build → Secure → Gate → Deploy  or Block 
```

---

## 🏗️ System Architecture

```
┌─────────────────────────────────────────────────────────────────────┐
│                  SECURE HEALTHCARE DEVSECOPS                        │
└─────────────────────────────────────────────────────────────────────┘

                          ┌──────────────┐
                          │     User     │
                          │   Browser    │
                          └──────┬───────┘
                                 │ HTTP request
                                 ▼
                          ┌──────────────────────┐
                          │   Spring Boot App    │
                          │   REST API · 9090    │
                          └──────┬───────────────┘
               ┌─────────────────┼─────────────────┐
               ▼                 ▼                 ▼
    ┌──────────────────┐ ┌─────────────┐ ┌──────────────────┐
    │ Spring Security  │ │ Patient DB  │ │ /health endpoint │
    │ BCrypt · RBAC    │ │  In-memory  │ │  status: healthy │
    └──────────────────┘ └─────────────┘ └──────────────────┘
                                 │ runs inside
                                 ▼
                    ┌─────────────────────────┐
                    │  ╔═══════════════════╗  │
                    │  ║  Spring Boot App  ║  │  Docker
                    │  ║   port 9090       ║  │  Container
                    │  ╚═══════════════════╝  │  Non-root
                    └────────────┬────────────┘
                                 │
─────────────────── GitHub Actions CI/CD Pipeline ───────────────────

  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐
  │  Build   │→ │   Test   │→ │  Docker  │→ │  CodeQL  │
  │  Maven   │  │  JUnit   │  │  Image   │  │   SAST   │
  └──────────┘  └──────────┘  └──────────┘  └──────────┘

  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐
  │ Gitleaks │→ │  Trivy   │→ │ Checkov  │→ │ Groq AI  │
  │ Secrets  │  │   CVE    │  │   IaC    │  │ Summary  │
  └──────────┘  └──────────┘  └──────────┘  └──────────┘

                                 │
                                 ▼
                    ┌─────────────────────────┐
                    │      Policy Gate        │
                    ├────────────┬────────────┤
                    │  CRITICAL  │    CLEAN   │
                    │   BLOCK    │ DEPLOY     │
                    └────────────┴────────────┘
                                 │
                                 ▼ deploy
                    ┌────────────────────────┐
                    │    Docker Container    │
                    │  Spring Boot · 9090    │
                    └────────────────────────┘
```

---

## 🛠️ Tech Stack

<div align="center">

| Layer | Technology | Badge |
|---|---|---|
| Backend | Spring Boot 4.0.6 (Java 17) | ![Spring](https://img.shields.io/badge/-Spring%20Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white) |
| Frontend | Thymeleaf + Bootstrap | ![Bootstrap](https://img.shields.io/badge/-Bootstrap-7952B3?style=flat-square&logo=bootstrap&logoColor=white) |
| Auth | Spring Security + BCrypt | ![Security](https://img.shields.io/badge/-Spring%20Security-6DB33F?style=flat-square&logo=springsecurity&logoColor=white) |
| Containers | Docker Multi-stage Non-root | ![Docker](https://img.shields.io/badge/-Docker-2496ED?style=flat-square&logo=docker&logoColor=white) |
| CI/CD | GitHub Actions | ![GHA](https://img.shields.io/badge/-GitHub%20Actions-2088FF?style=flat-square&logo=githubactions&logoColor=white) |
| SAST | CodeQL | ![CodeQL](https://img.shields.io/badge/-CodeQL-000000?style=flat-square&logo=github&logoColor=white) |
| Secrets | Gitleaks | ![Gitleaks](https://img.shields.io/badge/-Gitleaks-FF0000?style=flat-square&logo=git&logoColor=white) |
| Container Scan | Trivy | ![Trivy](https://img.shields.io/badge/-Trivy-1904DA?style=flat-square&logo=aqua&logoColor=white) |
| IaC Security | Checkov | ![Checkov](https://img.shields.io/badge/-Checkov-5C4EE5?style=flat-square&logo=paloaltonetworks&logoColor=white) |
| Dependency | OWASP Dependency-Check | ![OWASP](https://img.shields.io/badge/-OWASP-000000?style=flat-square&logo=owasp&logoColor=white) |
| AI Analysis | Groq — Llama 3.3 | ![Groq](https://img.shields.io/badge/-Groq%20AI-ff69b4?style=flat-square&logo=openai&logoColor=white) |

</div>

---

## 🚀 Pipeline Stages

```
Stage 1 ── 🔨 Build          →  Maven compile & package
Stage 2 ── 🧪 Test           →  JUnit unit tests
Stage 3 ── 🐳 Docker         →  Build secure container image
Stage 4 ── 🔍 SAST           →  CodeQL static analysis
Stage 5 ── 🔑 Secret Scan    →  Gitleaks credential detection
Stage 6 ── 📦 Container Scan →  Trivy CVE scanning
Stage 7 ── 🏗️  IaC Security  →  Checkov Dockerfile & config scan
Stage 8 ── 🚦 Policy Gate    →  Block if CRITICAL/HIGH found
Stage 9 ── 🤖 AI Summary     →  Groq LLM security report
```

---

## 🔐 Security Features

<details>
<summary><b>🛡️ Application Security</b> (click to expand)</summary>

- ✅ BCrypt password hashing — strength 12
- ✅ Session fixation prevention
- ✅ HTTP security headers enforced
- ✅ XSS protection enabled
- ✅ CSRF protection active
- ✅ Secure session timeout — 10 minutes
- ✅ Non-root Docker container

</details>

<details>
<summary><b>🏥 HIPAA Compliance Controls</b> (click to expand)</summary>

- ✅ Audit logging for all sensitive operations
- ✅ Patient data access logging
- ✅ Failed login attempt tracking
- ✅ Session timeout enforcement
- ✅ Role-based access control (RBAC)

</details>

<details>
<summary><b>⚙️ DevSecOps Pipeline Security</b> (click to expand)</summary>

- ✅ **CodeQL** — Static Application Security Testing
- ✅ **Gitleaks** — Hardcoded secret detection
- ✅ **Trivy** — Container vulnerability scanning
- ✅ **Checkov** — Infrastructure-as-Code security
- ✅ **OWASP** — Dependency vulnerability checking
- ✅ **Policy Gate** — Automatic deployment blocking

</details>

---

## 📊 Compliance Status

<div align="center">

| Standard | Status | Coverage |
|---|---|---|
| 🏥 HIPAA | ✅ Compliant | Audit logging · Session control · Access control |
| 🔟 OWASP Top 10 | ✅ Mitigated | Security headers · Input validation · Hashing |
| 🐳 CIS Docker | ✅ Compliant | Non-root user · Minimal image · Health checks |
| 🏛️ NIST | ✅ Aligned | SAST · SCA · Secret scanning · Policy enforcement |

</div>

---

## 📁 Project Structure

```
📦 secure-healthcare-devsecops/
├── 📁 .github/
│   └── 📁 workflows/
│       └── 📄 secure-pipeline.yml     ← CI/CD pipeline definition
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/com/healthcare/    ← Spring Boot source code
│   │   └── 📁 resources/
│   │       ├── 📁 templates/          ← Thymeleaf HTML templates
│   │       └── 📁 static/             ← CSS / JS assets
│   └── 📁 test/                       ← JUnit test cases
├── 🐳 Dockerfile                      ← Multi-stage secure image
├── 📄 pom.xml                         ← Maven dependencies
├── ⚙️  mvnw                           ← Maven wrapper
├── 🔒 security-policy.sh             ← Policy gate script
├── 🤖 ai-security-summary.py         ← Groq AI analysis
├── 📋 SECURITY.md                     ← Security documentation
├── 🤖 dependabot.yml                  ← Automated dependency updates
└── 📖 README.md                       ← You are here
```

---

## ▶️ Run Locally

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Manoj8541/secure-healthcare-devsecops.git
cd secure-healthcare-devsecops
```

### 2️⃣ Run with Maven

```bash
./mvnw spring-boot:run
```

### 3️⃣ Open in Browser

```
http://localhost:9090
```
---

## 🐳 Run with Docker

```bash
# Build the image
docker build -t healthcare-app .

# Run the container
docker run -p 9090:9090 healthcare-app
```

```
✅ App running at http://localhost:9090
```

---

## 🔒 GitHub Security Dashboard

After pushing, navigate to:

```
Your Repo → Security tab → View all alerts
```

| Alert Type | Tool |
|---|---|
| 🔍 Code Scanning Alerts | CodeQL |
| 🔑 Secret Scanning Alerts | Gitleaks |
| 📦 Dependency Alerts | Dependabot |
| 📋 Security Advisories | GitHub Native |

---

## 🎯 Why This Project Stands Out

```
🏆 Real Enterprise DevSecOps   →  Not just CI/CD — full security automation
🚦 Policy Enforcement          →  Insecure code never reaches production
🔁 Multi-layer Security        →  App + Container + Pipeline + Compliance
🤖 AI Integration              →  Real LLM used for intelligent security reporting
🏥 Healthcare Compliance       →  HIPAA-ready architecture from ground up
✨ Clean Code Quality           →  Professional structure and documentation
```

---

## 📝 License

```
This project is built for educational and portfolio demonstration purposes.
```

---

<div align="center">

### 👨‍💻 Built to demonstrate Enterprise DevSecOps for Healthcare Systems

<br/>

**⭐ Star this repo if it helped you — it means a lot!**

<br/>
</div>
