# Elevator Simulator

## Building and Executing

This Java project is built with [Apache Maven](https://maven.apache.org/).
Additionally, a Makefile is also provided for convenience. Before building this
project, please ensure Maven is installed on the system. Optionally,
[GNU Make](https://www.gnu.org/software/make/) can be helpful to simplify the
Maven and JRE commands.

To build the program, use Maven CLI:

```shell
$ mvn clean package
```

or

```shell
$ make
```

This will build the project to a `jar` file located in the `target` directory of
the project root.

For simple execution without any command line arguments, run

```shell
$ java -cp target/elevator-sim-1.0-SNAPSHOT.jar io.yiyuzhou.app.App
```

or

```shell
$ make run
```

## Usage

There are two command line arguments allowed:
  - A path to the property file.
  - A verbose flag for additional debug information.

For example, assuming you are on a standard GNU/Linux distribution. Your user
name is `foo`, and there exists a property file called `bar` at location
`/home/foo/Documents/bar`. You can run the program with that file path as one of
the arguments:

```shell
$ java -cp target/elevator-sim-1.0-SNAPSHOT.jar io.yiyuzhou.app.App /home/foo/Documents/bar
```

Please note that the UNIX style "shortcut" `~`, denoting the home directory will
not work. This is due to a limitation of Java's `File` object implementation.

Likewise, the verbose flag would help you to debug by outputting some useful
information to the shell.

```shell
$ java -cp target/elevator-sim-1.0-SNAPSHOT.jar io.yiyuzhou.app.App -v
```

or

```shell
$ make verbose
```
