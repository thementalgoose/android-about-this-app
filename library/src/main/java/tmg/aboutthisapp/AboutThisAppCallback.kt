package tmg.aboutthisapp

interface AboutThisAppCallback {
    fun dependencyItemClicked(item: AboutThisAppDependency)
    fun clickPlay()
    fun clickEmail()
    fun clickWebsite()
    fun clickGithub()
}