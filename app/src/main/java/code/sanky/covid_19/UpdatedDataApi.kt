package code.sanky.covid_19

data class UpdatedDataApi(
    val data: Data,
    val lastOriginUpdate: String,
    val lastRefreshed: String,
    val success: Boolean
)

data class Data(
    val regional: List<Regional>,
    val summary: Summary,
)

data class Summary(
    val confirmedButLocationUnidentified: Int,
    val confirmedCasesForeign: Int,
    val confirmedCasesIndian: Int,
    val deaths: Int,
    val discharged: Int,
    val total: Int
)

data class Regional(
    val confirmedCasesForeign: Int,
    val confirmedCasesIndian: Int,
    val deaths: Int,
    val discharged: Int,
    val loc: String,
    val totalConfirmed: Int
)