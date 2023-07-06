def lintchecks(){
    sh '''
        sh "echo Installing JSLint"
        sh "npm i jslint"
        sh "node_modules/jslint/bin/jslint.js server.js || true"
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