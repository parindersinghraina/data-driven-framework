pipeline {
    agent none
    parameters {
        choice(
          choices: ['test', 'live'],
          description: 'Select a target secrets environment.',
          name: 'SECRET_ENVIRONMENT')
    }
    environment {
        SECRET_ENVIRONMENT = "${params.SECRET_ENVIRONMENT}"
    }
    stages {
        stage('test') {
            steps {
                script {
                    def secrets = [
                        [path: 'secret/test', engineVersion: 1, secretValues: [
                        [envVar: 'TEST_USERNAME', vaultKey: 'TEST_USERNAME'],
                        [envVar: 'TEST_PASSWORD', vaultKey: 'TEST_PASSWORD']]]
                    ]
                    // inside this block your credentials will be available as env variables
                    withVault([configuration: configuration, vaultSecrets: secrets]) {
                        sh 'echo TEST_USERNAME'
                        sh 'echo TEST_PASSWORD'
                    }
                    def props = "Building and running tests"
                    props += "\nSECRET-ENVIRONMENT: ${env.SECRET_ENVIRONMENT}"
                    echo props
                    sh "./gradlew clean build"
                }
            }
        }
    }
    post {
        always {
            container('test-runner'){
                echo "Changing permissions on build folder, writing properties file, and generating Allure report"
                script {
                    sh 'chmod -R 777 build'
                    def props = "BRANCH=${env.BRANCH_NAME}"
                    props += "\nBUILD=${env.BUILD_NUMBER}"
                    props += "\nSECRET-ENVIRONMENT=${env.AUTOMATED_TEST_ENV_CONFIG}"
                    writeFile(file: "build/allure-results/environment.properties", text: props, encoding: "UTF-8")
                    allure results: [[path: 'build/allure-results']]
                }
              }
        }
    }
}
