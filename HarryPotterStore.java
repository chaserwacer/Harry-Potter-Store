/*
Chase Balmer
9/30/19
This is my own work, CJB
Project_Assignment1, Balmerc
This is a program that runs an online store for Defense Against the Dark Arts
*/

import java.util.Scanner;

public class HarryPotterStore
{
    public static void main(String[]args)
    {
        //Variables
        //-----------
        Scanner reader = new Scanner(System.in);
        String user = "";
        final String COUPON = "PittsburghPenguinsAreTheBest";
        int coupCheck = 0;
        int decision = 0;
        int grossMoneyHourly = 0;
        int grossMoneySpell = 0;
        double grossOverall = 0;
        boolean loop = true;
        int quickLeave = 0;
        //-----------

        //Loop that will reopen store after a customer has checked out
        while(loop)
        {

                //"Open" Store and get users name(If statement used to close program)
                System.out.println("Greetings! Welcome to DADA Tutoring!");
                System.out.println("What is your name?(Enter empty string to close program)");
                user = reader.nextLine();

                if(user.equalsIgnoreCase(""))
                {
                    System.out.println("Program Terminating");
                    loop = false;
                    System.exit(1);
                }
                else
                    System.out.println("Well hello there " + user + "! I am happy to do buisness with you!");


                //Use password method to decide if discount should be applied
                coupCheck = coupon(COUPON, reader);


                //If user would like to close the store and not buy anything, they have the option
                System.out.println("If you would like to leave the shop and not purchase anything, enter -1, if you would like to continue, input any other int value.");
                quickLeave = reader.nextInt();

                if(quickLeave == -1)
                    System.out.println("Hope you'll purchase tutoring next time! Have a good day!\n\n\n\n");
                else

                //While loop used if user would like to change order, runs until checkout has been done
                while(decision != 3)
                {
                        //Ask user what they would like to do, runs to if statement inside loop which allows user to change their mind on what they order
                        decision = Decision(reader);

                        if(decision == 1)
                        {
                            grossMoneyHourly = hourlyTutoring(coupCheck, reader);
                            grossMoneySpell = 0;
                        }
                        else if(decision == 2)
                        {
                            grossMoneySpell = perSpell(coupCheck, reader);
                            grossMoneyHourly = 0;
                        }
                        else if(decision == 3)
                        {
                            //If bill method returns zero nothing was bought, and this means the program wont carry out the paying method because it is not needed
                            grossOverall = bill(grossMoneyHourly,grossMoneySpell, reader);
                            if(grossOverall == 0)
                            {
                                System.out.println("");
                            }
                            else
                                pay(grossOverall,reader);

                        }
                        else
                            System.out.println("ERROR");

                    }

        //Clear line so loop runs smoothly upon iteration
        reader.nextLine();
        }//Loop End

    }//Main End

    //Methods


    //Coupon Method
    static int coupon(final String COUPON, Scanner reader)
    {
        //Ask for password and determine if user should recieve discount
        //Returns a 1 or 0 to determine what prices should be displayed later

        //Variables
        int discountCheck = 0;
        String coupon = "";
        System.out.print("\n\tCoupon Code Special Password: ");
        coupon = reader.nextLine();

        //Determine if they know password
            if(coupon.equalsIgnoreCase(COUPON))
                  {
                      System.out.println("\tCorrect! Discounted prices will be listed throughout your order. ");
                       discountCheck = 1;
                    return discountCheck;
                }
            else
                {
                    System.out.print("\tIncorrect, you may attempt again: ");
                       coupon = reader.nextLine();
                }
                if(coupon.equalsIgnoreCase(COUPON))
                {
                    System.out.println("\tCorrect! Discounted prices will be listed throughout your order. ");
                    discountCheck = 1;
                    return discountCheck;
                }
                else
                {
                    System.out.println("\tWrong Again, regualar prices will be displayed throughout your order.\n\n");
                    return discountCheck;
                }
     }//End of Coupon



    //Decision Method
    static int Decision(Scanner reader)
    {
        //Purchase Decision | Ask user what type of tutoring they would like
        //Variables
        int decision = 0;
        int loop = 0;

        //Loop will run until variable is set to 1(Which meant user picked a viable option)
        //Depending on users choice an int will be stored to determine what method is to be used next
        while(loop == 0)
        {
            System.out.println("What would you like to do?\n1) Order hourly tutoring\n2) Order per spell tutoring\n3) Checkout\n(You may only purchase 1 type of tutoring, and ordering will reset previous orders to 0. You may change your order later however)");
            decision = reader.nextInt();

            if(decision == 1)
            {
                decision = 1;
                loop = 1;
            }
            else if(decision == 2)
            {
                decision = 2;
                loop = 1;
            }
            else if(decision == 3)
            {
                decision = 3;
                loop = 1;
            }
        }//while end

        return decision;

    }//End of Decision



