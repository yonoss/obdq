/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.errorcodes;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author admin
 */
public class ErrorCodeManager 
{
     /**
     * Serialize and stores the Code into the data file
     **/
    public void testGenerateDeprecated(String path)
    {
        try
        {
            for(int i=100;i<1999;i++)
            {
                Code test=new Code();
                if(i<=999)
                    test.setId("P0"+i);
                else
                     test.setId("P"+i);
                test.getMeanings().getMeanings().add(new Meaning("asa da"+i,"1", "0","0"));
                test.getMeanings().getMeanings().add(new Meaning("asa d aa a"+i,"2", "0","0"));
                test.getCauses().getCauses().add(new Cause("asa d aa a cause"+i,"0", "0","0"));
                test.getCauses().getCauses().add(new Cause("as dddd a a 2"+i, "0", "0","0"));
                test.getSolutions().getSolutions().add(new Solution("sol 1"+i,"0", "0", "0"));
                test.getSolutions().getSolutions().add(new Solution("sol 2"+i,"0", "0", "0"));
                test.getDescriptions().getDecriptions().add(new Description("desc 1"+i,"0", "0","0"));
                test.getDescriptions().getDecriptions().add(new Description("desc 2"+i,"0", "0","0"));
                test.setLanguage("en");
                test.setCodeForCar("all");
                storeErrorCode(test, path);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    //ObdqProperties.workingDirectoryPath+ObdqProperties.errorCodesPath
    
    public void storeErrorCode(Code Obj, String FileLocation) throws Exception
    {        
                JAXBContext context = JAXBContext.newInstance(Code.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//m.marshal(Ads, System.out);
                File f = new File(FileLocation);
               
		Writer w = null;
		try
                {
                    f.mkdir();
                    w = new FileWriter(FileLocation+Obj.getId()+".xml",false);
                    m.marshal(Obj, w);
		} finally
                {
                    try
                    {
                        w.close();
                    }
                    catch (Exception e)
                    {
                    }
		}
    }
    /**
     * Retrieves the Code from the data file
     **/
    
    //ObdqProperties.workingDirectoryPath+ObdqProperties.errorCodesPath
    public Code loadErrorCode(String Code, String FileLocation) throws Exception
    {
		JAXBContext context = JAXBContext.newInstance(Code.class);
		Unmarshaller um = context.createUnmarshaller();
		Code result = (Code) um.unmarshal(new FileReader(FileLocation+Code+".xml"));

                return result;
    }  
}
