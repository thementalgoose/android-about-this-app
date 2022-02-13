package tmg.aboutthisapp

interface AboutThisAppCallback {
    fun dependencyItemClicked(item: AboutThisAppDependency)
    fun clickUrl(url: String?)
    fun sendEmail(email: String?, subject: String)
}