bpwj
====

Simple Java Parser Development Framework created by [Steven John Metsker](http://c2.com/cgi/wiki?SteveMetsker) for his book [Building Parsers With Java](http://www.amazon.com/Building-Parsers-Java%C2%BF-Steven-Metsker/dp/0201719622).

I first came across this framework when I read the book back in the early 2000s. At the time I had built parsers for some domain specific languages (DSL) in C ([lex and yacc](http://dinosaur.compilertools.net/lex/)) and was working on a parser for another DSL using [JavaCC](https://javacc.java.net/). In comparison, I found this framework much simpler and intuitive, so I ended up using it instead. I recently needed to build a rule-based parser for some Natural Language Processing (NLP) work I am doing, so I looked for this code on the Internet but couldn't find it.

The code for the framework is available on the CDROM accompanying the book. Because these were the early days of Java, the code is packaged using [InstallAnywhere](http://www.flexerasoftware.com/producer/products/software-installation/installanywhere/), probably the state of the art in Java packaging at the time. I decided to convert it to a Maven project and put it on GitHub so this wonderful piece of software becomes more broadly accessible.

I believe I am being true to the wishes of the author based on the readme.txt accompanying the code on the CDROM.

> Copyright
> ---------
> The code on the CD is free. It is copyrighted, so you may not claim that 
> you wrote the code. Otherwise you may use the code as you wish.
>

##Building

The code has just been copied into a Maven structure. It is currently set to use Java 1.7 but the code was originally written against a much older version, so older versions should work as well. The only dependency is on Xerces. To download this code and build a JAR that you can reference from your own application, execute the following sequence of commands:

    git clone https://github.com/sujitpal/bpwj.git
    cd bpwj
    mvn package

This will generate bpwj-1.0-SNAPSHOT.jar in the target subdirectory.

##Usage

There are lots of examples of how to use this code under [the examples directory](https://github.com/sujitpal/bpwj/src/main/java/sjm/examples). The simplest one to get started with in my opinion is the [Arithmetic Parser](https://github.com/sujitpal/bpwj/src/main/java/sjm/examples/arithmetic/).

