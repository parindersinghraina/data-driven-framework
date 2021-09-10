pipeline {
    agent any
    parameters {
        choice(
          choices: ['all'],
          description: 'Select a suite.',
          name: 'SUITE')
    }
    environment {
        AUTOMATED_TEST_SUITE = "${params.SUITE}"
    }
    stages {
        stage('test') {
            steps {
                script {
                    def props = "Building and running testing-automated"
                    props += "\nBRANCH:      ${env.BRANCH_NAME}"
                    props += "\nBUILD:       ${env.BUILD_NUMBER}"
                    props += "\nSUITE:       ${env.AUTOMATED_TEST_SUITE}"
                    echo props
                    sh "/usr/local/bin/docker-compose up --no-color --abort-on-container-exit"
                }
            }
        }
    }
    post {
        always {
                echo "Changing permissions on build folder, writing properties file, and generating Allure report"
                script {
                    sh 'chmod -R 777 build'
                    def props = "BRANCH=${env.BRANCH_NAME}"
                    props += "\nBUILD=${env.BUILD_NUMBER}"
                    props += "\nSUITE=${env.AUTOMATED_TEST_SUITE}"
                    writeFile(file: "build/allure-results/environment.properties", text: props, encoding: "UTF-8")
                    allure results: [[path: 'build/allure-results']]
                    sh '/usr/local/bin/docker-compose down'
                }
        }
    }
}