[#--
Copyright (C) 2020 The ORT Project Authors (see <https://github.com/oss-review-toolkit/ort/blob/main/NOTICE>)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

SPDX-License-Identifier: Apache-2.0
License-Filename: LICENSE
--]

[#assign PurlUtils = statics['org.ossreviewtoolkit.model.utils.PurlExtensionsKt']]

:publisher: OSS Review Toolkit
[#assign now = .now]
:revdate: ${now?date?iso_local}

:title-page:
:sectnums:
:toc:

[#assign firstProjectId = projects?first.id]
[#assign firstProjectUrl = ortResult.getProject(firstProjectId).vcsProcessed.url]

= Vulnerability Report: Project ${firstProjectUrl}[${firstProjectId.name}], Version ${firstProjectId.version}

[#assign advisorResultsWithErrors = helper.advisorResultsWithIssues(AdvisorCapability.VULNERABILITIES, Severity.ERROR)]
[#if advisorResultsWithErrors?has_content]
== Warning
[.alert]
Errors were encountered while retrieving vulnerability information. Therefore, this report may be incomplete and
lack relevant vulnerabilities. Further details about the issues that occurred can be found in the
<<Packages with errors>> section.

<<<
[/#if]

== Packages
[#assign advisorResults = helper.advisorResultsWithVulnerabilities()]
[#list advisorResults as id, results]
=== ${PurlUtils.toPurl(id)}

[#list results as result]

*Advisor: ${result.advisor.name}*

[#list helper.filterForUnresolvedVulnerabilities(result.vulnerabilities) as vulnerability]

* ${vulnerability.id}
[#list vulnerability.references?filter(ref -> ref.scoringSystem?? && ref.score?? && ref.severity??) as reference]
** Source: link:++${reference.url}++[] +
   Severity: [.severity-${reference.severity?lower_case}]#${reference.severity}# (${reference.scoringSystem} score ${reference.score?string["0.0"]})
[/#list]

[/#list]

[#list result.summary.issues as issue]
* ${issue.severity}: ${issue.message}
[/#list]

[/#list]
[/#list]

[#if !advisorResults?has_content]
No packages with security vulnerabilities have been found.
[/#if]

[#if advisorResultsWithErrors?has_content]
<<<
== Packages with errors

When retrieving vulnerability information for these packages, the advisor module encountered errors. Therefore, it is
possible that existing security vulnerabilities are missing from the report. This section lists the issues that
occurred when requesting vulnerability information for the single packages.

[#list advisorResultsWithErrors as id, results]
=== ${id.name}

${PurlUtils.toPurl(id)}

[#list results as result]

[cols="1,5",options="header"]
|===
|Advisor|Message
[#list result.summary.issues as issue]
|${result.advisor.name}|${issue.message}
[/#list]
|===

[/#list]
[/#list]
[/#if]