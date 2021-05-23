# Casino & Bank
This project is for practicing usage of Layouts, Views, and Serializable.

User can go to the Casino, play dice rolling game, or go to the Bank for deposit or withdraw money.

> User's data is just a class called Finance, not connecting to any DB. 

## Main Activity
- Bank Name and Account Number are hard coded and can't be changed. But they are read and set into Finance class.
- By default, ***$$ in My Pocket*** and ***Account Balance*** don't have any amount shown behind, just the dollar sign($). 
The data shown was read from the Finance class.
- ***$$ in My Pocket*** and ***Account Balance*** will automatically update if user wins/loses money in Casino, or deposits/withdraws money in Bank.
- On launching the app, user is given $200 cash in wallet and $5000 balance in bank account by default.
- User can choose to go to Casino or Bank by clicking Buttons.
- ***Validation for entering Casino*** 
    - User will NOT be able to enter the Casino if the money in my pocket is less than **$50** since minimum bet is $50.

## Casino
- User is Player 1 (YOU)
- Each player is allowed to throw 3 dice at a roll
- Each game you bet **$50**
- Each player has 3 chances to roll the dice, on the last try, the last total score will be added up
to the total points of the player
- Player can choose **PASS** to skip the rest of the rolling chance for the current round
- The Player who reaches **100** total point wins the game
- If you win, $50 reward will be automatically added to your pocket
- If Player 2 wins, you lose $50 and it will automatically debit from the remaining cash in your 
pocket
- You can choose continue or not after each game
- If you choose continue, game table will be reset
- If there are **less than $50** in your pocket, you will be automatically kicked out from the casino

## Bank