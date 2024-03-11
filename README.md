# 🎄 Advent of Code {year}
Solutions for [Advent of Code](https://adventofcode.com/) in Java ☕.
## Template setup
### Configure repository
1. Open [the template repository](https://github.com/thermoweb/aoc-java-template) on Github
2. Click [User this template](https://github.com/thermoweb/aoc-java-template/generate) and create your repository
3. Clone your repository on your computer

### Requirements
- Java 17+ installed

## Usage
### Before starting
First you have to build the project
```shell
./mvnw clean install
```

There is two ways to run `aoc`:

- using maven custom goal
- using command line tool (java [picocli](https://picocli.info/) application)

> [!TIP]
> In order to be able to use shortened maven goal, add the maven plugin group in your `~/.m2/settings.xml`:
> ```xml
> <settings>
>   <pluginGroups>
>     <pluginGroup>org.thermoweb</pluginGroup>
>   </pluginGroups>
> </settings>
> ```
> If this is not configured you'll have to user `org.thermoweb:aoc-maven-plugin:<goal>` instead of `aoc:<goal>` in the command line.

### Scaffold a day
This will create both day class and the associated test.
#### with aoc cli
```shell
# example: `./aoc scaffold --day 1`
./aoc scaffold --day <day>
```

#### with maven goal
```shell
# example: `./mvnw aoc:scaffold -Dday=1`
./mvnw aoc:scaffold -Dday=<day>
```
or if you did not added the pluginGroup in your maven settings:
```shell
# example: `./mvnw org.thermoweb:aoc-maven-plugin:scaffold -Dday=1`
./mvnw org.thermoweb:aoc-maven-plugin:scaffold -Dday=<day>
```


### Download Input for a day

> [!IMPORTANT]
> This requires in your home folder the file `.adventofcode.session` with your session-cookie in it.
> To find it, you have to log in on [adventofcode.com](https://adventofcode.com) and use the web developer tool to retrieve the value of the `session` cookie.

```shell
# example: `./aoc download --day 1`
./aoc download --day <day>
```

this will create the input file and download corresponding data of day and an empty example file.

### Solve a day
```shell
# example: `./aoc solve --day 1`
./aoc solve --day <day>
```
This will launch the day solver for the specified day with the input. 

---

Inspired by [advent-of-code-rust](https://github.com/fspoettel/advent-of-code-rust) template.