security:
    name: Security Scans
    runs-on: ubuntu-latest
    needs: docker
    permissions:
      security-events: write
      actions: read
      contents: read

    steps:
      - name: Checkout Repository
        uses: actions/checkout@v4.2.2
        with:
          fetch-depth: 0

      - name: Setup Docker Buildx
        uses: docker/setup-buildx-action@v3.10.0

      - name: Build Docker Image for Scanning
        run: docker build -t healthcare-app:latest .

      - name: Gitleaks Secret Scan
        uses: gitleaks/gitleaks-action@v2.3.9
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
        # ✅ Removed continue-on-error — now actually blocks

      - name: Trivy Docker Image Scan
        uses: aquasecurity/trivy-action@master
        with:
          image-ref: healthcare-app:latest
          format: table
          output: trivy-results.txt
          severity: HIGH,CRITICAL
          exit-code: '1'
          # ✅ exit-code 1 — blocks if HIGH or CRITICAL found

      - name: Upload Trivy Results
        uses: actions/upload-artifact@v4.6.2
        with:
          name: trivy-results
          path: trivy-results.txt
        continue-on-error: true

      - name: Checkov Dockerfile Scan
        uses: bridgecrewio/checkov-action@v12
        with:
          file: Dockerfile
          framework: dockerfile
          soft_fail: false
          # ✅ soft_fail false — blocks if insecure config found


  policy-gate:
    name: Policy Enforcement Gate
    runs-on: ubuntu-latest
    needs: [ security, codeql ]

    steps:
      - name: Checkout Repository
        uses: actions/checkout@v4.2.2
        with:
          fetch-depth: 0

      - name: Download Trivy Results
        uses: actions/download-artifact@v4
        with:
          name: trivy-results
        continue-on-error: true

      # ✅ No .sh needed — policy enforced by scan exit codes above
      - name: Deployment Decision
        run: |
          echo "=========================================="
          echo " POLICY ENFORCEMENT GATE"
          echo "=========================================="
          echo " Build        : PASSED"
          echo " Tests        : PASSED"
          echo " Docker Build : PASSED"
          echo " CodeQL SAST  : SCANNED"
          echo " Gitleaks     : SCANNED"
          echo " Trivy        : SCANNED"
          echo " Checkov      : SCANNED"
          echo "=========================================="
          echo " DEPLOYMENT   : ALLOWED"
          echo "=========================================="