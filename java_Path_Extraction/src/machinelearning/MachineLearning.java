package machinelearning;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class MachineLearning {

        public static void main(String[] args) {

                String fileName = "C://Users/samsung/Desktop/Notes/Spring 2012/Machine Learning/Project/songs_20120323.xls";
                Vector dataHolder = ReadCSV(fileName);
                printCellDataToConsole(dataHolder);
        }

        public static Vector ReadCSV(String fileName) {
                Vector cellVectorHolder = new Vector();

                try {
                        FileInputStream myInput = new FileInputStream(fileName);

                        POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

                        HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

                        HSSFSheet mySheet = myWorkBook.getSheetAt(0);

                        Iterator rowIter = mySheet.rowIterator();

                        while (rowIter.hasNext()) {
                                HSSFRow myRow = (HSSFRow) rowIter.next();
                                boolean storeVal = false;
                                Iterator cellIter = myRow.cellIterator();
                                Vector cellStoreVector = new Vector();
                                while (cellIter.hasNext()) {
                                        HSSFCell myCell = (HSSFCell) cellIter.next();
                                        if(myCell.getColumnIndex() == 2 && !(myCell.getRichStringCellValue().getString().contains("http://")))
                                        {
                                            cellStoreVector.addElement(myCell);
                                            storeVal = true;
                                        }                                        
                                }
                                if(storeVal)
                                {
                                    cellVectorHolder.addElement(cellStoreVector);
                                }                                
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return cellVectorHolder;
        }

        private static void printCellDataToConsole(Vector dataHolder) {

                for (int i = 0; i < dataHolder.size(); i++) {
                        Vector cellStoreVector = (Vector) dataHolder.elementAt(i);
                        for (int j = 0; j < cellStoreVector.size(); j++) {
                                HSSFCell myCell = (HSSFCell) cellStoreVector.elementAt(j);
                                String stringCellValue = myCell.toString();
                                System.out.print(stringCellValue + "\t");
                        }
                        System.out.println();
                }
        }
}
