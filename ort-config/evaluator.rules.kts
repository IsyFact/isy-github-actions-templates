val allowedLicenses = licenseClassifications.licensesByCategory["allow"].orEmpty()

fun RuleSet.wrongLicenseInLicenseFileRule() = projectSourceRule("WRONG_LICENSE_IN_LICENSE_FILE_RULE") {
    require {
        +projectSourceHasFile("LICENSE")
    }

    val detectedRootLicenses = projectSourceGetDetectedLicensesByFilePath("LICENSE").values.flatten().toSet()
    val wrongLicenses = detectedRootLicenses - allowedLicenses

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