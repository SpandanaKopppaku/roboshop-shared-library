def lintchecks(){
    sh '''
        echo Installing JSLint for ${COMPONENT}
        npm i jslint
        node_modules/jslint/bin/jslint.js server.js || true
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