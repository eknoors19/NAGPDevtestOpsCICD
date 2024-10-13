pipeline{
  agent any
  tools{
     maven 'MAVEN_HOME'
  }
  stages{
    stage("Checkout"){
      steps{
        checkout scm
      }
    }
    stage("Build"){
      steps{
       bat 'mvn clean'
      }
    }
    stage("Test"){
      steps{
       bat 'mvn test'
      }
    }
 
 	stage("TestNGReport"){
      steps{
       testNG()
      }
    }
    
    stage("SonarQube"){
      steps{
        withSonarQubeEnv("TestSonarQube"){
          bat "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.8.0.2131:sonar"
        }
      }
    }
    stage("Upload to Artifactory"){
      steps{
        rtMavenDeployer(
            id:'deployer',
            serverId: '3185683@artifactory',
            releaseRepo: 'NAGPDevTestOps',
            snapshotRepo: 'NAGPDevTestOps'
        )
        rtMavenRun(
            pom:'pom.xml',
            goals: 'clean install',
            deployerId: 'deployer'
        )
        rtPublishBuildInfo(
            serverId:'3185683@artifactory'
        )
      }
    }
  }
}