package org.cz.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.util.Scanner;

public class Main implements Runnable{
    public static void main(String[] args) throws JAXBException {
        String filePath = "";
        while(true){
            Scanner scanner = new Scanner(System.in);
            if("true".endsWith(scanner.next())){
                new Thread(new Main("<?xml version=\"1.0\" encoding=\"UTF-8\"?><dataConvert xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" encoding=\"GBK\" relativePath=\"/bms/csvFile/\" updateRecoder=\"updateRecoder\" xsi:noNamespaceSchemaLocation=\"../sync-1.0.xsd\"><data class=\"java.util.Map\" typeCode=\"CROSSING_INFO\"><fields><field target=\"originalIndexcode\" unique=\"true\"></field><field target=\"name\"></field><field target=\"appCode\"></field><field target=\"treeNodeIndexcode\"></field><field target=\"enable\"></field><field target=\"laneNum\"></field><field target=\"latitude\"></field><field target=\"longitude\"></field><field target=\"crossingType\"></field><field target=\"intercity\"></field><field target=\"relatedCameraIndexCode\"></field></fields><defaultValues><defaultValue target=\"enable\">1</defaultValue><defaultValue target=\"typeCode\">CROSSING_INFO</defaultValue></defaultValues><updateExcludes><updateExclude target=\"relatedCameraIndexCode\" model=\"forceUpdate\" /><updateExclude target=\"longitude\" model=\"zero\" /><updateExclude target=\"latitude\" model=\"zero\" /><updateExclude target=\"createTime\" model=\"force\" /><updateExclude target=\"updateTime\" model=\"force\"/></updateExcludes></data><data class=\"java.util.Map\" typeCode=\"LANE_INFO\"><fields><field target=\"originalIndexcode\" unique=\"true\"></field><field target=\"name\"></field><field target=\"appCode\"></field><field target=\"treeNodeIndexcode\"></field><field target=\"crossingIndexCode\"></field><field target=\"laneNumber\"></field><field target=\"relatedCameraIndexCode\"></field></fields><defaultValues><defaultValue target=\"enable\">1</defaultValue><defaultValue target=\"typeCode\">LANE_INFO</defaultValue></defaultValues><updateExcludes><updateExclude target=\"relatedCameraIndexCode\" model=\"forceUpdate\" /><updateExclude target=\"createTime\" model=\"force\" /><updateExclude target=\"updateTime\" model=\"force\"/></updateExcludes></data></dataConvert>\n",DataConvert.class)).start();
//                new Thread(new Main("F:\\development\\mytest\\sourcecode\\common\\src\\main\\resources\\jaxb\\sync.xml",DataConvert2.class)).start();
//                new Thread(new Main("F:\\development\\mytest\\sourcecode\\common\\src\\main\\resources\\jaxb\\sync.xml",DataConvert2.class)).start();

/*                //验证sync_out_1.0
                xmlToBean("F:\\development\\mytest\\sourcecode\\common\\src\\main\\resources\\jaxb\\sync-1.0.xml",DataConvert.class);
                //验证sync_out_0.1
                xmlToBean("F:\\development\\mytest\\sourcecode\\common\\src\\main\\resources\\jaxb\\sync.xml",DataConvert2.class);
                //验证bms
                xmlToBean("F:\\development\\mytest\\sourcecode\\common\\src\\main\\resources\\jaxb\\bms-1.0.xml",DataConvert2.class);
                //验证vms
                xmlToBean("F:\\development\\mytest\\sourcecode\\common\\src\\main\\resources\\jaxb\\vms-1.0.xml",DataConvert2.class);
                //验证ncg
                xmlToBean("F:\\development\\mytest\\sourcecode\\common\\src\\main\\resources\\jaxb\\ncg-1.0.xml",DataConvert2.class);
                //验证ivms-6.1
                xmlToBean("F:\\development\\mytest\\sourcecode\\common\\src\\main\\resources\\jaxb\\ivms-6.1.xml",DataConvert2.class);*/
            }
        }
    }

    public static Object xmlToBean(String filePath,Class<?> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
//        File file = new File(filePath);
        Object o = unmarshaller.unmarshal(new StringReader(filePath));
        System.out.println("success");
        return o;
    }

    private String path;
    private Class clazz;

    public Main(String path, Class clazz) {
        this.path = path;
        this.clazz = clazz;
    }

    @Override
    public void run() {
        try {
            xmlToBean(path,clazz);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
