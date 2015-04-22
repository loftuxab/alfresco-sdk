package gradle.configuration


/**
 * @author Laurent Van der Linden
 */
class BaseConfig {
    Repository repository = new Repository(project)
    Versions versions = new Versions()

    Project project

    BaseConfig(Project project) {
        this.project = project
    }

    def repository(Closure closure) {
        project.configure(repository, closure)
    }

    def versions(Closure closure) {
        project.configure(versions, closure)
    }
}
