[#assign PurlUtils = statics['org.ossreviewtoolkit.model.utils.PurlExtensionsKt']]

:publisher: OSS Review Toolkit
[#assign now = .now]

:title-page:
:sectnums:
:toc:

== Summary
[#assign Unknown = 0]
[#assign Low = 0]
[#assign Medium = 0]
[#assign High = 0]
[#assign Critical = 0]
[#assign advisorResults = helper.advisorResultsWithVulnerabilities()]
[#list advisorResults as id, results]
[#list results as result]
[#assign vulnerabilities = helper.filterForUnresolvedVulnerabilities(result.vulnerabilities)]
[#assign Unknown = Unknown + vulnerabilities?filter(vuln -> vuln.references[0]?? && vuln.references[0].severityRating?? == "UNKNOWN")?size]
[#assign Low = Low + vulnerabilities?filter(vuln -> vuln.references[0]?? && vuln.references[0].severityRating?? == "LOW")?size]
[#assign Medium = Medium + vulnerabilities?filter(vuln -> vuln.references[0]?? && vuln.references[0].severityRating?? == "MEDIUM")?size]
[#assign High = High + vulnerabilities?filter(vuln -> vuln.references[0]?? && vuln.references[0].severityRating?? == "HIGH")?size]
[#assign Critical = Critical + vulnerabilities?filter(vuln -> vuln.references[0]?? && vuln.references[0].severityRating?? == "CRITICAL")?size]
[/#list]
[/#list]
[#assign Total = Unknown + Low + Medium + High + Critical]
* Critical: ${Critical}
* High: ${High}
* Medium: ${Medium}
* Low: ${Low}
* Unknown: ${Unknown}
* Total: ${Total}

== Packages
[#assign advisorResults = helper.advisorResultsWithVulnerabilities()]

[#list advisorResults as id, results]
=== ${PurlUtils.toPurl(id)}

[#list results as result]

*Advisor: ${result.advisor.name}*
[#assign total = 0]
[#list helper.filterForUnresolvedVulnerabilities(result.vulnerabilities) as vulnerability]

* ${vulnerability.id}
[#list vulnerability.references?filter(ref -> ref.scoringSystem?? && ref.severity??) as reference]
[#assign severityString = vulnerabilityReference.getSeverityString(reference.scoringSystem, reference.severity)]

** Source: ${reference.url} +
   Severity: [.severity-${severityString?lower_case}]#${reference.severity}# (${reference.scoringSystem})

[/#list]

[/#list]
[#list result.summary.issues as issue]
* ${issue.severity}: ${issue.message}
[/#list]

[/#list]
[/#list]