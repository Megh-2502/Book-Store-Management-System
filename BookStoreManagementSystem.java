import java.util.*;
import javax.xml.transform.Source;
import java.io.*;

class User {
    String name;
    int id_no;
    String book1, book2;
    int book_no, issued_book;

    public User(String name, int id_no) {
        this.name = name;
        this.id_no = id_no;
    }
}

public class BookStoreManagementSystem {
    class Node {
        String data;
        Node left, right;

        public Node(String item) {
            data = item;
            left = null;
            right = null;
        }
    }

    Node root = null;
    static Scanner sc;

    void insert(String data) {
        root = insertRec(root, data);
    }

    Node insertRec(Node root, String data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) // If book name > root then place it as Right child
        {
            root.right = insertRec(root.right, data);
        } else {
            System.out.println("error.");
        }
        return root;
    }

    void deletedata(String data) {
        root = deleteRec(root, data);
    }

    Node deleteRec(Node root, String data) {
        if (root == null)
            return root;

        // If book name < root then search it at left side and delete
        if (data.compareTo(root.data) < 0)
            root.left = deleteRec(root.left, data);
        // If book name > root then search it at right side and delete
        else if (data.compareTo(root.data) > 0)
            root.right = deleteRec(root.right, data);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.data = minValue(root.right);

            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    void update(String data, String data1) {
        deletedata(data);
        insert(data1);
    }

    String minValue(Node root) {
        String minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    void printInorder(Node node) {
        if (node == null)
            return;

        printInorder(node.left);

        System.out.print(node.data + "        ");

        printInorder(node.right);
    }

    void printInorder() {
        printInorder(root);
    }

    void inorder() {
        inorderRec(root);
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.data);
            inorderRec(root.right);
        }
    }

    void printTree() {
        root = printTreeRec(root, 0);
    }

    Node printTreeRec(Node t, int space) {
        if (t == null)
            return root;

        space += 5;

        printTreeRec(t.right, space);

        System.out.println();

        for (int i = 5; i < space; i++)
            System.out.print(" ");
        System.out.print("[" + t.data + "]");

        return printTreeRec(t.left, space);
    }

    boolean containsNode(String value) {
        return containsNodeRecursive(root, value);
    }

    boolean containsNodeRecursive(Node current, String data) {
        if (current == null) {
            return false;
        }
        if (data.equalsIgnoreCase(current.data)) {
            return true;
        }
        if (data.compareTo(current.data) < 0) {
            return containsNodeRecursive(current.left, data);
        } else {
            return containsNodeRecursive(current.right, data);
        }
    }

    static void display(User arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("\nName of Student:" + arr[i].name);
            System.out.println("\nId of Student:" + arr[i].id_no);

        }
    }

    static void Selectionsort(User arr[]) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j].id_no < arr[min_idx].id_no) // Sort according to ID number of student
                    min_idx = j;

            User temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) throws IOException {
        sc = new Scanner(System.in);
        BookStoreManagementSystem bs = new BookStoreManagementSystem();
        BookStoreManagementSystem tree = new BookStoreManagementSystem();
        User[] array = new User[5];
        array[0] = new User("megh", 1);
        array[1] = new User("shaily", 2);
        array[2] = new User("rutvi", 3);
        array[3] = new User("ali", 4);
        array[4] = new User("nasir", 5);

       /* FileWriter fr = new FileWriter("append.txt", true);
        BufferedWriter br = new BufferedWriter(fr);

        FileWriter fr1 = new FileWriter("append.txt", true);
        BufferedWriter br1 = new BufferedWriter(fr1);

        FileWriter fr2 = new FileWriter("append.txt", true);
        BufferedWriter br2 = new BufferedWriter(fr2);

        FileWriter fr3 = new FileWriter("append.txt", true);
        BufferedWriter br3 = new BufferedWriter(fr3);

        FileReader file = new FileReader("x.txt");
        BufferedReader reader = new BufferedReader(file);

        FileReader file2 = new FileReader("y.txt");
        BufferedReader reader2 = new BufferedReader(file2);

        FileReader file3 = new FileReader("z.txt");
        BufferedReader reader3 = new BufferedReader(file3);*/
        int i = 0;
        int bookIndex = 0;
        int[][] arr = new int[100][2];
        String[] bookNames = new String[arr.length];
        int ch1;
        do {
            System.out.println("\n.....................................");
            System.out.println("1. Admin Login. ");
            System.out.println("2. User Login. ");
            System.out.println("3. Exit. ");
            System.out.println("\n.....................................");

            System.out.println("\nEnter Your choice:");
            ch1 = sc.nextInt();

            switch (ch1) {
                case 1:
                    String pwd1 = "abc123";
                    String id1 = "abc123";

                    System.out.println("\nEnter UserId:");
                    String id2 = sc.next();

                    System.out.println("\nEnter Password:");
                    String pwd2 = sc.next();

                    if (!id1.equals(id2))
                        System.out.println("Invalid Userid.");
                    else if (!pwd1.equals(pwd2))
                        System.out.println("Invalid Password.");
                    else {
                        System.out.println("Login successfully.");
                    }
                    int ch2;
                    do {
                        System.out.println("\n.....................................");
                        System.out.println("1. Add book. ");
                        System.out.println("2. Delete book. ");
                        System.out.println("3. Update book. ");
                        System.out.println("4. Print Books Details. ");
                        System.out.println("5. Print Books in-order. ");
                        System.out.println("6. Print tree ");
                        System.out.println("7. Exit");

                        System.out.println("\n.....................................");

                        System.out.println("\nEnter Your choice:");
                        ch2 = sc.nextInt();
                        switch (ch2) {
                            case 1:
                               /* //read book names from already existed x.txt file
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    tree.insert(line);
                                    arr[i][0]++;
                                    arr[i][1]++;
                                    //bookIndex=4;
                                    bookNames[bookIndex] = line;
                                    bookIndex++;
                                    i++;
                                    //reader.readLine();
                                }*/
                                //to add a new book
                                System.out.println("\nEnter name of book:");
                                String name = sc.next();
                                boolean z1 = tree.containsNode(name);
                                bookNames[bookIndex]=name;
                                if (z1 == true) {
                                    System.out.println("\nIt is already exists:");
                                } else {
                                    System.out.println("\nEnter quantity of book:");
                                    int quantity = sc.nextInt();
                                    
                                    tree.insert(name);
                                    arr[i][0] += quantity; // Total quantity of books
                                    arr[i][1] += quantity; // Available quantity of books
                                    i++;
                                    /*br1.write("name"+name+ "  ");
									br2.write("total"+quantity+"  ");
									br3.write("q"+quantity);
                                    br1.flush();
                                    br2.flush();
                                    br3.flush();*/
                                }
                                bookIndex++;
                                break;

                            case 2:
                                System.out.println("\nEnter name of book:");
                                String b1 = sc.next();

                                boolean x = tree.containsNode(b1);
                                if (x == true) {
                                    System.out.println("DELETE CHECK");
                                    tree.deletedata(b1);
                                }
                                break;

                            case 3:
                                System.out.println("\nEnter name of book:");
                                String b2 = sc.next();
                                int a = -1; // Initialize a to -1
                               /* // Fill the bookNames array with book names from arr
                                for ( i = 0; i < arr.length; i++) {
                                    if (bookNames[i] != null) {
                                        bookNames[i] = sc.next();
                                        System.out.println("b");
                                    }
                                }*/
                                
                                // Search for the book in the bookNames array
                                int j=0;
                                boolean y = tree.containsNode(b2);
                                if (y == true) {
                                    for (j = 0; j < bookNames.length; j++) {
                                    if (bookNames[j] != null && bookNames[j].equalsIgnoreCase(b2)) {
                                        a = j; // Set a to the index where the book is found
                                        System.out.println("Index For Checking: "+a);
                                        break; // Exit the loop once found
                                        
                                    }
                                    
                                }
                                    System.out.println("\nName of book: "+b2);
                                    System.out.println("\nEnter quantity of book to add more:");
                                    int q = sc.nextInt();
                                    arr[a][0] += q; // Update the total quantity of the book
                                    arr[a][1] += q; // Update the available quantity of the book
                                }
                               /* if (arr[a][0]!=-1 && arr[a][1]!=-1) {
                                    System.out.println("\nEnter quantity of book to add more:");
                                    int q = sc.nextInt();
                                    arr[a][0] += q; // Update the total quantity of the book
                                    arr[a][1] += q; // Update the available quantity of the book
                                } */
                                else {
                                    System.out.println("Book not found.");
                                }
                                break;

                            case 4:
                                for (int r = 0; r < i; r++) {
                                    System.out.println("Name of book is: " + bookNames[r]);
                                    System.out.println("Total Quantity of book is: " + arr[r][0]);
                                    System.out.println("Available Quantity of book is: " + arr[r][1]);
                                    System.out.println();
                                }
                                break;

                            case 5: // To Print Books in-order
                                tree.printInorder();
                                break;

                            case 6: // To print binary search tree
                                tree.printTree();
                                break;

                            case 7:
                                break;
                        }
                    } while (ch2 != 7);
                    break;

                case 2:
                    int ch3;
                    do {
                        System.out.println("\n.....................................");
                        System.out.println("1. Issue book. ");
                        System.out.println("2. Return book. ");
                        System.out.println("3. Purchase book.");
                        System.out.println("4. Exit");
                        System.out.println("\n.....................................");

                        System.out.println("\nEnter Your choice:");
                        ch3 = sc.nextInt();
                        switch (ch3) {
                            case 1:
                                int index;
                                System.out.println("\nEnter your id:");
                                int id = sc.nextInt();
                                int l=0;
                                // Find the student by ID
                                for ( l = 0; l < array.length; l++) {
                                    if (array[l].id_no == id) {
                                        System.out.println("ID: "+array[l].id_no);
                                        System.out.println("Name: "+array[l].name);
                                        System.out.println();
                                        
                                        break; // Exit the loop once the student is found
                                    }
                                }
                                index=l;
                                System.out.println("ID INDEX FOR CHECKING: "+index);
                                if (index != -1) {
                                    if (array[index].book_no >= 2) {
                                        System.out.println("\nYou can't issue more than two books.");
                                    } else {
                                        System.out.println("\nEnter name of book:");
                                        String book = sc.next();
                                        boolean bookFound = false;
                                        int m = -1;
                                        int j=-1;
                                        // Find the book in the array of book names
                                        for (j = 0; j < arr.length; j++) {
                                            // System.out.println("a'");
                                            if (bookNames[j] != null && String.valueOf(bookNames[j]).equalsIgnoreCase(book)) {
                                                System.out.println("CHECK 1");
                                                m = j;
                                                bookFound = true;
                                                break; // Exit the loop once the book is found
                                            }
                                        }
                                        System.out.println("CHECK 2");
                                        if (bookFound && arr[m][1] > 0) {
                                            if (array[index].book1 == null)
                                                array[index].book1 = book;
                                            else
                                                array[index].book2 = book;
                                                System.out.println("CHECK 3");
                                            System.out.println("Book issued successfully.");
                                            arr[m][1]--;
                                            System.out.println("CHECK 4");
                                            array[index].book_no++;
                                            System.out.println("CHECK 5");
                                            /*br.write("\nStudent name:	" + array[index].name);
                                            br.write("\nStudent ID  :	" + array[index].id_no);
                                            br.write("\nIssued Book :	" + book);*/
                                        } else {
                                            System.out.println("Invalid ID");
                                        }
                                    }
                                }
                                break;

                            case 2:
                                try {
                                    int ind;
                                    System.out.println("\nEnter your id:");
                                    int s_id = sc.nextInt();
                                    System.out.println("\nEnter name of book:");
                                    String Rbook = sc.next();
                                    int k=0;
                                    // Find the student by ID and check if they have the book
                                    for ( k = 0; k < array.length; k++) {
                                        if (array[k].id_no == s_id && (array[k].book1 != null && array[k].book1.equalsIgnoreCase(Rbook)
                                                || array[k].book2 != null && array[k].book2.equalsIgnoreCase(Rbook))) {
                                            
                                            System.out.println("CHECK 6");
                                            break;
                                        }
                                    }
                                    ind = k;
                                    System.out.println("CHECK "+ind);
                                    if (ind != -1) {
                                        boolean bookFound = false;
                                        int n = -1;
                                        int j=-1;
                                        // Find the book in the array of book names
                                        for (j = 0; j < arr.length; j++) {
                                            if (bookNames[j] != null && String.valueOf(bookNames[j]).equalsIgnoreCase(Rbook)) {
                                                n = j;
                                                bookFound = true;
                                                System.out.println(bookFound);
                                                System.out.println("CHECK 7");
                                                break; // Exit the loop once the book is found
                                            }
                                            
                                            
                                        }

                                        if (bookFound) {
                                            if (array[ind].book1 != null && array[ind].book1.equalsIgnoreCase(Rbook))
                                                array[ind].book1 = null;
                                            else
                                                array[ind].book2 = null;

                                            System.out.println("Book is returned successfully.");
                                            int a = n ; 
                                            System.out.println("CHECK 8");
                                            arr[a][1]++;
                                            array[ind].book_no--;
                                            System.out.println("CHECK 9");
                                        } else
                                            System.out.println("Book is not available.");
                                            System.out.println("CHECK 10");
                                    } else
                                        System.out.println("Invalid ID");
                                } catch (Exception e) {
                                    System.out.println("Something went wrong.");
                                }
                                break;

                            case 3:
                                System.out.println("CHECH 11");
                                System.out.println("\nEnter name of book to purchase:");
                                String purchaseBookName = sc.next();
                                boolean purchaseBookFound = false;
                                int purchaseBookIndex=-1;

                                for(int j=0;j<arr.length;j++)
                                {
                                    if(bookNames[j].equalsIgnoreCase(purchaseBookName))
                                    {
                                        System.out.println("CHECK 12");
                                        purchaseBookIndex=j;
                                        purchaseBookFound=true;
                                        break;
                                    }
                                }

                                if(purchaseBookFound)
                                {
                                    System.out.println("CHECK 13");
                                    System.out.println("Enter quantity of books to purchase: ");
                                    int purchaseQuantity=sc.nextInt();
                                    if(purchaseQuantity<0)
                                    {
                                        System.out.println("Invalid Quantity");

                                    }
                                    else if(arr[purchaseBookIndex][1]<purchaseQuantity)
                                    {
                                        System.out.println("CHECK 14");
                                        System.out.println("Insufficient Stock");
                                    }

                                    else
                                    {
                                        System.out.println("CHECK 15");
                                        System.out.println("Are you a member?\n(Yes/No)");
                                        String memberStatus=sc.next();
                                        if(memberStatus.equalsIgnoreCase("Yes"))
                                        {
                                            double bookPrice=500.0;
                                            double discountedPrice=bookPrice*0.2;
                                            System.out.println("Your Discounted Price: "+discountedPrice+"Rs");
                                        }
                                        else if(memberStatus.equalsIgnoreCase("No"))
                                        {
                                            System.out.println("Would you like to become member? \n(Yes/No)");
                                            String becomeMember =sc.next();
                                            if(becomeMember.equalsIgnoreCase("Yes"))
                                            {
                                                System.out.println("CHECK 16");
                                                System.out.println("To become a member you need to pay 1000Rs");
                                                System.out.println("Do you want to continue? \n(Yes/No)");
                                                String payMembershipFee=sc.next();
                                                if(payMembershipFee.equalsIgnoreCase("Yes"))
                                                {
                                                    System.out.println("You are now a member!");
                                                }
                                                else {
                                                    System.out.println("Membership registration cancel.");
                                                }
                                            }
                                            else 
                                            {
                                                double bookPrice=500.0;
                                                System.out.println("Your total price: "+bookPrice*purchaseQuantity+"Rs");
                                            }

                                        }
                                        else {
                                            System.out.println("Invalid input.\nPlease enter 'yes' or 'no' for membership");
                                        }
                                    }
                                }
                                else
                                {
                                    System.out.println("Book not found in the inventory");
                                }
                                break;
                        }
                    } while (ch3 != 4);

                case 3:
                    break;
            }

        } while (ch1 != 3);
        /*br.close();
        fr.close();
        reader.close();*/
    }
}