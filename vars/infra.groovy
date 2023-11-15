def call() {
     properties([
            parameters([
                choice(choices: 'dev\nprod', description: "Select your environment", name: "ENV"),
                choice(choices: 'apply\ndestroy', description: "Chose an action", name: "ACTION")
            ]),
        ])
    node{

        ansiColor('xterm'){
            git branch; 'main', url: "https://github.com/SpandanaKopppaku/${REPONAME}.git"
        
            stage('terraform init') {
                sh "${TFDIR}"
                sh "terrafile -f env-${ENV}/Terrafile"
                sh "terraform init -backend-config=env-${ENV}/${ENV}-backend.tfvars"
            }

            stage('terraform plan') {
                sh "${TFDIR}"
                sh "terraform plan -var-file=env-${ENV}/${ENV}.tfvars"
            }

            stage('terraform action') {   
                sh "terraform ${ACTION} -auto-approve -var-file=env-${ENV}/${ENV}.tfvars"

                }
            }

        }

    }

// pipeline {
//     agent {
//         label 'ws'
//     }
//     options {
//         ansiColor('xterm')
//     }
//     parameters {
//         choice(name: 'ENV', choices: ['dev', 'prod'], description: 'Select The Environment')
//         choice(name: 'ACTION', choices: ['apply', 'destroy'], description: 'Select Create or Destroy')
//     }

//     stages {
//         stage('terraform init') {
//             steps {    
//                     sh "terrafile -f env-${ENV}/Terrafile"
//                     sh "terraform init -backend-config=env-${ENV}/${ENV}-backend.tfvars"
//             }
//         }

//         stage('terraform plan') {
//             steps {    
//                     sh "terraform plan -var-file=env-${ENV}/${ENV}.tfvars"
//             }
//         }

//         stage('terraform apply') {
//             steps {    
//                     sh "terraform ${ACTION} -auto-approve -var-file=env-${ENV}/${ENV}.tfvars"
//             }
//         }
//     }
// }        