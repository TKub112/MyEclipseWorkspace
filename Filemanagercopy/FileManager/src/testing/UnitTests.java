package testing;


import org.junit.Test;

import clientApp.FileCopy;
import clientApp.FxGetFolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import serverApp.ManagerCSV;

import java.io.File;
import java.util.ArrayList;
import org.junit.Assert;



public class UnitTests {

	
	/*
     * Test a getting a list of file
     */
    @Test
    public void getListFilesFolder2()
    {
	ObservableList<String> testList = FXCollections.observableArrayList();
	testList.add("test1.txt");
	testList.add("test2.txt");
	testList.add("test3.txt");
	ObservableList<String> end;
	end = FxGetFolder.getListFilesFolder("C:\\Users\\Tobiasz\\eclipse-workspace\\FileManager\\testDir\\readfoldertest");
	System.out.println(end);
	Assert.assertEquals(end,testList);
    }
    
    /*
     * CopyFileTester
     * testing copying a file class
     */
	@Test
    public void CopyFileTester()
    {
    	File f = new File("C:\\Users\\Tobiasz\\eclipse-workspace\\FileManager\\testDir\\test.txt");
    	Thread t = new Thread(new FileCopy(f,"C:\\Users\\Tobiasz\\eclipse-workspace\\FileManager\\testDir\\testintest\\"));
		t.start();
		File f1 = new File("C:\\Users\\Tobiasz\\eclipse-workspace\\FileManager\\testDir\\testintest\\test.txt");
    	Assert.assertEquals(f1, f);
    }
     

	 /*
     * getFileDiscTest
     * testing MagaerCSV method
     */
    @Test
    public void getFileDiscTest()
    {
    	String test = ManagerCSV.getFileDisc("Marcin", "test.txt");
    	String test1 = "1";
    	
    	Assert.assertEquals(test, test1);
    	
    }
    
    /*
     * CSVManagerTest
     */
    @Test
    public void CSVManagerTest()
    {
		ArrayList<String> rand = new ArrayList<String>();
		ArrayList<String> test =ManagerCSV.getNameOfUserFiles(null);;
		
		Assert.assertEquals(test, rand);
    }
    
   /*
    *
    * TestingCompressClass validation of compression
   */
    @Test
    public void TestCompressClass()
    {
    	File f = new File("C:\\Users\\Tobiasz\\eclipse-workspace\\FileManager\\testDir\\test.txt");
    	Thread t = new Thread(new FileCopy(f,"testDir/test.txt/testintest"));
		t.start();
		File f1 = new File("C:\\Users\\Tobiasz\\eclipse-workspace\\FileManager\\testDir\\\\test.txt\\\\testintest\\\\test.txt compressed.zip");
    	
    	Assert.assertEquals(f1, f);
    }
     
    
    /*
     * getListFilesFolder tester, try to get list form unknwon path
     */
    @Test
    public void getListFilesFolder()
    {
		ObservableList<String> testlist;
		testlist = FxGetFolder.getListFilesFolder("none");
		Assert.assertEquals(testlist,null);
    }
    
    
 
     
    
}