    //Hourly Tutoring Method
    static int hourlyTutoring(int discountCheck, Scanner reader)
    {
        //Method displays either discounted prices or regular based on value determined earlier
        //Will order amount of tutoring and return the cost into a variable

        System.out.println("\nHourly Tutoring:\n");

        //Variables
        int hoursOrdered = 0;
        int amountOwedHours = 0;


        //if decides what prices to display
        if (discountCheck == 1)
        {
            //Display Discounts
            System.out.println("0 - 4 hours of tutoring: 2219 knuts per hour");
            System.out.println("There is a reduction of 145 knuts per hour for every increment of 5 hours of tutoring\n");

            //Place order
            System.out.println("You currently have no hours of tutoring ordered");
            System.out.println("How many whole hours would you like to order(Will reset all other values): ");
               hoursOrdered = reader.nextInt();

               //Calclate hourly rate
               if(hoursOrdered <= 0)
                System.out.println("Interpreted as 0 hours ordered");
               else if(hoursOrdered <= 4)
                amountOwedHours = hoursOrdered * 2219;
            else if(hoursOrdered <= 9)
                amountOwedHours = hoursOrdered * 2088;
            else if(hoursOrdered <= 14)
                amountOwedHours = hoursOrdered * 1958;
            else if(hoursOrdered <= 19)
                amountOwedHours = hoursOrdered * 1827;
            else if(hoursOrdered <= 24)
                amountOwedHours = hoursOrdered * 1697;
            else
                amountOwedHours = hoursOrdered * 1566;
        }

        else
        {
            //Display Regular
            System.out.println("0 - 4 hours of tutoring: 2465 knuts per hour");
            System.out.println("There is a reduction of 145 knuts per hour for every increment of 5 hours of tutoring\n");

            //Place order
            System.out.println("You currently have no hours of tutoring ordered");
            System.out.println("How many whole hours would you like to order(Will reset all other values): ");
            hoursOrdered = reader.nextInt();

            //Calclate hourly rate
            if(hoursOrdered < 0)
                System.out.println("Interpreted as 0 hours ordered");
            else if(hoursOrdered <= 4)
            amountOwedHours = hoursOrdered * 2465;
            else if(hoursOrdered <= 9)
                amountOwedHours = hoursOrdered * 2320;
            else if(hoursOrdered <= 14)
                amountOwedHours = hoursOrdered * 2175;
            else if(hoursOrdered <= 19)
                amountOwedHours = hoursOrdered * 2030;
            else if(hoursOrdered <= 24)
                amountOwedHours = hoursOrdered * 1885;
            else
                amountOwedHours = hoursOrdered * 1740;
        }

        //Return and display
        System.out.println("You have ordered " + hoursOrdered + " hour(s) of tutoring\n\n");
        return amountOwedHours;

    }//End of hourly


    //Per spell Method
    static int perSpell(int discountCheck, Scanner reader)
    {
        //Method displays either discounted prices or regular based on value determined earlier
        //Will order amount of spells and return the cost into a variable

        System.out.println("\nPer Spell Tutoring:\n");

        //variables
        int spellsOrdered = 0;
        int amountOwedSpells = 0;

        //Display Prices
        if (discountCheck == 1)
        {
            //Display Discounts
            System.out.println("1 - 2 spells: 2456 knuts each");
            System.out.println("3 - 4 spells: 1972 knuts each");
            System.out.println("All 5 spells: 1479 knuts each");
            System.out.println("(Your discount will subtract 116 knuts from each spell order)\n");

            //Place Order
            System.out.println("\nYou now have zero ordered spells,");
            System.out.println("How many spells would you like to purchase(Will reset all other values): ");
            spellsOrdered = reader.nextInt();

            //Calculate Price
            if(spellsOrdered < 0)
                System.out.println("Interpreted as no spells ordered");
            else if(spellsOrdered <= 2)
                amountOwedSpells = (spellsOrdered * 2465) - (spellsOrdered * 116);
            else if(spellsOrdered <= 4)
                amountOwedSpells = (spellsOrdered * 1972) - (spellsOrdered * 116);
            else if(spellsOrdered == 5)
                amountOwedSpells = (spellsOrdered * 1479) - (spellsOrdered * 116);
            else
            {
                System.out.println("Interpreted as 5 spells ordered");
                spellsOrdered = 5;
                amountOwedSpells = (spellsOrdered * 1479) - (spellsOrdered * 116);
            }

        }
        else
        {
            //Display Regular
            System.out.println("1 - 2 spells: 2456 knuts each");
            System.out.println("3 - 4 spells: 1972 knuts each");
            System.out.println("All 5 spells: 1479 knuts each");

            //Place Order
            System.out.println("\nYou now have zero ordered spells,");
            System.out.println("How many spells would you like to purchase(Will reset all other values): ");
            spellsOrdered = reader.nextInt();

            //Calculate Price
            if(spellsOrdered < 0)
                System.out.println("Interpreted as no spells ordered");
            else if(spellsOrdered <= 2)
                amountOwedSpells = (spellsOrdered * 2465);
            else if(spellsOrdered <= 4)
                amountOwedSpells = (spellsOrdered * 1972);
            else if(spellsOrdered == 5)
                amountOwedSpells = (spellsOrdered * 1479);
            else
            {
                System.out.println("Interpreted as 5 spells ordered");
                spellsOrdered = 5;
                amountOwedSpells = (spellsOrdered * 1479) - (spellsOrdered * 116);
            }
        }

        //return and prompt
        System.out.println("You have ordered " + spellsOrdered + " spell(s)\n\n");
        return amountOwedSpells;


    }//End of perSpell


