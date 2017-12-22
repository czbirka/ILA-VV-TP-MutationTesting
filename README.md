# ILA-VV-TP-MutationTesting

## Mutation Testing

## SOUKPA Adou & ZBIRKA Christophe


### How to start?

Compile the project and use the file MutationTestingApp :    

/src/main/java/m2/ila/fr/istic/ila/vv/MutationTestingApp.java

### Configuration
We use the file application.properties to configurate the mutations we want to do.  
We have to define:  
* **paths** : different paths to the maven project we want to implement mutations.  
* **mutators** : mutators we want to use. They need to be separate by a comma.  
  
  
### Mutations
Four types of mutations are implemented:  
* Remove all the instructions in the body of a *void* method.  

* Replace all the instructions in the boby of a *boolean* method by `return true;` and `return false;`.  

* Replace arithmetic operator by other one:  

| operator | replaced by |
|-------------------|-------------|
| +          |      -      |
|        -          |      +      |
|        *          |      /      |
|        /          |      *      |
   

* Replace comparison operator by other one:   

| operator | replaced by |
|-------------------|-------------|
|        <          |      <=      |
|        <=          |      <      |
|        >          |      >=      |
|        >=          |      >      |
|        ==          |      !=      |
|        !=          |      ==      |
   
### Report   
After all the mutations and all the tests, a report is generate at each time. It is save in the folder of the project. The nama of this report contains the date and the time:   
example : *bilan mutations 2017_12_22 19:42:01*
