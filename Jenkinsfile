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
                sh 'docker build -t \ .'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker stop \ || true
                    docker rm   \ || true
                    docker run -d \
                        --name \ \
                        -p \ \
                        --restart unless-stopped \
                        \
                '''
            }
        }
    }

    post {
        success { echo 'App live at http://YOUR_SERVER_IP:9090' }
        failure { echo 'Build failed - check Console Output' }
    }
}
