## How to compile
```
javac -d bin src/*.java
```
This compiles all source files into a `bin/` directory.

## How to run
```
java -cp bin Main
```
if that doesn't work, try this
```
java -cp bin src.Main
```
we don't run n=1000000 on bubble, selection, insertion sort because it's too big, add full flag to include it