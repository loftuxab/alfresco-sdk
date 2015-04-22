package gradle.configuration


/**
 * @author Laurent Van der Linden
 */
class Repository {
    Authentication authentication = new Authentication()
    Endpoint endpoint = new Endpoint()
    Project project

    Repository(Project project) {
        this.project = project
    }

    def authentication(Closure closure) {
        project.configure(authentication, closure)
    }

    def endpoint(Closure closure) {
        project.configure(endpoint, closure)
    }
}
