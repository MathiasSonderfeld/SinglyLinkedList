# SinglyLinkedList
A Singly Linked List for Java projects.
Implements Java.util.List and uses Java Generics.
This implementation is not synchronized.

### Integration
I recommend using Maven for the integration.
This list is available on the Maven Central Repository shortly under groupId io.github.mathiassonderfeld and artifactid singlylinkedlist.
https://mvnrepository.com/artifact/io.github.mathiassonderfeld/singlylinkedlist

Alternatively you can use github as repositry or build a jar with maven and install it in your local repository.
Or you can download the jar and integrate it as you please.

### Usage
Use as any other List.

### Comparison vs. java.util.LinkedList
From my local benchmarks, this implementation outscales the LinkedList at over 2 million entries.
Your milage may vary.

---
*Because sometimes you want exactly that specific datastructure.*
