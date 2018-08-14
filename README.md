



# Crosswords generation as CSP
This project was apart of AI course at Birzeit University. 

The main focus of the project was on the generation part of the puzzle, the generation was implemented using Constraints Satisfaction Problems (CSPs) search technique, which started from finding appropriate variables and values (domains), then finding the best data structures to satisfy the problems specification in terms of both space and time, our aim was not just to create a generator to fill the grid, but a reasoning UI that is easy to use for normal users and for those whom in real need of it like the newspapers editors, they can choose the topic(s) and the file they want, then they can choose the generation style (American, British, random or just manually), then if there’s a possible words combination that can fit the constraints a new UI will pop-up with hints and a numbering link to the grid so they can print/publish it.

## Approach
Our approach focused on how to treat the space and the time well, because of this, every step must be accurately measured and the decision of choosing the variable or the value must be done using heuristics. 
### Choosing the variable
 We used a sorted list, that is sorted based on two heuristics, the main heuristic is the most constraining variable heuristic, the second one will be used when the two or more variables have the same most constraint variable number, which was the minimum remaining value heuristic, after choosing the variable, if there’s a value that applies both the forward checking and the arc consistency, the heuristics of the variable will be set to ZERO so it won’t be used again in the sorted set. If there was no appreciate value a backtracking will occur. 
 ### Choosing the value 
 After choosing the variable, the next stage will be the value picking, we want to choose a value that maximize others neighbors values. Not just maximize them in total but also no zero neighbor values. This is done simply by knowing how each letter frequency in each position using the array list in the value which we are linking to, and that’s our heuristic for the value. If there’s no such a value a feedback will be sent to the DFS loop so that a backtracking will occur. 
 ### Forward Checking 
 The forward checking will happen after each successful assignment (the assignment that maximize neighbor’s options with no zero options for any one), in forward checking we will iterate over all the neighbors and add the constraints that came from assigning the value. Any neighbor that its values became zero will just be enough indicator to choose other value to assign. The other value will be the right next value that satisfy the choosing the value condition. If there’s no other value, then a backtracking will occur. 
 ### Arc Consistency 
 After the forward checking some of the neighbors values are eliminated, when so, the arc consistency stage will start. In arc consistency we will traverse all the neighbors, and for each one we will take the intersection cell of them and their neighbors, after knowing the intersection cell and its location for both the neighbor and the neighbor’s neighbors we will add a new type of constraints called the arc consistency constraints, each variable will have this type of constraints, and what it does is basically ORing the available values from the normal constraints with the arc consistency values, so after the intersection phase we had for example {“cat”, “car”, “bar”, “cur”} and the arc consistency list says that the character at position 0 must be ‘c’ and the character at position 2 must be ‘r’, the new available words will be {“car”, “cur”} if the new available values were a null set then we have to choose other value, the arc consistency technique is done for both the starting point and the ending point, that is for the variable neighbor and for the variable neighbor’s neighbor.
 ### Depth First Search (DFS) 
 The depth first search is done just like any DFS approach, we started from pushing an state containing all the variables unassigned, then in the loop body of the DFS we will choose a variable, and then a value, if some constraints does not satisfy while choosing the value then a backtrack will occur, when the stack is empty then there’s no such word combinations that can fit the grid, otherwise the solution will be found during the DFS. 

Choosing the variables and the values is all done by the heuristics, the forward checking and the arc consistency just like discussed above. 

#### All of this will be displayed in the UI, from the grid to all its components to the list of words. a log will be displayed to the user telling him what happend each iteration in the DFS, and finally a summary window button will appear that displays all the main events that happened during filling the grid, like the number of iterations, the number of backtracks, the time spent and the space used.

## Features
 - The matrix can be of any size ( n*m ).
 - The user can choose to pick a enter a size or just pick a famous predefined grid.
 - The user can build the grid  (fill it with black squares) with American or British styles or just random. 
 - The solution generation can be done using 3 different categories (Topics) or mix them, the topics are:
	 1. Health
	 2. Arts
	 3. Technologies 
 - The user can process the generation step by step, with animation (with adjustable speed) or simply skipping to the final solution.
 - If there's a solution, the user can view a results window which shows the across/down hints and their linked numbers to the gird, so he/she can publish it as a puzzle under the chosen topic(s).
 - If the user is interested in the performance aspects he/she can view the summary window which informs:
	 1. Number of backtracks
	 2. Number of iterations
	 3. Time taken
	 4. Space used
 
## Development
Many steps can be implemented to improve the project, but all of them need to be well studied and understood, there are tons of optimization and solving techniques like the **phase transition phenomena**, which was first discussed in the fashion of crosswords puzzle in Anbulagan and Adi Botea paper. Their paper has the best results so far in the crosswords puzzle as a CSP approach.


*More details about the project like the challenges we faced and the data structures used can be found in our paper in this repo.*

## Screenshots
### Initial screen
![Alt text](/Screenshots/initialScreen.PNG?raw=true "Initial Screen")
### The screen before specifying words/file
![Alt text](/Screenshots/beforeAddingTheWords.PNG?raw=true "The screen before specifying words/file")
### The screen after adding a words file
![Alt text](/Screenshots/afterUploadingAFile.PNG?raw=true "The screen after adding a words file")
### Animation mode
![Alt text](/Screenshots/whileAnimating.PNG?raw=true "Animation mode")
### Final results
![Alt text](/Screenshots/finalResult.PNG?raw=true "Final results")
### The results for newspapers publishing
![Alt text](/Screenshots/theResultsForTheNewspaperSubmission.PNG?raw=true "The results for newspapers publishing")
### Performance summary
![Alt text](/Screenshots/summary.PNG?raw=true "Performance summary")




## Authors
- Ahmad Yahya. ( @AhmadYahya97 )
- Maha Hajja.
