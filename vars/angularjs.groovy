def lintchecks(){
    sh '''
        echo Installing angularlint for ${COMPONENT}
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
        stage("Code Qality Analysis"){
          steps{
            sh "echo Code Qality Analysis is in place"
            }  
            
        }
     }
   
   }
}


//          script{
//                    sample.info('CART')
//                 }