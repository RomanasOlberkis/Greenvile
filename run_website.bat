@echo off
echo ========================================
echo    Cloverville Website Server
echo ========================================
echo.

cd /d "C:\Users\Roman\Desktop\Cloverville\website"

echo Starting server at http://localhost:8000
echo.
echo Press Ctrl+C to stop the server
echo ========================================
echo.

start http://localhost:8000/dashboard.html

python -m http.server 8000

pause
