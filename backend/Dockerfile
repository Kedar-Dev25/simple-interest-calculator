name: Java Backend CI

on:
  push:
    paths:
      - 'backend/**'
  pull_request:
    paths:
      - 'backend/**'

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout repository
        uses: actions/checkout@v3

      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '17'

      - name: Navigate to backend
        run: cd backend

      - name: Build with Maven
        run: ./mvnw clean compile

      - name: Verify build
        run: ./mvnw test
