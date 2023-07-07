def lintchecks(){
    sh '''
        echo Performing lintchecks for ${COMPONENT}
        #pip3 install pylint
        #pylint *.py
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
            sh "npm install"
            }  
            
        }
     }
   
   }
}


//          script{
//                    sample.info('CART')
//                 }