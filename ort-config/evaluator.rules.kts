import com.here.ort.model.LicenseFinding
import com.here.ort.model.PackageCurationResult
import com.here.ort.model.config.RuleSet
import com.here.ort.model.config.createRuleSet
import com.here.ort.reporter.reporters.evaluator.EvaluatorRuleViolation

// Erstelle eine Regelmenge
val ruleSet = createRuleSet("ALL_LICENSES_RULES")

fun RuleSet.collectAllLicensesRule() = ruleSet {

    // Erstelle eine Menge, um alle gefundenen Lizenzen zu speichern
    val allFoundLicenses = mutableSetOf<String>()

    // Sammle Lizenzen aus allen Projekten
    licenseFindings.values.flatten().forEach { licenseFinding ->
        allFoundLicenses.add(licenseFinding.license.toString())
    }

    // Sammle Lizenzen aus allen Paketen
    project.collectPackages().forEach { pkg ->
        pkg.licenseFindings.forEach { licenseFinding ->
            allFoundLicenses.add(licenseFinding.license.toString())
        }
    }

    // Optionale: Gib alle gefundenen Lizenzen aus (nur zu Debugging-Zwecken)
    println("Alle gefundenen Lizenzen: ${allFoundLicenses.joinToString(", ")}")

    // Optional: Füge eine Regelverletzung hinzu, um alle gefundenen Lizenzen zu dokumentieren
    issue(
            message = "Die folgenden Lizenzen wurden gefunden: ${allFoundLicenses.joinToString(", ")}",
            howToFix = "Überprüfen Sie die gefundenen Lizenzen und stellen Sie sicher, dass sie den Lizenzrichtlinien entsprechen."
    )
}

// Regelmenge zur Evaluierung hinzufügen
ruleSet.collectAllLicensesRule()

//val allowedLicenses = licenseClassifications.licensesByCategory["allow"].orEmpty()
//
//fun RuleSet.wrongLicenseInLicenseFileRule() = projectSourceRule("WRONG_LICENSE_IN_LICENSE_FILE_RULE") {
//    println("XXXXXXXXXXXXXXXX TEST XXXXXXXXXXXXXX")
//    println("Allowed Licenses: ${allowedLicenses.joinToString()}")
//
//    val detectedRootLicenses = licenseFindings.map { it.license }.toSet()
//    println("Detected Licenses in LICENSE file: ${detectedRootLicenses.joinToString()}")
//
//    val wrongLicenses = detectedRootLicenses - allowedLicenses
//    println("Wrong Licenses: ${wrongLicenses.joinToString()}")
//
//    if (wrongLicenses.isNotEmpty()) {
//        error(
//                message = "The file 'LICENSE' contains the following disallowed licenses ${wrongLicenses.joinToString()}.",
//                howToFix = "Please use only the following allowed licenses: ${allowedLicenses.joinToString()}."
//        )
//    } else if (detectedRootLicenses.isEmpty()) {
//        error(
//                message = "The file 'LICENSE' does not contain any license which is not allowed.",
//                howToFix = "Please use one of the following allowed licenses: ${allowedLicenses.joinToString()}."
//        )
//    }
//}
//
//ruleSet(ortResult) {
//    wrongLicenseInLicenseFileRule()
//}
