node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
//     def mvn = tool 'Default Maven';
//     withSonarQubeEnv() {
//       sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=Hunters_League"
//     }
    // hello world
    sh 'echo "SonarQube Analysis...!"'
  }
}