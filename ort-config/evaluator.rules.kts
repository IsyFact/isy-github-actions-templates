val allowedLicenses = licenseClassifications.licensesByCategory["allow"].orEmpty()

fun RuleSet.wrongLicenseInProject() = packageRule("WRONG_LICENSE_IN_PROJECT") {
    println("XXXXXXXXXXXXXXXX TEST XXXXXXXXXXXXXX")
    println("Allowed Licenses: ${allowedLicenses.joinToString()}")

    // Debug-Ausgabe aller licenseFindings
    licenseFindings.forEach { licenseFinding ->
        println("Found License: ${licenseFinding.license} in file ${licenseFinding.location.path}")
    }

    // Lizenzprüfung
    val detectedLicenses = licenseFindings.map { it.license }.toSet()
    println("Detected Licenses in Project: ${detectedLicenses.joinToString()}")

    val wrongLicenses = detectedLicenses - allowedLicenses
    println("Wrong Licenses: ${wrongLicenses.joinToString()}")

    if (wrongLicenses.isNotEmpty()) {
        error(
                message = "The project contains the following disallowed licenses ${wrongLicenses.joinToString()}.",
                howToFix = "Please use only the following allowed licenses: ${allowedLicenses.joinToString()}."
        )
    } else if (detectedLicenses.isEmpty()) {
        error(
                message = "The project does not contain any detected licenses.",
                howToFix = "Please ensure that licenses are properly detected and classified."
        )
    }
}

ruleSet(ortResult) {
    wrongLicenseInProject()
}
