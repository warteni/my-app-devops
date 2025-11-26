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
                url: 'https://github.com/ton-username/bonjour-devops-app.git'
                echo '✅ Code récupéré depuis GitHub'
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

        // STAGE 4: SAST SonarQube
        stage('SAST - SonarQube') {
            steps {
                script {
                    withSonarQubeEnv('sonar-server') {
                        sh 'mvn sonar:sonar -Dsonar.projectKey=bonjour-devops-app'
                    }
                }
                echo '✅ Analyse SonarQube terminée'
            }
        }

        // STAGE 5: Packaging
        stage('Package WAR') {
            steps {
                sh 'mvn package -DskipTests'
                echo '✅ Application packagée en WAR'
            }
        }

        // STAGE 6: Déploiement Tomcat
        stage('Déploiement Tomcat') {
            steps {
                sh '''
                    echo "🚀 Déploiement sur Tomcat..."
                    # Arrêt de Tomcat
                    sudo systemctl stop tomcat9 || true

                    # Nettoyage ancien déploiement
                    sudo rm -rf /var/lib/tomcat9/webapps/bonjour-devops*

                    # Copie du WAR
                    sudo cp target/bonjour-devops.war /var/lib/tomcat9/webapps/

                    # Démarrage Tomcat
                    sudo systemctl start tomcat9

                    sleep 10
                    echo "🎯 Application déployée: http://localhost:8080/bonjour-devops/"
                '''
                echo '✅ Déploiement Tomcat réussi'
            }
        }
    }

    post {
        always {
            echo '🏁 Pipeline CI/CD terminé'
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
