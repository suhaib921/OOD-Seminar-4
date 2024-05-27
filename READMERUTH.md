

# Util folder kan det uppfattas som plagiat på så sätt som vi skrev.det
# View scanitem, what is the error.
# Why do we have to similar methods in TotalRevenuefileoutput and TotalRevenueview?
# what is the difference between these to classes?
TotalRevenueView is responsible for displaying the total revenue on the user interface by printing to System.out.
Log Total Revenue to a File:
TotalRevenueFileOutput logs the total revenue to a file named Total_Revenue.txt.


#In your Process Sale program, use the Observer pattern to implement a new function-
ality, namely to show 

the sum of the costs for all sales made since the program started. 


This total income shall be handled by two new classes. The first, TotalRevenueView,
shall be placed in the view and show the total income on the user interface, for ex-
ample by printing to System.out.



The second, TotalRevenueFileOutput, shall print the total income to a file. 

How to print to a file is illustrated in se.leiflindback.
oodbook.polymorphism.logapi.FileLogger, which can be found in listing 9.1 in the text-
book and in the book’s git repository. These two classes handling the total income shall
never call the controller or any other class, but instead be updated using the Observer
pattern. Both shall implement the same observer interface. The grade is not affected no
matter how simple or advanced the view is.


# Shouldn't  the sale object that is send to inventory sys inside the salelog be saledto?

# Should we have this in the view Class contr.addSaleObserver(new TotalRevenueFileOutput()); &  contr.addSaleObserver(new TotalRevenueView());
        
