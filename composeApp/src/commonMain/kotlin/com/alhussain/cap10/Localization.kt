package com.alhussain.cap10

enum class AvailableLanguages {
    AR,
    EN,
}

expect fun getCurrentLanguage(): AvailableLanguages

expect fun getCurrentPlatform(): String

interface Localization {
    val appName: String
    val bookStadium: String
    val leagues: String
    val officials: String
    val posts: String
    val home: String
    val myTeams: String
    val stadiums: String
}

private object EnglishLocalization : Localization {
    override val appName = "Cap10"
    override val bookStadium = "Book Stadium"
    override val leagues = "Leagues"
    override val officials = "Officials"
    override val posts = "Posts"
    override val home = "Home"
    override val myTeams = "My Teams"
    override val stadiums = "Stadiums"
}

private object ArabicLocalization : Localization {
    override val appName = "Cap10"
    override val bookStadium = "حجز ملعب"
    override val leagues = "البطولات"
    override val officials = "الرسميات"
    override val posts = "المنشورات"
    override val home = "الرئيسية"
    override val myTeams = "فرقي"
    override val stadiums = "الملاعب"
}

fun getCurrentLocalization() =
    when (getCurrentLanguage()) {
        AvailableLanguages.EN -> EnglishLocalization
        AvailableLanguages.AR -> ArabicLocalization
    }
