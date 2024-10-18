import java.util.*;
import java.io.*;

class Packer
{
    public static void main(String arg[]) throws Exception
    {
        System.out.println("------------------------------------------------");
        System.out.println("----------  Welcome to Packer Unpacker ----------");
        System.out.println("------------------------------------------------");

        Scanner sobj = new Scanner(System.in);
        boolean bret = false;

        System.out.println("Enter the name of Directory which contains all files that you want to pack: ");
        String DirectoryName = sobj.nextLine();

        System.out.println("Enter the name of packed file that you want to create : ");
        String PackedFile = sobj.nextLine();
        
        /*
            in your program:
            Create the File Object:
            File fobjPack = new File(PackedFile); creates a File object to represent the file specified by the PackedFile name.
            Create the Actual File:
            fobjPack.createNewFile(); is then used to actually create the file on the disk if it does not already exist.
        */

        File fobjPack = new File(PackedFile);

        bret = fobjPack.createNewFile();
        if(bret == false)
        {
            System.out.println("Unable to create packed file...");
            return;
        }

        System.out.println("Packed file gets succesfully created in current directory.");
        /*
            new File(DirectoryName):
            This constructor creates a new File object representing the path specified by DirectoryName.
            DirectoryName is a String variable that holds the path to a directory (or file) that you want to check or manipulate.
            For example, if DirectoryName is "myDirectory", then fobj represents a path to "myDirectory".
        */

        File fobj = new File(DirectoryName);


        /*
            isDirectory() Method:
            This method is a member of the File class.
            It checks if the File object (fobj) represents a directory.
            It returns true if the path represents an existing directory, and false otherwise.
        */
        bret = fobj.isDirectory();
        if(bret == true)
        {
            File Arr[] = fobj.listFiles();
            /*
                fobj:
                This is a File object that represents a directory. It was created using new File(DirectoryName) and was previously checked to ensure it is indeed a directory.
                listFiles() Method:
                This is a method of the File class.
                It returns an array of File objects representing the files and directories inside the directory represented by fobj.
                The array contains File objects for each entry in the directory, including both files and subdirectories.
                If the path represented by fobj is not a directory, or if an I/O error occurs, listFiles() returns null.
                File Arr[]:
                Arr is an array of File objects.
                Each element in the array represents a file or directory within the directory represented by fobj.
            */

            System.out.println("Number of files in the directory are : "+Arr.length);
            /*
                Arr.length:
                This gives the number of elements in the Arr array.
                Since Arr contains File objects for each file and subdirectory in the directory, Arr.length represents the total number of files and directories present in the directory.
            */

            String Header = null;

            // To write the data into packed file
            FileOutputStream fcombine = new FileOutputStream(PackedFile);
            int iRet = 0;
            byte Buffer[] = new byte[1024];
            /*
                byte Buffer[] = new byte[1024];: Creates a byte array Buffer with a size of 1024 bytes. This array is used to temporarily hold data during file reading or writing operations.
            */

            System.out.println("Packing activity started...");

            // Travel Directory
            for(int i = 0; i < Arr.length; i++)
            {
                // Create header
                Header = Arr[i].getName() + " " + Arr[i].length();
                /*
                    example:
                    Suppose Arr[i] refers to a file named "report.docx" with a size of 15000 bytes:
                    Arr[i].getName(): "report.docx"
                    Arr[i].length(): 15000
                    Header will be: "report.docx 15000"
                */

                System.out.println("File packed with the name : "+Arr[i].getName());

                // Add extra white spaces at the end of header
                for(int j = Header.length(); j < 100; j++)
                {
                    Header = Header + " ";
                }

                // Convert string header into byte array
                byte hArr[] = Header.getBytes();
                /*
                    Header.getBytes(): Converts the Header string into a byte array using the platform's default charset.
                    byte hArr[]: The resulting byte array which holds the byte representation of the Header string.
                    Purpose: Enables file I/O operations by converting a string (header) into a format suitable for writing to or reading from files.
                */

                // Write header into packed file
                fcombine.write(hArr,0,100);

                // To read the file from directory
                // The FileInputStream is used to read the file’s content in bytes, which can then be processed or written to another file.
                FileInputStream fiobj = new FileInputStream(Arr[i]);

                // write the data into packed file after header
                while((iRet = fiobj.read(Buffer)) != -1 )
                {
                    fcombine.write(Buffer,0,iRet);
                }
                fiobj.close();
                /*
                    Reading Data: fiobj.read(Buffer) reads bytes from the input file into Buffer and returns the number of bytes read.
                    Writing Data: fcombine.write(Buffer, 0, iRet) writes the bytes read to the output file.
                    Loop: The while loop ensures that data is read and written in chunks until the end of the file is reached.
                    Closing: fiobj.close() releases the file input stream resources.
                */

            }

            System.out.println("Packing activity completed..");
            System.out.println("Total file packed succesfully : "+Arr.length);

            System.out.println("------------------------------------------------");
            System.out.println("Thank you for using Packer Unpacker");
            System.out.println("------------------------------------------------");
        }
        else 
        {
            System.out.println("There is no such directory");
        }
    }
}
