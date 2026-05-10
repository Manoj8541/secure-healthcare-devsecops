import os
from groq import Groq

# Groq free API — Real AI (Llama 3)
client = Groq(
    api_key=os.environ["GROQ_API_KEY"]
)

# Read Trivy results
trivy_results = ""
try:
    with open("trivy-results.txt", "r",
              encoding="utf-8", errors="ignore") as f:
        trivy_results = f.read()[:4000]
except FileNotFoundError:
    trivy_results = "No Trivy results file found."

# Build prompt
prompt = f"""
You are a DevSecOps security analyst for a healthcare application.

Analyze these security scan results and provide:
1. Brief summary of findings (2-4 bullets)
2. Risk level (LOW / MEDIUM / HIGH / CRITICAL)
3. Top 3 remediation recommendations (actionable)
4. HIPAA compliance status (2-3 bullets)
5. OWASP alignment (2-3 bullets)

Security Scan Results (Trivy container scan):
{trivy_results}

Keep response professional, concise, and suitable for an engineering/security review.
"""

# Call Groq AI (Llama 3)
response = client.chat.completions.create(
    model="llama3-8b-8192",
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

print("==========================================")
print(" AI SECURITY ANALYSIS (Groq - Llama 3)")
print("==========================================")
print(response.choices[0].message.content)
print("==========================================")