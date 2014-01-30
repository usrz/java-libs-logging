USRZ Java Logging
=================

After a bit of frustration with the current state of logging in Java land,
this is a package that wraps and configures a number of logging sub-systems.

This includes:

* Apache Commons Logging 1.1
* Apache Log4j 1.2
* Apache Log4j 2.0
* JDK Logging (`java.utils.logging`, tested with JDK7)
* Logback 1.0
* SLF4J 1.7

To initialize logging simply call the `Logging.init()` method in the first
line of your `main(String[])` method, or add, at the top of your main class,
something like this:

```
static { Logging.init(); }
```

Our `Log` class can be used by instantiating it atop your class files, the
name of the logger will be automatically discovered by examining stack traces.

```
private static final Log = new Log();
```

Note that you are **NOT** allowed to use our `Log` class in **ANY** of your
project, as this does not want to create *yet another logging API* of any sort.

You're free to write your own taking inspiration from it, though.

License
-------

This is all licensed under the [Apache Software License version 2][LICENSE.md],
with the restriction of being unable to use the `Log` class in your code.
