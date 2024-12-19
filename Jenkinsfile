pipeline {
    agent {
        docker {
            image 'maven:3.8.8-eclipse-temurin-17'
            args '''
                -v /var/run/docker.sock:/var/run/docker.sock
                --network samurai_net
            '''
        }
    }
    environment {
        SONAR_PROJECT_KEY = "Hunters_League"
        SONAR_TOKEN = "sqa_4bd153196a6890aaebaa8e23d1e59842a5563a0b"
        SONAR_HOST_URL = "http://host.docker.internal:9001"
    }
    stages {
        stage('Build and SonarQube Analysis') {
            steps {
                echo "Running Maven build and SonarQube analysis..."
                sh """
                mvn clean package sonar:sonar \
                  -Dsonar.projectKey=$SONAR_PROJECT_KEY \
                  -Dsonar.host.url=$SONAR_HOST_URL \
                  -Dsonar.login=$SONAR_TOKEN
                """
            }
        }
        stage('Quality Gate Check') {
            steps {
                script {
                    echo "Waiting for SonarQube Quality Gate..."
                    def qualityGate = waitForQualityGate()
                    if (qualityGate.status != 'OK') {
                        error "Quality Gate failed! Stopping the build."
                    }
                    echo "Quality Gate passed! Proceeding..."
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    echo "Building Docker Image..."
                    sh 'docker build -t hunters_league_img .'
                }
            }
        }
        stage('Deploy Docker Container') {
            steps {
                script {
                    echo "Deploying Docker container..."
                    sh """
                    docker stop hunters_league_container || true
                    docker rm hunters_league_img || true
                    docker run -d --name hunters_league_container hunters_league_img
                    """
                }
            }
        }
    }
}