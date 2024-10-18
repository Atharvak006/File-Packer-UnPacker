import java.util.*;
import java.io.*;


class Unpacker {

    public static void main(String Args[]) throws Exception
    {
        System.out.println("------------------------------------------------");
        System.out.println("----------  Packer Unpacker ----------");
        System.out.println("------------------------------------------------"); 
        
        byte Header[] = new byte[100];
        int FileSize = 0;
        String str = null;
        Scanner sobj = new Scanner(System.in);
        int iRet = 0;
        int iCnt = 0;

        System.out.println("Enter the name of the packed file that you want to unpack : ");
        String PackedFile = sobj.nextLine();

        File fobj = new File(PackedFile);
        
        FileInputStream fiobj = new FileInputStream(fobj);
        /*
            File fobj = new File(PackedFile); creates a File object for the packed file specified by the user.
            FileInputStream fiobj = new FileInputStream(fobj); creates a FileInputStream object to read the content of the packed file.
        */

        while((iRet = fiobj.read(Header,0,100)) > 0)
        {
            String Hstr = new String(Header); //Converts the Header byte array into a String named Hstr.

            str = Hstr.trim(); // removes any leading or trailing whitespace from the header string.
            
            String Tokens[] = str.split(" ");
            /*
                What Happens in Detail:
                Input String: Suppose str is "example.txt 12345".
                Delimiter: The delimiter is a space " ".
                Operation:
                The split(" ") method scans through the str and splits it into parts wherever it finds a space.
                For "example.txt 12345", there is one space in the string. The method will split the string into two parts:
                "example.txt" (the part before the space)
                "12345" (the part after the space)
                Output:
                The result of str.split(" ") is an array of String:

                example : String Tokens[] = {"example.txt", "12345"};
            */

            File NewFile = new File(Tokens[0]);
            NewFile.createNewFile();
            /*
                new File(Tokens[0]):
                Tokens[0]: This is the first element of the Tokens array, which contains the file name. For instance, if Tokens[0] is "example.txt", it represents the name of the file that needs to be created.
            */
            FileSize = Integer.parseInt(Tokens[1]);
            /*
                Tokens[1]
                Tokens Array:
                Tokens is an array of String objects, which was previously populated by splitting a header string.
                For example, if the header string was "example.txt 15000", then Tokens would be an array where:
                Tokens[0] is "example.txt"
                Tokens[1] is "15000"
            */

            byte Buffer[] = new byte[FileSize];
            /*
                byte Buffer[] = new byte[FileSize];

                byte Buffer[]:
                This declares a variable named Buffer which is an array of bytes (byte[]).
                new byte[FileSize]:
                This creates a new byte array with a length of FileSize.
                FileSize: This variable represents the number of bytes to be read and stored. It was previously assigned the size of the file (from the header).
             */
            fiobj.read(Buffer, 0, FileSize);
            /*
                fiobj:
                This is a FileInputStream object that is used to read bytes from a file.
                fiobj.read(Buffer, 0, FileSize):
                This reads bytes from the FileInputStream (fiobj) into the Buffer array.
                Buffer: The destination array where the bytes read from the file will be stored.
                0: The starting offset in the Buffer array where the bytes will be written. In this case, it starts at the beginning of the array.
                FileSize: The number of bytes to read from the file and write into the Buffer array.
             */

            FileOutputStream foobj = new FileOutputStream(NewFile);
            foobj.write(Buffer , 0 , FileSize);    
            
            System.out.println(Tokens[0] + " unpacked succesfully");

            iCnt++;
        }
        System.out.println("------------------------------------------------");

        System.out.println("Unpacking activity completed..");
        System.out.println("Total file unpacked succesfully : "+iCnt);

        System.out.println("------------------------------------------------");
        System.out.println("Thank you for using Packer Unpacker");
    }
};
