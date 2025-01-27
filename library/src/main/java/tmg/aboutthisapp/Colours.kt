package tmg.aboutthisapp

@Deprecated(
    message = "This class has moved packages",
    replaceWith = ReplaceWith(
        expression = "tmg.aboutthisapp.configuration.Colours(colorPrimary, background, surface, primary, onBackground, onSurface, onPrimary)",
        imports = arrayOf("tmg.aboutthisapp.configuration.Colours")
    ),
    level = DeprecationLevel.ERROR
)
class Colours(
    private val colorPrimary: Int,
    private val background: Int,
    private val surface: Int,
    private val primary: Int,
    private val onBackground: Int,
    private val onSurface: Int,
    private val onPrimary: Int,
)