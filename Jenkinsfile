pipeline {
    agent any

    environment {
        AWS_REGION = 'ap-south-1'
        AWS_ACCOUNT_ID = 'AROA57LZ7YA4RRW4YI4WV:i-042b0bb1b888913e0'
        ECR_REPO = 'jenkins-automation'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Verify Jar') {
            steps {
                sh 'ls -ltr target/'
            }
        }

        stage('Docker Build') {
            steps {
                sh '''
                    docker build -t jenkins-automation .
                '''
            }
        }

        stage('Debug Environment') {
            steps {
                sh '''
                    echo REGION=$AWS_REGION
                    echo ACCOUNT=$AWS_ACCOUNT_ID
                    echo REPO=$ECR_REPO
                '''
            }
        }

        stage('ECR Login') {
            steps {
                sh '''
                    aws ecr get-login-password \
                    --region $AWS_REGION | docker login \
                    --username AWS \
                    --password-stdin \
                    $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com
                '''
            }
        }

        stage('Tag Image') {
            steps {
                sh '''
                    docker tag jenkins-automation:latest \
                    $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPO:latest
                '''
            }
        }

        stage('Push Image') {
            steps {
                sh '''
                    docker push \
                    $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPO:latest
                '''
            }
        }
    }
}
