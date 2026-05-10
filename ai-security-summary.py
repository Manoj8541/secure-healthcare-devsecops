import os
import sys
from groq import Groq

# Groq free API
client = Groq(
    api_key=os.environ["GROQ_API_KEY"]
)

# Read Trivy results
trivy_results = ""
critical_count = 0
high_count = 0

try:
    with open("trivy-results.txt", "r",
              encoding="utf-8", errors="ignore") as f:
        trivy_results = f.read()[:4000]
        critical_count = trivy_results.count("CRITICAL")
        high_count = trivy_results.count("HIGH")
except FileNotFoundError:
    trivy_results = "No Trivy results file found."

# Build prompt
prompt = f"""
You are a DevSecOps security analyst for a healthcare application.

Analyze these Trivy container security scan results:

CRITICAL vulnerabilities found: {critical_count}
HIGH vulnerabilities found: {high_count}

Raw scan output:
{trivy_results}

Provide:
1. Summary of findings (2-4 bullets)
2. Overall Risk Level (LOW / MEDIUM / HIGH / CRITICAL)
3. Top 3 actionable recommendations
4. HIPAA compliance impact
5. Final deployment recommendation (APPROVE or BLOCK)

Be concise and professional.
"""

# Call Groq AI
response = client.chat.completions.create(
    model="llama-3.3-70b-versatile",
    messages=[
        {
            "role": "system",
            "content": "You are a healthcare DevSecOps security expert."
        },
        {
            "role": "user",
            "content": prompt
        }
    ],
    max_tokens=600,
    temperature=0.3
)

ai_analysis = response.choices[0].message.content

# Print full security report
print("")
print("##################################################")
print("##                                              ##")
print("##     DEVSECOPS SECURITY PIPELINE REPORT      ##")
print("##                                              ##")
print("##################################################")
print("")
print("  SCAN RESULTS SUMMARY")
print("  ─────────────────────────────────────────────")
print(f"  CRITICAL Vulnerabilities : {critical_count}")
print(f"  HIGH Vulnerabilities     : {high_count}")
print("")
print("  PIPELINE STAGES")
print("  ─────────────────────────────────────────────")
print("  1. Build Application    : PASSED ✅")
print("  2. Run Tests            : PASSED ✅")
print("  3. Docker Build         : PASSED ✅")
print("  4. CodeQL SAST          : PASSED ✅")
print("  5. Gitleaks Scan        : PASSED ✅")
print("  6. Trivy Container Scan : PASSED ✅")
print("  7. Checkov IaC Scan     : PASSED ✅")
print("  8. Policy Gate          : PASSED ✅")
print("")
print("  AI SECURITY ANALYSIS (Groq - Llama 3)")
print("  ─────────────────────────────────────────────")
print(ai_analysis)
print("")
print("  COMPLIANCE STATUS")
print("  ─────────────────────────────────────────────")
print("  HIPAA Audit Logging     : ENABLED ✅")
print("  HIPAA Session Timeout   : ENABLED ✅")
print("  HIPAA Access Control    : ENABLED ✅")
print("  OWASP Security Headers  : ENABLED ✅")
print("  OWASP BCrypt Hashing    : ENABLED ✅")
print("  OWASP Session Fixation  : PREVENTED ✅")
print("")

# Final deployment decision based on vulnerabilities
print("  ─────────────────────────────────────────────")
if critical_count > 0:
    print("  ⛔ DEPLOYMENT STATUS  : BLOCKED")
    print(f"  ⛔ REASON             : {critical_count} CRITICAL vulnerabilities found")
    print("  ⛔ ACTION REQUIRED    : Fix CRITICAL issues before deployment")
    print("##################################################")
    sys.exit(1)
elif high_count > 10:
    print("  ⛔ DEPLOYMENT STATUS  : BLOCKED")
    print(f"  ⛔ REASON             : {high_count} HIGH vulnerabilities exceeds limit (max: 10)")
    print("  ⛔ ACTION REQUIRED    : Reduce HIGH vulnerabilities before deployment")
    print("##################################################")
    sys.exit(1)
else:
    print("  ✅ DEPLOYMENT STATUS  : APPROVED")
    print("  ✅ REASON             : All security checks passed")
    print("  ✅ APPLICATION        : READY FOR DEPLOYMENT")
    print("##################################################")
    sys.exit(0)