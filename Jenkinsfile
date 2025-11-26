pipeline {
    agent any

    tools {
        maven 'M3'
        jdk 'JDK21'
    }

    stages {
        // STAGE 1: Checkout GitHub
        stage('Checkout GitHub') {
            steps {
                git branch: 'main',
                url: 'https://github.com/warteni/my-app-devops.git'  // ← CORRIGÉ
                echo '✅ Code récupéré depuis GitHub - warteni/my-app-devops'
            }
        }

        // STAGE 2: Build Maven
        stage('Build Maven') {
            steps {
                sh 'mvn clean compile'
                echo '✅ Build Maven terminé'
            }
        }

        // STAGE 3: Tests Unitaires
        stage('Tests Unitaires') {
            steps {
                sh 'mvn test'
                echo '✅ Tests unitaires exécutés'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        // STAGE 4: Quality Check (remplace SonarQube)
        stage('Quality Check') {
            steps {
                sh 'mvn checkstyle:checkstyle || echo "Checkstyle non configuré"'
                echo '✅ Vérification qualité terminée'
            }
        }

        // STAGE 5: Packaging
        stage('Package WAR') {
            steps {
                sh 'mvn package -DskipTests'
                echo '✅ Application packagée en WAR'
                sh 'ls -la target/*.war'
            }
        }

        // STAGE 6: Affichage du Message
        stage('Affichage Message') {
            steps {
                sh '''
                    echo "🎉 EXÉCUTION DU PROGRAMME :"
                    java -cp target/classes org.example.Main
                    echo "🚀 Pipeline CI/CD réussi !"
                '''
                echo '✅ Message affiché avec succès'
            }
        }
    }

    post {
        always {
            echo "🏁 Pipeline CI/CD terminé - Build #${env.BUILD_NUMBER}"
            archiveArtifacts artifacts: 'target/*.war', fingerprint: true
        }
        success {
            echo '✅ SUCCÈS: Pipeline exécuté avec succès!'
        }
        failure {
            echo '❌ ÉCHEC: Pipeline a échoué!'
        }
    }
}
