# Security Policy

## Supported Versions

| Version | Supported          |
| ------- | ------------------ |
| 1.0.x   | ✅ Yes             |

## Reporting a Vulnerability

If you discover a security vulnerability:

1. **Do NOT** open a public GitHub issue
2. Use GitHub Private Vulnerability Reporting
3. Or email: security@healthcare-app.com

## Response Timeline

| Step | Timeline |
|------|----------|
| Acknowledgement | Within 24 hours |
| Assessment | Within 72 hours |
| Fix | Within 7-14 days |
| Disclosure | After fix deployed |

## Security Measures

This application implements:

- BCrypt password hashing (strength 12)
- Session fixation prevention
- Security headers (XSS, Clickjacking)
- HIPAA audit logging
- Container security scanning (Trivy)
- SAST scanning (CodeQL)
- Secret detection (Gitleaks)
- Dependency scanning (Dependabot)
- Policy enforcement gate

## Compliance

- HIPAA compliant audit logging
- OWASP Top 10 mitigations
- CIS Docker benchmark (Checkov)
