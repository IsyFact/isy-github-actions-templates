val allowedLicenses=licenseClassifications.licensesByCategory["allow"].orEmpty()

funRuleSet.wrongLicenseInLicenseFileRule()=projectSourceRule("WRONG_LICENSE_IN_LICENSE_FILE_RULE"){
    println("XXXXXXXXXXXXXXXX  TEST  XXXXXXXXXXXXXX")
    println("AllowedLicenses:${allowedLicenses.joinToString()}")

    val detectedRootLicenses=licenseFindings.keys
    println("DetectedLicensesinLICENSEfile:${detectedRootLicenses.joinToString()}")

    val wrongLicenses=detectedRootLicenses-allowedLicenses
    println("WrongLicenses:${wrongLicenses.joinToString()}")

    if(wrongLicenses.isNotEmpty()){
        error(
                message="Thefile'LICENSE'containsthefollowingdisallowedlicenses${wrongLicenses.joinToString()}.",
                howToFix="Pleaseuseonlythefollowingallowedlicenses:${allowedLicenses.joinToString()}."
        )
    }else if(detectedRootLicenses.isEmpty()){
        error(
                message="Thefile'LICENSE'doesnotcontainanylicensewhichisnotallowed.",
                howToFix="Pleaseuseoneofthefollowingallowedlicenses:${allowedLicenses.joinToString()}."
        )
    }
}

ruleSet(ortResult){
    wrongLicenseInLicenseFileRule()
}
