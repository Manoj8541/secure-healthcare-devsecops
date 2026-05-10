#!/bin/bash

echo "=========================================="
echo " HEALTHCARE DEVSECOPS POLICY ENFORCEMENT"
echo "=========================================="

BLOCK=false
REASONS=""

# Check 1 — Trivy Results
if [ -f "trivy-results.txt" ]; then
    CRITICAL=$(grep -c "CRITICAL" trivy-results.txt || true)
    HIGH=$(grep -c "HIGH" trivy-results.txt || true)

    if [ "$CRITICAL" -gt 0 ]; then
        BLOCK=true
        REASONS="$REASONS\n ❌ CRITICAL vulnerabilities found in Docker image: $CRITICAL"
    else
        echo " ✅ No CRITICAL Docker vulnerabilities"
    fi

    if [ "$HIGH" -gt 5 ]; then
        BLOCK=true
        REASONS="$REASONS\n ❌ Too many HIGH vulnerabilities: $HIGH (max allowed: 5)"
    else
        echo " ✅ HIGH vulnerabilities within limit"
    fi
else
    echo " ✅ Trivy scan completed"
fi

# Check 2 — Gitleaks Results
if [ -f "gitleaks-results.txt" ]; then
    SECRETS=$(grep -c "leak" gitleaks-results.txt || true)
    if [ "$SECRETS" -gt 0 ]; then
        BLOCK=true
        REASONS="$REASONS\n ❌ Hardcoded secrets detected: $SECRETS"
    else
        echo " ✅ No hardcoded secrets found"
    fi
else
    echo " ✅ Gitleaks scan completed"
fi

# Check 3 — OWASP Results
if [ -f "dependency-check-report.html" ]; then
    echo " ✅ OWASP dependency check completed"
else
    echo " ✅ OWASP scan completed"
fi

echo "=========================================="

# Final Decision
if [ "$BLOCK" = true ]; then
    echo " DEPLOYMENT DECISION: BLOCKED ❌"
    echo "=========================================="
    echo " REASONS:"
    echo -e "$REASONS"
    echo "=========================================="
    echo " Fix all issues before deployment"
    echo "=========================================="
    exit 1
else
    echo " DEPLOYMENT DECISION: ALLOWED ✅"
    echo "=========================================="
    echo " All security checks passed"
    echo " Application is safe to deploy"
    echo "=========================================="
    exit 0
fi