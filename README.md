# :banana: Overview
This is a *sample microservice* application built with [Spring Boot](http://projects.spring.io/spring-boot/) and [Docker](https://www.docker.com/), deployed on Amazon ECS, to manage bananas :banana:

This service demonstrates deployment with [mu](http://getmu.io)

Review the steps below to learn how it was created and how to create your own:

# :banana: Step 1: Set up your environment

You will need to install the [Spring Boot CLI](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started-installing-the-cli) to get started.  The recommended way is to use [SDKMAN!](http://sdkman.io/index.html).  First install SDKMAN! with:

```
$ curl -s "https://get.sdkman.io" | bash
```

Then, install Spring Boot with:

```
$ sdk install springboot
```

Alternatively, you could install with [Homebrew](http://brew.sh/):

```
$ brew tap pivotal/tap
$ brew install springboot
```

Or [MacPorts](http://www.macports.org/):

```
$ sudo port install spring-boot-cli
```

# :banana: Step 2: Scaffold out your microservice project

Use the Spring Boot CLI to create a project:

```
$ spring init --build=gradle --package-name=com.stelligent --dependencies=web,actuator,hateoas -n Banana banana-service
```

# :banana: Step 3: Create a REST domain, repository, resource and controller

write some code...


# :banana: Step 4: Run it locally

Start the app by running:

```
$ gradle bootRun
``` 

Try it out:

```
$ curl http://localhost:8080/bananas
```


