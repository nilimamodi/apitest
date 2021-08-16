# apitest
Description :

This is a rest API to check the entered Creditcard number type and is it valid or not.

* URL to run the program - http://localhost:8004/Restful-app/rest/validateCreditcard?cardNumber=4111111111111

Steps to run the program:

1) run the above URL and pass your card number as carNumber="<yourcardnumber>"
for example: http://localhost:8004/Restful-app/rest/validateCreditcard?cardNumber=4111111111111

Result : 
Visa :4111111111111 (invalid)

Test Cases:
- checks the string length
- check the card type.
Card Type   Begins With Number Length
AMEX 	    34 or 37            15
Discover    6011 		16
MasterCard  51-55 		16
Visa        4 			13 or 16


The steps for validating using the Luhn algorithm are:
1. Starting with the next to last digit and continuing with every other digit going back to the
beginning of the card, double the digit.
2. Sum all doubled and untouched digits in the number. For digits greater than 9 you will need to
split them and sum them independently (i.e. "10", 1 + 0).
3. If that total is a multiple of 10, the number is valid else invalid.