    //Show Bill Method
    static int bill(int grossMoneyHourly, int grossMoneySpell, Scanner reader)
    {
        //Variables
        int grossOverall = 0;

        if(grossMoneyHourly == 0 && grossMoneySpell == 0)
        {
            System.out.println("\n\nHope you'll purchase tutoring next time! Have a good day!\n\n");
            grossOverall = 0;
        }
        else

        //Uses if to decide what data to put out
        if(grossMoneyHourly == 0)
        {
            //blank if used to ignore displaying hourly info if entered
        }
        else
        {
            //Display price of order and set amount of money to new variable
            System.out.println("\n\nYou have ordered " + grossMoneyHourly + " knuts worth of hourly tutoring");
            grossOverall = grossMoneyHourly;
        }

        if(grossMoneySpell == 0)
        {
            //blank if used to ignore displaying spell info if entered
        }
        else
        {
            //Display price of order and set amount of money to new variable
            System.out.println("\n\nYou have ordered " + grossMoneySpell + " knuts worth of spell tutoring");
            grossOverall = grossMoneySpell;
        }

        //Return variable containing the amount of owed money
        return grossOverall;

    }//End of Bill


    //Pay Method
    static void pay(double grossOverall, Scanner reader)
    {
        //Addes Tax
        //Method runs until user has paid for their order

        //Variables
        double grossTax = 0;
        double tax = 0;
        int choice = 0;
        int usersMoney = 0;
        int money = 0;
        int moneyLeft = 0;
        int netOverall = 0;

        //Create tax, round, display total money owed
        grossTax = grossOverall + (grossOverall * .05);
        tax = (Math.round(grossTax));

        System.out.println("\n\tMinistry of Magic Tax: " + Math.round(grossOverall * .05) + " knuts");
        netOverall = (int)(double)tax;

        System.out.println("\nAfter the added tax, you owe me " + netOverall + " knuts\n");


        //Get Money
        System.out.println("Here is the currency exchange: ");
        System.out.println("\t29 knuts == 1 sickle\n\t17 sickles == 1 galleon == 493 knuts");

        while(usersMoney < netOverall)
        {
                System.out.println("What currency would you like to add(You can add multiple of each): \n1) Galleon\n2) Sickles\n3)Knuts");
                choice = reader.nextInt();

                if(choice == 1)
                {
                    System.out.print("How many galleons would you like to add to your total: ");
                    money = reader.nextInt();

                    usersMoney += (money * 493);
                }
                else if(choice == 2)
                {
                    System.out.print("How many sickles would you like to add to your total: ");
                    money = reader.nextInt();

                    usersMoney += (money * 29);
                }
                else if (choice == 3)
                {
                    System.out.print("How many knuts would you like to add to your total: ");
                    money = reader.nextInt();

                    usersMoney += (money);
                }
                else
                {
                    System.out.println("Enter something valid");
                }

                //Calc how much is left
                moneyLeft = netOverall - usersMoney;
                if(moneyLeft < 0)
                    System.out.println();
                else
                    System.out.println("You still must pay " + moneyLeft + " knuts\n");

        }//While end

                //Give Change if needed
                int overPay = (Math.abs(moneyLeft));
                if(overPay > 0)
                {
                    System.out.println("You have overpaid by " + overPay + " knuts");

                    if(overPay >= 493)
                    {
                        int galleonsBack = overPay / 493;
                        System.out.print("Your change is " + galleonsBack + " galleons");
                        int leftOverKnuts = overPay % 493;

                        if(leftOverKnuts >= 29)
                        {
                            int sicklesBack = leftOverKnuts / 29;
                            System.out.print(", " + sicklesBack + " sickles");
                            int leftOverKnuts2 = leftOverKnuts % 29;
                            System.out.print(", and " + leftOverKnuts2 + " knuts");
                        }
                        else
                            System.out.println(" and " + leftOverKnuts + " knuts");
                    }

                    else if(overPay >= 29)
                    {
                        int sicklesBack = overPay / 29;
                        System.out.print("Your change is " + sicklesBack + " sickles");
                        int leftOverKnuts = overPay % 29;
                        if(leftOverKnuts > 0)
                            System.out.print("and " + leftOverKnuts + " knuts");
                    }
                    else
                    {
                        System.out.println("Your change is " + overPay + " knuts");
                    }

                }
                else
                    System.out.println("You have payed accordingly");

                //Final Prompt
                System.out.println("\n\nThank you for shopping here! We hope to see you again!\n\n\n\n");
    }//End of pay





    }//End of Program

