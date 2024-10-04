# v1.6.0
## Features
- `IFS-2857`:
    - Einführung von Inputs für `log_level` und `failure_level` um den Log-Schweregrad und -Fehlerschweregrad für Antora-Dokumentations-Builds zu steuern
    - Implementierung von Schritten zum Auslösen eines Dokumentations-Builds im Repository `isyfact.github.io` und Warten auf dessen Fertigstellung.
    - Verbesserte Log-Verarbeitung durch Abrufen, Bereinigen und Formatieren von Build-Logs in saubere Logs und eine Markdown-Datei
    - Automatische Kommentierung der verarbeiteten Logs bei Pull-Requests zur besseren Sichtbarkeit
    - Sicherstellung, dass der Workflow fehlschlägt, wenn der Antora-Build auf Fehler stößt, und verbessert die Fehlererkennung und -behandlung
- `IFS-2853`:
  - Verbesserung des Dependency-Scan-Templates mit dependencyManagement-Unterstützung:
    - Hinzufügen eines neuen Inputs `scan-dependency-management`, um das Scannen von Dependencies zu steuern.
    - Implementierung eines Schrittes zur Änderung der POM-Datei von `isyfact-products-bom` für das Scannen von Dependencies in dependencyManagement.
- `IFS-2079`:
  - Hinzufügen von Inputs zum Ausschluss von Lizenzen beim Dependency Review Template

## Fixes
- `IFS-3729`: Hinzufügen der Standard-Deploy-Url für Deploy Maven Template
- `IFS-3938`: Behebung eines Leerzeichenfehlers bei der Überprüfung des Vorhandenseins von Release
- `IFS-3611`: Behebung eines Syntaxfehlers im PR-Agent 

## Chore
- Codium-ai/pr-agent von 0.23 auf 0.24
- codex-/await-remote-run von 1.11.0 auf 1.12.2
- codex-/return-dispatch von 1.12.0 auf 1.16.0

# v1.5.0
- `IFS-3956`: Anpassung des Workflows `oss_review_toolkit_template.yml` zur Veröffentlichung der Reports
- `IFS-3938`: 
  - Behebung eines Syntaxfehlers im Schritt `Check release existence` in `maven_deploy_template.yml`. 
  - Anpassung von default `deploy-server-url` in `maven_deploy_template.yml` an Maven Central

# v1.4.0
- `IFS-3611`: 
  - Hinzufügen des Workflow-Templates `pr_agent_template.yml` zur Automatisierung von PR-Analyse mit dem CodiumAI PR-Agent
  - Hinzufügen der PR-Agent `pr_agent.yml` und Commit Message Checker `commit_message_checker.yml` Workflows
- `IFS-3731`: Hinzufügen der Workflow-Templates:
  - `docs_build_template.yml`: Auslösen der Dokumentation-Build im zentralen Antora Playbook
  - `update_antora_version_template.yml`: Automatisches Aktualisieren der Antora-Version in `antora.yml`

# v1.3.0
- `IFS-3727`: Überprüfung von Snapshot als auch RC-Versionen, Überprüfung von Release-Existenz für Tags in `maven_deploy_template.yml`

# v1.2.0
- `IFS-3767`: Hinzufügen des `OSS Review Toolkit` als Template

# v1.1.0 
- `IFS-3729`: Hinzufügen von `severity-threshold` und `perform-scan` als Inputs zu `maven_dependency_scan_template.yml`
- `IFS-3826`: Hinzufügen von `environment` zur Nutzung von GitHub Environments mit `maven_deploy_template.yml`

# v1.0.0
- `IFS-2835`: Aktualisierung der `dependency_review_template.yml` zur Überprüfung der Lizenzgültigkeit
- `IFS-3842`: Initiale Bereitstellung der Templates
