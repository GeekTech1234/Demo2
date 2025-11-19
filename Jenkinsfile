pipeline
{
    agent any

    stages
    {
        stage('SmokeTest')
        {
            steps
            {
               build 'MyFirstJenkinsJob'
            }
        }

        stage('RegressionTest')
        {
            steps
            {
                build 'MySecondJenkinsJob'
            }
        }

        stage('E2ETest')
        {
            steps
            {
                build 'SeleniumTest'   
            }
        }
    }

    post
    {

      always
      {
          emailext body: 'Pipeline execution is completed hence sending an email at the end', subject: 'Pipeline completed', to: 'bhavikparmar0711@gmail.com,bhavikparmargithub@gmail.com,nikitadaredi@gmail.com'
      }

      success
      {
          emailext body: 'Pipeline execution is successfully completed without any failure', subject: 'Success', to: 'bhavikparmar0711@gmail.com,bhavikparmargithub@gmail.com,nikitadaredi@gmail.com'
      }

      failure
      {
          error 'Build is failed'
          emailext body: 'Pipeline execution is completed and there are failures, please look into this asap.', subject: 'Failure', to: 'bhavikparmar0711@gmail.com,bhavikparmargithub@gmail.com,nikitadaredi@gmail.com'
      }

      unstable
      {
          unstable 'Build is unstable hence pipelinevexecution is unstabled'
      }

      cleanup
      {
          echo 'Cleanup process is started'
      }

    }
}
