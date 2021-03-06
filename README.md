# RuleEngine

[![TravisCI](https://travis-ci.org/mcgivrer/RuleEngine.svg?branch=develop)](https://travis-ci.org/mcgivrer/RuleEngine "open the TravisCI compilation trend")
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fmcgivrer%2FRuleEngine.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2Fmcgivrer%2FRuleEngine?ref=badge_shield)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/cd3178efe577482d99439e943e88a0f6)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mcgivrer/RuleEngine&amp;utm_campaign=Badge_Grade)
[![Github Releases](https://img.shields.io/github/release/mcgivrer/RuleEngine.svg)](https://github.com/mcgivrer/RuleEngine/releases/tag/${project.version})

## Description

This project intends to provide the framework of a RuleEngine with Rule to be processed through a thread pool to distribute tasks.

## Compile

To compile the full project, please execute the following command :

```bash
$> mvn clean install
```

## Execute

to execute the compiled jar, please execute the command bellow :

```bash
$> mvn exec:java
```

or :

```bash
$> java -jar ruleengine-1.0-SNAPSHOT.jar
```

After full build, on the windows platform, you can execute the `target/${project.name}.exe` executable file.

```bash
c:\> target\ruleengine.exe
```

## Edit

Import this project as an Existing Maven Project into your preferred IDE, 
(like [Eclipse](http://www.eclipse.org/downloads "open the eclipse official web download page") ?)

## License

- [MIT](./MIT "go and read the license")
