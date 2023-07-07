def lintchecks(){
    sh '''
        echo Performing lintchecks for ${COMPONENT}
        mvn checkstyle:check || true
        echo link checks completed for ${COMPONENT}
    '''    
}

def call(COMPONENT){
pipeline{
    agent {
        label 'ws'
    }
    stages{                                              //start of the stages
        stage("Lint checks"){
          steps{
                script{
                   lintchecks()
                }
            }  
            
        }
        stage("Code Compile"){
          steps{
            sh "mvn clean compile"
            }  
            
        }
     }
   
   }
}


//          script{
//                    sample.info('CART')
//                 }