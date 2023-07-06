def lintchecks(){
    sh '''
        echo Installing JSLint
        npm i jslint
        node_modules/jslint/bin/jslint.js server.js || true
    '''    
}

def call(){
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