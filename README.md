# RuleEngine

[![TravisCI](https://travis-ci.org/mcgivrer/RuleEngine.svg?branch=develop)](https://travis-ci.org/mcgivrer/RuleEngine "open the TravisCI compilation trend")
[![buddy pipeline](https://app.buddy.works/mcgivrer/RuleEngine/pipelines/pipeline/148927/badge.svg?token=${project.buddy.token} "buddy pipeline")](https://app.buddy.works/mcgivrer/RuleEngine/pipelines/pipeline/${project.buddy.pipelineId})
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fmcgivrer%2FRuleEngine.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2Fmcgivrer%2FRuleEngine?ref=badge_shield)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/${project.codacity.token})](https://www.codacy.com/project/mcgivrer/RuleEngine/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mcgivrer/RuleEngine&amp;utm_campaign=Badge_Grade_Dashboard)
[![Github Releases](https://img.shields.io/github/release/mcgivrer/RuleEngine.svg)](https://github.com/mcgivrer/RuleEngine/releases/tag/${project.version})

## Description

This project intends to provide the framework of a RuleEngine with Rule to be processed through a thread pool to distribute tasks.

## Compile

To compile the full project, please execute the following command :

```bash
$> mvn clean install
```

## Execute

to execute the the compiled jar, please execute the command bellow :

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

[MIT](./LICENSE "Read the MIT license")
