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
- Validation for entering Casino: user can NOT enter Casino if the money in your pocket is less than $50 since minimum bet is $50.

## Casino

## Bank