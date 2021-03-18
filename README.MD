# StringUnpacker

### Description

The application takes a string of type `number[substring]` and outputs a string containing the substring that repeats
the specified number of times.

### How to use

Requires Java 11 or greater.  
To start application run command:  
`./gradlew run -q --console=plain`

Enter string in format: `number[substring]`, for example:  
`2[xyz]`  
Application returns:   
`xyzxyz`  
Each substring could contain another substring, for example:  
`2[xyz3[asd]]`  
Application returns:  
`xyzasdasdasdxyzasdasdasd`  
The application accepts only Latin letters, digits, and brackets.  
Enter command `exit` to terminate the application. 

