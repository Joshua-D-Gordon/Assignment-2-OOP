### Assignment-2-OOP

## ***By Asaf Shmaryahu & Johsua Gordon***
### ***Part 1***
### **Table Of Content's**
| No. | Content    | Description                                          |
|-----|------------|------------------------------------------------------|
| 0   | Overview   | an overview explaining about Part 1                  |
| 1   | Function 2 | About our implication of the function getNumOfLines  |
| 2   | Function 3 | About our implication of the function getNumOfLines  |
| 3   | Function 4 | About our implication of the function getNumOfLines  |
 

0. **About Part 1** -
   In Part 1 we are required to genarate a number of files with a random number of written lines and culcualte the amount of lines using three diffrent funcions. the first a normal funcion the second a function using a thread and the third a function using a thread pool. We created a function that randomly genarates a random number of lines for each files, for each line we randomly genarated a seqence from the Ascci values of charaters to be written on to the line of each file.
1. **Function 2** -
    Function 3 recieves a String array of file names and initilizes a sum to 0. it then trys to open each file and count the number of lines and adds it to the total sum. it then returns the total sum.

2. **Function 3** -
   Function 3 recieves a String array of file names and initilizes a sum to 0. it then implements a mythread class that extends thread and culculates the single file number of lines. once the thread is not alive it adds the value to the total sum.
   ***note this is no faster than the previose function as the priviose function used one thread main and looped over, here as well we used one thread at a time and then added to the total sum***

3. **Function 4** - Function 4 recieves a String array of file names and initilizes a sum to 0. it then trys to create a new threadpool the size of the amount of files, then after submit to the threadpool a new callable variable called callfunction that computes the number of lines in the file. the total sum is addintioned to the future get result of that thread.once finished the function returns the total number of lines returned.
    ***note this function is much faster than the previouse functions as it uses a thread pool an dose async computations on the threads***

***a Class Diagram will be attached in the folder***

---
### ***Part 2***
### **Table Of Content's**
| No. | Content                                            | Description                                         |
|-----|----------------------------------------------------|-----------------------------------------------------|
| 0   | Overview                                           | an overview explaining about Part 2                 |
| 1   | Considerations for further use                     | About our consideration for further use             |
| 2   | Design patterns employed                           | About our implication of Design Patterns employed   |
| 3   | Flexibility, performance , maintainability of code | About our Design Patterns implication contributions |

0. **About Part 2** -
   In Part 2 we are required to  create two new tǇpes that eǆtend the functionality of Java's Concurrency Framework:

0.1 - A generic task with a Type that returns a result and may throw an exception. Each task has a priority used for scheduling͕ inferred from the integer value of the task͛s Type.

0.2 - A custom thread pool class that defines a method for submitting a generic task as described in the section 1 to a priority queue, and a method for submitting a generic task created by a Callable<V> and a Type, passed as arguments.

1. **considerations for further use** -
individuls might inherit from our class's if they two feel they are limited by the standard java threadpool, executor ect...
inindividuls might use our classes and use thier own comparator function and override our cmp. they also might use a hashmap to automaticlly cast future returned instances to the correct form and not just a generic object.

2. **Design patterns employed** -
We used multiple Design patterns such as factory, adapter. for exampe we made our classes very generic thus an individual can use it not only for ints' doubles ect.. but for any type (not null) class and eaisaly cast the future returned object to his/her prefrence.
we aslo used factory for overrinding factor threads to be able to insert a non default priority.


3. **flexibility, performance , maintainability of code** -

because the factory thread is open for desgin and we made the class's generic and adaptable, individuls can overide and use thier own implication of factorthread and use casting for return values of futures hence this is not bounded for a one case use.

***A variety of difficulties arose during this assignment, however we were able to overcome them while learning along the way with trail and error, One difficulty we encontered while running tests was the fact that we needed to cast the returned value to a string or int we then though of making multiple futures that return diffrent types. instead we decided to return a genral object that would have to be casted but on the upside our one futute can return everything and is not bounded to one type***

***a Class Diagram will be attached in the folder***

---