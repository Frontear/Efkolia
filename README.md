# Efkolia [![CodeFactor](https://www.codefactor.io/repository/github/frontear/efkolia/badge)](https://www.codefactor.io/repository/github/frontear/efkolia) [![Efkolia CI](https://github.com/Frontear/Efkolia/workflows/Efkolia%20CI/badge.svg)](https://github.com/Frontear/Efkolia/actions?query=workflow%3A%22Efkolia+CI%22) [![Maven Central](https://img.shields.io/maven-central/v/com.github.frontear/Efkolia.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.frontear%22%20AND%20a:%22Efkolia%22)

My minecraft modding framework, contains all the necessities that I deem important when creating a mod for minecraft. It's not intended for public use, but you may use it as you see fit, following the terms of the license.

## Getting Started

- Clone this repository via `git clone https://github.com/Frontear/Efkolia.git Efkolia`
- Import the project into [IntelliJ IDEA](https://jetbrains.com/idea)
- Create a *gradle.properties* (or update the one at `~/.gradle/gradle.properties`) with your `nexusUsername`, `nexusPassword` (nexus usertokens), `signing.keyId` (last 8 characters of key), and `signing.password`. Do NOT share this file publicly.

## Usage

The project can be imported from maven central, so you can simply import it to your respective platform. Please see the **maven central** badge for more information.

```gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.frontear:Efkolia:+") // latest version
}
```

## License

This project falls under the [GNU General Public License v3](https://tldrlegal.com/license/gnu-general-public-license-v3-(gpl-3)) &#8212; you may copy, distribute and modify the software as long as you track changes/dates in source files.
