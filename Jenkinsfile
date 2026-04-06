pipeline {
    agent any

    environment {
        IMAGE_NAME = "spring-boot-app"
        CONTAINER  = "spring-app"
        PORT       = "9090"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'github-creds',
                    url: 'https://github.com/seangnang/Kotlin-Springboot.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t spring-boot-app .'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker stop spring-app || true
                    docker rm spring-app || true
                    docker run -d \
                        --name spring-app \
                        -p 9090:8080 \
                        --restart unless-stopped \
                        spring-boot-app
                '''
            }
        }
    }

    post {
        success { echo 'App live at port 9090' }
        failure { echo 'Build failed - check Console Output' }
    }
}
