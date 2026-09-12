## How to compile
 
From inside the `HeapSortProject` folder:
 
```
javac -d bin src/*.java
```
 
This compiles all source files into a `bin/` directory.
 
## How to run
 
Standard run (recommended — see note below):
 
```
java -cp bin Main
```
 
Run **all four** algorithms on **all three** dataset sizes, including the
1,000,000-element dataset for Bubble/Selection/Insertion Sort:
 
```
java -cp bin Main full
```