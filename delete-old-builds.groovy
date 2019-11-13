import jenkins.model.Jenkins
import hudson.model.Job

// how much history do you need?
MAX_BUILDS = 5

for (job in Jenkins.instance.items) {
    def recent = job.builds.limit(MAX_BUILDS)
    for (build in job.builds) {
        if (!recent.contains(build)) {
            if (build.keepLog || build.building) {
      	        // if build is marked to keep or is currently building do not delete
                println "Keep build: " + build
            }
            else {
                // delete build
      	        println "Deleting build: " + build
                build.delete()
            }
        }
    }
}