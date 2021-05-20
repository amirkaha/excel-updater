# excel-updater
Created a program that can automate the entire process of international Countdown Rice Orders, saving time, add efficiency but most importantly eliminate any human error,
as well as check for any mistakes in the order itself.

The program will:

1/ Read the Coundown order emailed to us(PDF file) using PDF reader, and ensure the port of loading correct.

2/ From the PDF, the Countdown order number, Ship before date, Ship after date and type of Rice data will be extracted.

    (The program will terminate if this order number already exists in our system, as we sometimes recieve duplicates)
    
3/ This information will then be added to our Internation Shipments database (Excel File) using (Apache Poi), and a new incrementing order number will be created for us.


4/ Based on the extracted data, a new order will be created and sent to our supplier. (Word document using Apachi Poi XWPF).


5/ Our newly created purchase order, as well as the Countdown purchase order, will then be moved to a newly created folder, which will be referenced by the extracted order number.


6/ Then Using Selenium Webdriver, our overseas supplier will then be sent an email with our newly created purchase order attached.

