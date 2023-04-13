# Yirui's Project---Accounting Software

## Introduction

### What will the application do?

- The application is about an easy version of **accounting software**. It allows the users to keep track of their 
expenses and have a basic idea of their *monthly expenditure*. It will also support *various currencies* so that it is
possible for the users to spend different currencies and keep accounts.

### Who will use it?

- According to the feature, **international students** or **people who need to deal with transnational payments** are 
the primary audiences. Sometimes, if people get financial supports by families or by themselves, they do not want to 
exceed budgets, so the monthly summarizing graph will give a compact result of the expenditures, and they can reflect 
based on that.


### Why is this project of interest to me?

- As an international student, I always have a budget on the monthly and yearly expenses. I feel like spending money 
wisely, so I need some **statistical analysis** to help me understand my spending. Plus, for most of the time, I need to 
deal with money with *different exchange rates*. It could be the money that I use in my home country and also Canadian 
dollars. So I would like to have an application that is convenient to transfer the different exchange rates when I do
bookkeeping.

## User Stories 
- As a user, I want to be able to keep a new expense.
- As a user, I want to be able to keep many expenses.
- As a user, I want to be able to modify an existed expense.
- As a user, I want to be able to delete an existed expense.
- As a user, I want to be able to choose currencies freely.
- As a user, I want to be able to keep money in different accounts.
- As a user I want to be reminded to save the entire state of the application to file when I quit the program.
- As a user, I want to reload the expenses from file and resume exactly where I left off at some earlier time.

## Instructions for Grader
- You can generate the first required action related to adding Xs to a Y by pressing the ENTER key.
- You can generate the second required action related to removing Xs to a Y by clicking the button labelled "Remove".
- You can locate my visual component by clicking on the file menu.
- You can save the state of my application by clicking the "Save" button under the file menu.
- You can reload the state of my application by clicking the "Load" button under the file menu.

## Phase 4: Task 2
- An example of the printed logged events:
  Wed Apr 12 14:45:30 PDT 2023
  The description has been updated to: Tnt
  Wed Apr 12 14:45:30 PDT 2023
  The date has been updated to: 1.2
  Wed Apr 12 14:45:30 PDT 2023
  The currency has been updated to: cad
  Wed Apr 12 14:45:30 PDT 2023
  The amount has been updated to: 10.0
  Wed Apr 12 14:45:30 PDT 2023
  The bank account has been updated to: bmo
  Wed Apr 12 14:45:30 PDT 2023
  Added one expense.
  Wed Apr 12 14:45:48 PDT 2023
  The description has been updated to: Saveon
  Wed Apr 12 14:45:48 PDT 2023
  The description has been updated to: 1.2
  Wed Apr 12 14:45:48 PDT 2023
  The description has been updated to: cad
  Wed Apr 12 14:45:48 PDT 2023
  The description has been updated to: 10
  Wed Apr 12 14:45:48 PDT 2023
  The description has been updated to: bmo
  Wed Apr 12 14:45:51 PDT 2023
  The expense has been removed.

## Phase 4: Task 3
For the selectCurrency method inside AccountingApp, I can refactor the if statement by passing the str into
selection.equals() to avoid repetitiveness. Also in the AccountingApp, I may refactor the if statement of 
doConvertMoney() by passing a currency string, an exchange rate(double number) and also the currency unit and symbol.