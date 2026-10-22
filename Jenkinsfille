pipeline {

    agent any

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

    }
}
