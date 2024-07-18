val allowedLicenses = licenseClassifications.licensesByCategory["allow"].orEmpty()

fun RuleSet.wrongLicenseInLicenseFileRule() = projectSourceRule("WRONG_LICENSE_IN_LICENSE_FILE_RULE") {
    println("XXXXXXXXXXXXXXXX TEST XXXXXXXXXXXXXX")
    println("Allowed Licenses: ${allowedLicenses.joinToString()}")

    val detectedRootLicenses = licenseFindings.keys
    println("Detected Licenses in LICENSE file: ${detectedRootLicenses.joinToString()}")

    val wrongLicenses = detectedRootLicenses - allowedLicenses
    println("Wrong Licenses: ${wrongLicenses.joinToString()}")

    if (wrongLicenses.isNotEmpty()) {
        error(
                message = "The file 'LICENSE' contains the following disallowed licenses ${wrongLicenses.joinToString()}.",
                howToFix = "Please use only the following allowed licenses: ${allowedLicenses.joinToString()}."
        )
    } else if (detectedRootLicenses.isEmpty()) {
        error(
                message = "The file 'LICENSE' does not contain any license which is not allowed.",
                howToFix = "Please use one of the following allowed licenses: ${allowedLicenses.joinToString()}."
        )
    }
}

ruleSet(ortResult) {
    wrongLicenseInLicenseFileRule()
}
