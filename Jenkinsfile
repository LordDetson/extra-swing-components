pipeline {
  agent any
  tools {
    jdk 'openjdk17'
    maven 'maven-3.6.3'
  }
  stages {
    stage ('Build extra-swing-components') {
      steps {
        withSonarQubeEnv('SonarQube') {
          sh 'mvn clean -Dmaven.test.failure.ignore=true install $SONAR_MAVEN_GOAL'
        }
      }
    }
  }
}
