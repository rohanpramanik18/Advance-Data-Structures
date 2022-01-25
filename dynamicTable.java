import java.util.* ;

class dynamicTable 
{
    static int arr[] ;
    static int count ;     // Stores the count (no. of elements) in the table
    static int excount ;   // Stores the count at which the table size doubles

    // Constructor for initializing the array
    public dynamicTable(int size)
    {
        arr = new int[size] ;
        count = 0 ;
    }

    // Method for inserting the element
    static void insert(int ele)
    {
        // Doubles the array if array lenght is equal to count
        if(arr.length == count)
        {
            // Initializes array size to 1 for inserting first element
            if(arr.length == 0)
            {
                arr = new int[1] ;
            }
            else
            {
                // Temporary-array of double the array size
                int temparr[] = new int[arr.length * 2] ;
                
                // Assigning count to excount (Required for deletion)
                excount = count ;

                // Copying arr elements to temp-arr and inserting 0 in remaining spaces of temp-arr 
                for(int i=0; i<temparr.length; i++)
                {
                    if(i < arr.length)
                    {
                        temparr[i] = arr[i] ;
                    }
                    else
                    {
                        temparr[i] = 0 ;
                    }
                }

                // Assigning temp-arr to original array
                arr = temparr ;
            }
        }

        arr[count] = ele ;  // Inserting element at count position to arr
        count ++ ;          // Incrementing count
    }

    // Method for deleting the element
    static void delete()
    {
        // Empty array when count is 0
        if(count == 0)
        {
            System.out.println("\nNo elements to delete");
        }
        else 
        {
            // If count-1 = excount means deletion of the element that caused the array size to double and halving the size after deleting it. For eg:- Deleting [3] from table : [1][2][3][0] 
            if(count-1 == excount)
            {
                // Creating temporary array of half size
                int temparr[] = new int[excount] ;

                // Copying the elements
                for(int i=0; i<temparr.length; i++) 
                {
                    temparr[i] = arr[i] ;
                }

                // Assigning temp-arr to original array
                arr = temparr ;

                count -- ;              // Decrementing the count
                excount = excount/2 ;   // Dividing excount by 2 to get the next halving position
            }
            else
            {   
                // Else simply deleting by decrementing the count and assigning 0 to that position in the array
                count -- ;
                arr[count] = 0 ;
            }

            System.out.println("\nElement deleted");
        }
    }

    // Method for printing the elements
    static void display()
    {
        // Empty array when count is 0
        if(count == 0)
        {
            System.out.println("\nTable is empty");
        }
        else
        {
            // Printing the elements in a row
            System.out.println() ;
            for(int i=0; i<arr.length; i++)
            {
                System.out.print("["+arr[i]+"]") ; 
            }
            System.out.println() ;
        }
        
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in) ;

        // Initializing array size with 0
        dynamicTable obj1 = new dynamicTable(0) ;

        // Menu-driven loop for inserting, deleting and printing 
        while(true)
        {
            System.out.println("\nEnter your choice \n1. Insert \t 2. Delete \t 3. Display \t 4. Exit ") ;
            int ch = sc.nextInt() ;

            switch(ch)
            {
                case 1 : System.out.println("Enter element to insert :- ") ;
                        int ele = sc.nextInt() ;
                        insert(ele) ;
                        break ;

                case 2 : delete() ;
                         break ;

                case 3 : display() ;
                         break ;

                case 4 : System.exit(0) ;
                         break ;

                default : System.out.println("Wrong Choice") ;

            }
        }
    }
}