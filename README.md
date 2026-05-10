# 🏥 Secure Healthcare DevSecOps

Enterprise-grade **DevSecOps** implementation for a Healthcare Patient Management System with automated security scanning, policy enforcement, and compliance controls.

---

## 📌 Project Overview

This project demonstrates a **complete DevSecOps pipeline** for a healthcare application, implementing:

- Spring Boot REST API with secure authentication
- Docker containerization with security best practices
- Comprehensive security scanning (SAST, SCA, Secrets, IaC)
- Automated policy enforcement gate
- HIPAA compliance controls
- Real AI-powered security analysis

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                  SECURE HEALTHCARE DEVSECOPS                │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│  ┌──────────┐    ┌──────────┐    ┌─────────────────────┐  │
│  │  Spring  │───▶│  Docker  │───▶│   GitHub Actions    │  │
│  │   Boot   │    │Container │    │   CI/CD Pipeline    │  │
│  └──────────┘    └──────────┘    └─────────────────────┘  │
│        │               │                    │              │
│        ▼               ▼                    ▼              │
│  ┌─────────────────────────────────────────────────────┐   │
│  │               SECURITY SCANNING LAYER               │   │
│  │    CodeQL  │  Gitleaks  │  Trivy  │  Checkov │ OWASP│   │
│  └─────────────────────────────────────────────────────┘   │
│                            │                               │
│                            ▼                               │
│                 ┌─────────────────┐                        │
│                 │   Policy Gate   │                        │
│                 │  ALLOW / BLOCK  │                        │
│                 └─────────────────┘                        │
└─────────────────────────────────────────────────────────────┘
```

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Spring Boot 4.0.6 (Java 17) |
| Frontend | Thymeleaf + Bootstrap |
| Authentication | Spring Security + BCrypt |
| Containerization | Docker (Multi-stage, Non-root) |
| CI/CD | GitHub Actions |
| SAST | CodeQL |
| Secret Detection | Gitleaks |
| Container Scan | Trivy |
| IaC Security | Checkov |
| Dependency Check | OWASP Dependency-Check |
| AI Analysis | Groq (Llama 3.3) |

---

## 🔐 Security Features Implemented

### Application Security
- BCrypt password hashing (strength 12)
- Session fixation prevention
- HTTP security headers
- XSS protection
- CSRF protection
- Secure session management (10 min timeout)
- Non-root Docker container

### Compliance (HIPAA)
- Audit logging for all sensitive operations
- Patient data access logging
- Failed login attempt tracking
- Session timeout enforcement
- Role-based access control

### DevSecOps Pipeline
- **CodeQL** — Static Application Security Testing (SAST)
- **Gitleaks** — Hardcoded secret detection
- **Trivy** — Container vulnerability scanning
- **Checkov** — Infrastructure-as-Code security
- **OWASP** — Dependency vulnerability checking
- **Policy Gate** — Automatic deployment blocking

---

## 🚀 Pipeline Stages

| Stage | Description | Tool |
|---|---|---|
| 1. Build | Compile & package application | Maven |
| 2. Test | Run unit tests | JUnit |
| 3. Docker | Build secure container image | Docker |
| 4. SAST | Static code analysis | CodeQL |
| 5. Secret Scan | Detect hardcoded credentials | Gitleaks |
| 6. Container Scan | Find OS & dependency CVEs | Trivy |
| 7. IaC Security | Scan Dockerfile & configs | Checkov |
| 8. Policy Gate | Block if CRITICAL/HIGH found | Custom Script |
| 9. AI Summary | Intelligent security report | Groq LLM |

---

## 📁 Project Structure

```
secure-healthcare-devsecops/
├── .github/
│   └── workflows/
│       └── secure-pipeline.yml
├── src/
│   ├── main/
│   │   ├── java/com/healthcare/
│   │   └── resources/
│   │       ├── templates/
│   │       └── static/
│   └── test/
├── Dockerfile
├── pom.xml
├── mvnw
├── security-policy.sh
├── ai-security-summary.py
├── SECURITY.md
├── dependabot.yml
└── README.md
```

---

## ▶️ How to Run Locally

### 1. Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/secure-healthcare-devsecops.git
cd secure-healthcare-devsecops
```

### 2. Run with Maven

```bash
./mvnw spring-boot:run
```

### 3. Access Application

```
http://localhost:9090
```

**Default Login:**

| Field | Value |
|---|---|
| Username | admin |
| Password | password123 |

---

## 🐳 Run with Docker

```bash
# Build image
docker build -t healthcare-app .

# Run container
docker run -p 9090:9090 healthcare-app
```

---

## 🔒 Security Dashboard (GitHub)

After pushing to GitHub, go to:

```
Repository → Security tab
```

You will see:

- Code Scanning Alerts (CodeQL)
- Secret Scanning Alerts (Gitleaks)
- Dependabot Alerts
- Security Advisories

---

## 📊 Compliance Status

| Standard | Status | Implementation |
|---|---|---|
| HIPAA | ✅ Compliant | Audit logging, session control, access control |
| OWASP Top 10 | ✅ Mitigated | Security headers, input validation, hashing |
| CIS Docker | ✅ Compliant | Non-root user, minimal image, health checks |
| NIST | ✅ Aligned | SAST, SCA, secret scanning, policy enforcement |

---

## 🎯 Interview Highlights

This project demonstrates:

- **Real Enterprise DevSecOps** — Not just CI/CD, but security automation
- **Policy Enforcement** — Code cannot reach production if insecure
- **Multi-layer Security** — Application + Container + Pipeline + Compliance
- **AI Integration** — Real LLM used for security analysis
- **Healthcare Compliance** — HIPAA-ready architecture
- **Clean Code Quality** — Professional structure and documentation

---

## 📝 License

This project is for educational and portfolio purposes.

---

## 👨‍💻 Author

Built as a demonstration of Enterprise DevSecOps practices for healthcare systems.

---

⭐ Star this repository if you found it helpful!
