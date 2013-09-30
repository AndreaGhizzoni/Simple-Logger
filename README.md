# Simple Logger for java projects.
--------------
## How to use:

Here is the logger initialization:
```sh
Logger l = Logger.getInstance();
```
Now, where you need, you can call:
```sh
l.write( Tag.DEBUG, "xxxx" );
```
to write a DEBUG string on Standard out.

When you put your code in production, you can set this Logger to exclude DEBUG log:
```sh
l.disableTag( Tag.DEBUG );
```
and change the out stream on File (for example):
```sh
l.setPrintStream(new PrintStream(new File("path/to/file.log")));
```