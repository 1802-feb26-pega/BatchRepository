#Banking App - First Iteration

#### Notes for Genesis:
When testing the project, the first time that you run the project you must indicate that you do not already have an account. I did not know if you would have preferred that I include premade accounts that I used during my testing so I did not include them. But once you have run through the project once and created a new "account," you will be able to login to the same account you created. (Data Persistence is cool). Thank you!!

## Flow of Code
### Driver
Progression through the app is controlled primary by the Driver, CreateAccount, and ChooseAction classes.
- The Driver class is the class through which the application begins execution
    - Within the main function in the Driver class, there is only a call to create a Driver object and a try and catch clause. 
    - Once the Driver object is created, the user is prompted with a series of question.
        - First we determine if the user already has an account or if they are a new user.
            - If they are a new user, they will be led to the create an account process in a generated CreateAccount object.
            - If they are a returning user, they will be prompted to login using their username and password
- The CreateAccount class asks the user to input certain information so that a new account can be created. A new account in this implementation of the banking app is simply a User object.
    - Once the account has been created, the user is taken to the choose action menu via the ChooseAction object.
- The ChooseAction class allows the user to use 4 functions:
    - deposit
    - withdraw
    - view balance
    - logout

### User Object
A User object is essentially a stand in for an account in this implementation and iteration of Banking App. After the user goes through the CreateAccount process, a new User object is created and a file name is generated for serialization purposes. Because usernames are unique in this project, the file name is "username.txt". When the user chooses to "logout" when on the "ChooseAction" menu, the User object that was being accessed and manipulated is serialized to the filename that is created for this instance of User. The process is similar for returning users. 
When a user indicates that they already have an account they are prompted to give their username. If there are no User objects in the directory for serialized User objects (src/users), then the user will be asked to create a new account. If there are User objects already there, the user's username will be checked against each now deserialized User object and if there is a match, the password will be checked as well.
Each time logout is chosen from the "ChooseAction" menu, the User object that is being accessed is serialized.

## Notes
There are a few areas that I believe could be improved to make for a better application.
1. I primarily used objects to control the flow of the application, but I believe that I could've made use of access modifiers to make methods accessible to other classes without needing to instantiate an entire object (static).
2. The constructor for the CreateAccount class could be overloaded so that if the user attempted to login but no User with that username existed, we could skip recollecting a username and remember the value that was already input.
3. Some of my methods could be further modularized in order to make the code more readable and easily edited. 
4. I need to improve my commenting

So, my goals for the second iteration are:
1. Have each method only have ONE function.
2. Refrain from using objections to control the flow of my application where possible
3. Write better comments for each method
4. Make use of overloading and overriding where applicable.
